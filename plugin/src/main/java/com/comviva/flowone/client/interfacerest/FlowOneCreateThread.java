package main.java.com.comviva.flowone.client.interfacerest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.BindingType;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.comviva.common.CommandExecutor;
import com.comviva.common.GeneralBodyRequest;
import com.comviva.common.GeneralBodyResponse;
import com.mahindracomviva.cms.common.monitoring.KpiCommonTimeOutException;
import com.mahindracomviva.cms.common.monitoring.MarkType;
import com.mahindracomviva.cms.common.monitoring.MeasureItem;
import com.mahindracomviva.cms.common.monitoring.MeasureUtil;

import main.java.com.comviva.flowone.kpi.FOKpiApplication;
import main.java.com.comviva.flowone.kpi.KPIS;
import main.java.com.comviva.flowone.logger.DBLogger;
import main.java.com.comviva.flowone.logger.DBLoggerThread;

@BindingType("http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class FlowOneCreateThread implements Runnable {

	private CommandExecutor executor = null;
	GeneralBodyRequest grRq = null;
	// Service to execute request
	// Constant Retry
	private int retries;
	private static final String ERROR_CODE = "errorCode";
	private static final String ERROR_MESSAGE = "errorMesage";
	private DBLogger asaLogs;
	private BindingProvider bp;
	private String[] endpoints;
	private Integer retryDelay;
	private Integer i; // variable to check endpoints reference
	private boolean connect;
	private String currentTimeString;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private String imsi;
	private String msisdn;
	private String ki;
	private String endpointCallback;
	private int wsTimeout;
	private String request;
	private String response="";

	/** Medicion de Kpi */
	private FOKpiApplication measureFO;
	private int viewError = 100;
	
	public FlowOneCreateThread(CommandExecutor executor, GeneralBodyRequest grRq, int retries, DBLogger asaLogs,
			String endpointAddress, String imsi, String msisdn, String ki) {

		this.executor = executor;
		this.grRq = grRq;
		this.retries = retries;
		this.asaLogs = asaLogs;
		this.endpoints = grRq.getServiceInfoUrl().split(";");
		this.i = 0;
		this.currentTimeString = this.format.format(new Date());
		this.endpointCallback = endpointAddress;
		this.connect = true;
		this.imsi = imsi;
		this.msisdn = msisdn;
		//this.ki = ki;
		this.ki = "1111111111111111111"+msisdn;

		wsTimeout = Integer.parseInt(grRq.getParameters().containsKey("wsTimeout_seg") ? grRq.getParameters().get("wsTimeout_seg")
				: grRq.getParameters().get("wsTimeout_ms"));
		this.retryDelay = Integer.parseInt(grRq.getParameters().get("retryDelay"));

		System.out.println(this.currentTimeString + " FlowOneThread | Service create");
	}

	
	public FOKpiApplication getMeasureFO() {
		return measureFO;
	}


	public void setMeasureFO(FOKpiApplication measureFO) {
		this.measureFO = measureFO;
	}


	private Integer sentRequest(String endpoint) {
		this.currentTimeString = this.format.format(new Date());
		Integer status = 404;

		try {
			// URL url = new URL("http://localhost:8088/InstantLink?wsdl");
			URL url = new URL(endpoint);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(this.wsTimeout);
			connection.setReadTimeout(this.wsTimeout); //Timeout de lectura.
			byte[] buffer = createRequest().getBytes();

			connection.setRequestProperty("Content-Length", String.valueOf(buffer.length));
			connection.setRequestProperty("Content-Type", "application/soap+xml; charset=" + "UTF-8");
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);

			connection.connect();
			// startDate = new Timestamp(new Date().getTime());
			String response = httpRead(connection, buffer);
			if (response != null)
				this.response = response;
			status = getStatus(response);
			System.out.println(response);
		} catch (SocketTimeoutException se) {
			System.out.println(currentTimeString + "- FlowOneThread | Timeout try connect to endpoint: "+endpoint);
			System.out.println(currentTimeString + "- FlowOneThread | " + se.getMessage());
			System.out.println(currentTimeString + "- FlowOneThread | mark TIMEOUT");
			throw new KpiCommonTimeOutException(KPIS.CreatePrepaidRSA + "Timeout endpoint: "+endpoint);			
		} catch (Exception e) {
			System.out.println(this.currentTimeString + " FlowOneThread | Could not connect to endpoint");
			System.out.println(this.currentTimeString + " FlowOneThread | " + e.getMessage());
		}
		return status;
	}

	private Integer getStatus(String response) {
		this.currentTimeString = this.format.format(new Date());
		Integer status = 404;

		Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(response)));

			NodeList errNodes = doc.getElementsByTagName("Status");
		
			status = Integer.parseInt(errNodes.item(0).getTextContent());

		} catch (SAXException e) {
			System.out.println(this.currentTimeString + " FlowOneThread | Error getting Status");
			System.out.println(this.currentTimeString + " FlowOneThread | " + e.getMessage());
		} catch (IOException e) {
			System.out.println(this.currentTimeString + " FlowOneThread | Error getting Status");
			System.out.println(this.currentTimeString + " FlowOneThread | " + e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out.println(this.currentTimeString + " FlowOneThread | Error getting Status");
			System.out.println(this.currentTimeString + " FlowOneThread | " + e.getMessage());
		} catch (Exception e) {
			System.out.println(this.currentTimeString + " FlowOneThread | Error getting Status");
			System.out.println(this.currentTimeString + " FlowOneThread | " + e.getMessage());
		}
		return status;
	}

	private String httpRead(HttpURLConnection connection, byte[] requestSoap) throws IOException {
		this.currentTimeString = this.format.format(new Date());
		java.io.OutputStream out = null;
		out = connection.getOutputStream();
		BufferedReader in = null;
		String response = "404";

		// System.out.println("[BUILD] - Envio SOAP REQUEST");
		try {
			out.write(requestSoap);
			out.close();
			// System.out.println("[BUILD] - READ RESPONSE");
			int responseCode = connection.getResponseCode();
			// System.out.println("[BUILD] - Comprobar -");
			String responseString = "";
			String outputString = "";
			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
				in = new BufferedReader(isr);
				while ((responseString = in.readLine()) != null) {
					outputString += responseString;
				}
				response = outputString;
			} else {
				// System.out.println("[BUILD] - httpRead -MAL-");
				InputStreamReader isr = new InputStreamReader(connection.getErrorStream(), "UTF-8");
				// System.out.println("[BUILD] - httpRead -MAL2-");
				in = new BufferedReader(isr);
				// System.out.println("[BUILD] - httpRead -MAL3-");
				while ((responseString = in.readLine()) != null) {
					outputString += responseString;
				}
				response = outputString;

			} // if-else
		} finally {
			try {
				if (in != null) {
					System.out.println(this.currentTimeString + " FlowOneThread | Close connection");
					in.close();
				}
			} catch (Exception e) {
				System.out.println(this.currentTimeString + " FlowOneThread | Error in close connection");
			}
		}
		return response;

	}// httpRead

	private String createRequest() {
		this.currentTimeString = this.format.format(new Date());
		String request = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ins=\"http://soa.comptel.com/2011/02/instantlink\">\n" //
				+ "<soap:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\"> \n" //
				+ "<wsa:ReplyTo>\n" //
				+ "<wsa:Address>" + this.endpointCallback + "</wsa:Address> \n" //
				+ "</wsa:ReplyTo>\n" //
				+ "</soap:Header>\n" + "<soap:Body>\n" + "<ins:CreateRequest>\n" + "     <ins:RequestHeader>\n"
				+ "    	<ins:NeType>CATALOG</ins:NeType>\n" + "      </ins:RequestHeader>\n"
				+ "      	<ins:RequestParameters>\n"
				+ "			<ins:Parameter name=\"SO1_SERVICENAME\" value=\"PREPAID_HRD\"/>\n"
				+ "			<ins:Parameter name=\"SO1_ACTIONCODE\" value=\"CREATE\"/>\n"
				+ "			<ins:Parameter name=\"IMSI\" value=\"" + this.imsi + "\"/>\n"
				+ "			<ins:Parameter name=\"MSISDN\" value=\"" + this.msisdn + "\"/>\n"
				+ "			<ins:Parameter name=\"PROFILE\" value=\"PREPAID\"/>\n"
				+ "			<ins:Parameter name=\"KIND\" value=\"2\"/>\n"
				+ "			<ins:Parameter name=\"KI\" value=\"" + this.ki + "\"/>\n"
				+ "			<ins:Parameter name=\"FSETIND\" value=\"2\"/>\n"
				+ "			<ins:Parameter name=\"PREPAID_TYPE\" value=\"OCS\"/>\n" 
				+ "			<ins:Parameter name=\"ReqUser\" value=\"bss\"/>\n"
				+ "		</ins:RequestParameters>\n"
				+ "	</ins:CreateRequest>\n" + "</soap:Body>\n" + "</soap:Envelope>";
		
		this.request = request;
		System.out.println (this.currentTimeString + "FlowOneThread | Request: ");
		System.out.println (request.replace("\n", ""));
		return request;
	}

	@Override
	public void run() {
		MeasureUtil.measureWithException(KPIS.CreatePrepaidRSA.getName(), measure->{
			this.currentTimeString = this.format.format(new Date());
			System.out.println(this.currentTimeString + " FlowOneThread | Thread begining ");
			System.out.println(this.currentTimeString + " FlowOneThread | Thread Invocacion to FlowOne Request");
	
			Integer status = 404;
			Integer index = 0;
			System.out.println(this.currentTimeString + " FlowOneThread | Trying connection with: " + endpoints[index]);
	
			try {
				status = sentRequest(this.endpoints[index]);
				
				if ((status != 0) && (status != 9)) { //KPi 0 � 9 es OK
					markKpiError(status, measure);
					System.out.println(this.currentTimeString + " FlowOneThread | Mark Failed: " + status);
				}
			} catch (Exception e) {
				System.out.println(this.currentTimeString + " FlowOneThread | " + e.getMessage());
				System.out.println(this.currentTimeString + " FlowOneThread | Error on first request");
				//Se marca el kpi como error 
				markKpiError(status, measure);
				System.out.println(currentTimeString + "-  FlowOneThread: mark FAILED: " + status);
				connect = false;
			}
			
			System.out.println(this.currentTimeString + " FlowOneThread | Validation of response");
	
			this.retries--;
	
			// MANEJO DE REINTENTOS
			while (status == 1 && retries > 0) { 	// Status==1 only temporary error
				try {
					Thread.sleep(this.retryDelay);
				} catch (InterruptedException e) {
					System.out.println(this.currentTimeString + " FlowOneThread | " + e.getMessage());
					System.out.println(this.currentTimeString + " FlowOneThread | Exception Delay Retry");
				}
	
				if (index == endpoints.length - 1) { // seteo variable indice Endpoints Array
					index = 0; // si no hay mas opciones reintento desde la primera
				} else {
					index++;
				}
	
				try {
					System.out.println(this.currentTimeString + " FlowOneThread | Retries left: " + retries);
					System.out.println(this.currentTimeString + " FlowOneThread | Trying connection with: " + endpoints[i]);
					status = sentRequest(this.endpoints[index]);
				} catch (Exception e) {
					System.out.println(this.currentTimeString + " FlowOneThread | " + e.getMessage());
					System.out.println(this.currentTimeString + " FlowOneThread | Error on request state during retries");
					//Se marca el kpi como error 
					markKpiError(status, measure);
					System.out.println(currentTimeString + "-  FlowOneThread: mark FAILED: " + status);
					this.connect = false;
				}
				retries--;
			}
	
			GeneralBodyResponse gbr;
			if (connect)
				gbr = buildResponse(status, grRq, measure);
			else
				gbr = buildResponse(null, grRq, measure);
	
			Map<String, String> parameters = getRequestParameters(gbr.getParameters().get("param01"));
	
			new Thread(new DBLoggerThread(this.asaLogs, "FlowOne", "Request create_prepaid_hrd", this.request,
					"1", "log", "", parameters.containsKey("sessionID") ? parameters.get("sessionID") : "-", msisdn, imsi,
							parameters.containsKey("iccid") ? parameters.get("iccid") : "-", "")).start();
			
			new Thread(new DBLoggerThread(this.asaLogs, "FlowOne", "Response create_prepaid_hrd", this.response,
					"1", "log", "", parameters.containsKey("sessionID") ? parameters.get("sessionID") : "-", msisdn, imsi,
							parameters.containsKey("iccid") ? parameters.get("iccid") : "-", "")).start();
	
			this.executor.executeCommandB(gbr);
			System.out.println(this.currentTimeString + " FlowOneThread | Execute commandB");
		});
	}

	private Map<String, String> getRequestParameters(String parameter) {
		Map<String, String> response = new HashMap<String, String>();

		if ((parameter != null) && (!parameter.trim().isEmpty())) {
			String[] firstSplit = parameter.split(";");
			if (firstSplit.length > 0) {
				for (String string : firstSplit) {
					String[] secondSplit = string.split("=");
					if ((secondSplit.length == 2) && (secondSplit[0] != null) && (!secondSplit[0].trim().isEmpty())
							&& (secondSplit[1] != null) && (!secondSplit[1].trim().isEmpty())
							&& (!response.containsKey(secondSplit[0].trim()))) {
						// Add the key/value pair to the map only if the key does not be in it
						response.put(secondSplit[0].trim(), secondSplit[1].trim());
					}
				}
			}
		}
		return response;
	}

	private GeneralBodyResponse buildResponse(Integer status, GeneralBodyRequest gbRq, MeasureItem<?> measure) {
		this.currentTimeString = this.format.format(new Date());
		GeneralBodyResponse gbRe;
		String resultCode;
		String resultDescription;
		Map<String, String> resultData = new HashMap<String, String>();
		
		Map<String, String> parameters = getRequestParameters(gbRq.getParameters().get("param01"));
		

		

		if (status != null) {
			System.out.println(this.currentTimeString + " FlowOneThread | BuildResponse");
			resultCode = Integer.toString(status);
			resultDescription = "";
			// resultData = new HashMap<String, String>();

			resultData.put(ERROR_CODE, resultCode);
			resultData.put(ERROR_MESSAGE, resultDescription);

			if (resultCode.equals("9"))
				resultCode = "0";

			if (resultCode.equalsIgnoreCase("0")) {
				measure.mark(MarkType.OK);
				System.out.println(currentTimeString + "-  FlowOneThread: mark OK");
			} else {
				//Se marca el kpi como error 
				markKpiError(Integer.valueOf(resultCode), measure);
				System.out.println(currentTimeString + "-  FlowOneThread: mark FAILED: " + resultCode);
			}
					
			gbRe = new GeneralBodyResponse(gbRq.getServiceInfoServiceName(), gbRq.getServiceInfoOperationName(),
					gbRq.getServiceInfoUrl(), gbRq.getParameters(), resultCode, resultDescription, resultData);
		} else {
			System.out.println(this.currentTimeString + " FlowOneThread | BuildResponse with error connection");
			gbRe = new GeneralBodyResponse(gbRq.getServiceInfoServiceName(), gbRq.getServiceInfoOperationName(),
					gbRq.getServiceInfoUrl(), gbRq.getParameters(), "500", "No connection with endpoints", resultData);
			//Se marca el kpi como error 
			measure.markSpecificError("500");
		}
		return gbRe;
	}

	private void markKpiError(Integer status, MeasureItem<?> measure) {
		//Se mapean los errores de FO como 10N, donde N es el error en si (status), pues hay mismo nros de error para distintas operaciones de FO
		int aux = status.intValue();
		if (aux != 404)
			aux = viewError + aux;
		measure.markSpecificError(String.valueOf(aux));
	}
}

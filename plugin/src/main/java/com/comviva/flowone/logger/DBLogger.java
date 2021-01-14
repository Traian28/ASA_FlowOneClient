package main.java.com.comviva.flowone.logger;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.StringReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.NodeList;
//import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;


import ats.dbLogger.sources.LoggerPortBindingStub;
import ats.dbLogger.sources.LoggerServiceLocator;
import ats.dbLogger.sources.WsAckRsp;

public class DBLogger {
	
	private LoggerServiceLocator service;
	private LoggerPortBindingStub stub;
	private static final String DEFAULT_SERVICE_PORT     = "LoggerPort";
	private static final String DEFAULT_SERVICE_TIMEOUT  = "8000";
	private static final String DEFAULT_SERVICE_WSDL     = "http://10.232.15.123:8081/ASA_Orchestrator/LoggerService?wsdl";
	private static final String DEFAULT_SERVICE          = "http://service.orchestrator.asa.ats_connection.com/";
	private String instanceID;
	private String module;
	private String currentTimeString;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	/**
	 * Constructor que instancia el webservice de ASALogs
	 */
	public DBLogger () {
		this.currentTimeString = this.format.format(new Date());
		service = new LoggerServiceLocator();

		try {
			service.setEndpointAddress(DEFAULT_SERVICE_PORT, DEFAULT_SERVICE);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			System.out.println("Logger FlowOne | "+e.getMessage());
		}
		
		QName qName = new QName(DEFAULT_SERVICE, DEFAULT_SERVICE_PORT);
		try {
			stub  = (LoggerPortBindingStub) service.getPort(qName, LoggerPortBindingStub.class);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			System.out.println("Logger FlowOne | "+e.getMessage());
		}
		
		((org.apache.axis.client.Stub) stub).setTimeout(Integer.valueOf(DEFAULT_SERVICE_TIMEOUT).intValue());
		
	}
	
	/**
	 * Constructor indicando el port y el webservice
	 * @param port
	 * @param address
	 */
	public DBLogger (String port, String address, String wsdl) {
		this.currentTimeString = this.format.format(new Date());
		service = new LoggerServiceLocator();

		try {
			service.setEndpointAddress(port, wsdl);
		} catch (ServiceException e) {
			System.out.println("Logger FlowOne | "+e.getMessage());
		}
		
		QName qName = new QName(address, port);
		try {
			stub  = (LoggerPortBindingStub) service.getPort(qName, LoggerPortBindingStub.class);
		} catch (ServiceException e) {
			System.out.println("Logger FlowOne | "+e.getMessage());
		}
		
		this.instanceID = null;
		this.module = null;
		
		((org.apache.axis.client.Stub) stub).setTimeout(Integer.valueOf(DEFAULT_SERVICE_TIMEOUT).intValue());
		
	}
	
	public LoggerPortBindingStub getService() {
		return this.stub;
	}
	
	public void setEndPoint(String port, String address) {
		try {
			service.setEndpointAddress(port, address);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			System.out.println("Logger FlowOne | "+e.getMessage());
		}
	}
	
	public void setInstanceID(String iid) {
		this.instanceID = iid;
	}
	
	public void setModule (String module) {
		this.module = module;
	}
    
	public void log(String module, String description, String details, String logLevel, String category, String logDate, String sessionId, String MSISDN, String IMSI, String ICCID, String instanceId) {
		// El parametro logDate se pasa como cadena vacia para que el asaLog registre fecha
		this.currentTimeString = this.format.format(new Date());
		try {
			WsAckRsp responseLog = stub.activityLog(this.module==null ? module : this.module, description, details, "1", "OK", null, sessionId, MSISDN, IMSI, ICCID, this.instanceID==null ? instanceId : this.instanceID );
			System.out.println(this.currentTimeString + "Logger FlowOne | Log: "+responseLog.getResultCode() + "-"+ responseLog.getResultDescription());
		} catch (Exception e) {
			System.out.println(this.currentTimeString + "Logger FlowOne | Log Fallo" + module);
		}
    }
}

//public class DBLogger {
//	
//	private String instanceID;
//	private String module;
//	private String currentTimeString;
//	private String endpoint;
//	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//	
//	/**
//	 * Constructor que instancia el webservice de ASALogs
//	 */
//	public DBLogger () {
//		this.currentTimeString = this.format.format(new Date());
//		this.instanceID = null;
//		this.module = null;
//	}
//	
//	/**
//	 * Constructor indicando el port y el webservice
//	 * @param port
//	 * @param address
//	 */
//	public DBLogger (String port, String address, String wsdl) {
//		this.currentTimeString = this.format.format(new Date());
//		this.instanceID = null;
//		this.module = null;
//		this.endpoint = wsdl;
//	}
//	
//	public void setInstanceID(String iid) {
//		this.instanceID = iid;
//	}
//	
//	public void setModule (String module) {
//		this.module = module;
//	}
//    
//	public void log(String module, String description, String details, String logLevel, String category, String logDate, String sessionId, String MSISDN, String IMSI, String ICCID, String instanceId) {
//		// El parametro logDate se pasa como cadena vacia para que el asaLog registre fecha
//		this.currentTimeString = this.format.format(new Date());
//		String request;
//		String resultDesc;
//		try {
//			System.out.println("empiezo");
//			request = createRequest(this.module==null ? module : this.module, description, details, sessionId, MSISDN, IMSI, ICCID, this.instanceID==null ? instanceId : this.instanceID );
//			System.out.println("empiezo");
//			resultDesc = sentRequest (request);
//			System.out.println(this.currentTimeString + " Logger FlowOne | Log: "+resultDesc);
//		} catch (Exception e) {
//			System.out.println(this.currentTimeString + " Logger FlowOne | Log Fallo" + module);
//			System.out.println(e.getMessage());
//		}
//    }
//
//	private String sentRequest(String request) {
//		this.currentTimeString = this.format.format(new Date());
//		String status = "Error log";
//
//		try {
//			// URL url = new URL("http://localhost:8088/InstantLink?wsdl");
//			URL url = new URL(this.endpoint);
//			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			connection.setConnectTimeout(1000);
//			byte[] buffer = request.getBytes();
//
//			connection.setRequestProperty("Content-Length", String.valueOf(buffer.length));
//			connection.setRequestProperty("Content-Type", "application/soap+xml; charset=" + "UTF-8");
//			connection.setRequestMethod("POST");
//			connection.setDoOutput(true);
//			connection.setDoInput(true);
//
//			connection.connect();
//			// startDate = new Timestamp(new Date().getTime());
//			String response = httpRead(connection, buffer);
//			System.out.println(response);
//			status = getStatus(response);
//		} catch (Exception e) {
//			System.out.println(this.currentTimeString + " FlowOneLog | Could not connect to endpoint");
//			System.out.println(this.currentTimeString + " FlowOneLog | " + e.getMessage());
//		}
//		return status;
//	}
//
//	private String getStatus(String response) {
//		this.currentTimeString = this.format.format(new Date());
//		String status = "fail Log";
//
//		Document doc = null;
//		try {
//			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
//					.parse(new InputSource(new StringReader(response)));
//
//			NodeList errNodes = doc.getElementsByTagName("resultDescription");
//		
//			status = errNodes.item(0).getTextContent();
//
//		} catch (SAXException e) {
//			System.out.println(this.currentTimeString + " FlowOneLog | Error getting Status");
//			System.out.println(this.currentTimeString + " FlowOneLog | " + e.getMessage());
//		} catch (IOException e) {
//			System.out.println(this.currentTimeString + " FlowOneLog | Error getting Status");
//			System.out.println(this.currentTimeString + " FlowOneLog | " + e.getMessage());
//		} catch (ParserConfigurationException e) {
//			System.out.println(this.currentTimeString + " FlowOneLog | Error getting Status");
//			System.out.println(this.currentTimeString + " FlowOneLog | " + e.getMessage());
//		} catch (Exception e) {
//			System.out.println(this.currentTimeString + " FlowOneLog | Error getting Status");
//			System.out.println(this.currentTimeString + " FlowOneLog | " + e.getMessage());
//		}
//		return status;
//	}
//
//	private String httpRead(HttpURLConnection connection, byte[] requestSoap) throws IOException {
//		this.currentTimeString = this.format.format(new Date());
//		java.io.OutputStream out = null;
//		out = connection.getOutputStream();
//		BufferedReader in = null;
//		String response = "404";
//
//		// System.out.println("[BUILD] - Envio SOAP REQUEST");
//		try {
//			out.write(requestSoap);
//			out.close();
//			// System.out.println("[BUILD] - READ RESPONSE");
//			int responseCode = connection.getResponseCode();
//			// System.out.println("[BUILD] - Comprobar -");
//			String responseString = "";
//			String outputString = "";
//			if (responseCode == HttpURLConnection.HTTP_OK) {
//				InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
//				in = new BufferedReader(isr);
//				while ((responseString = in.readLine()) != null) {
//					outputString += responseString;
//				}
//				response = outputString;
//			} else {
//				// System.out.println("[BUILD] - httpRead -MAL-");
//				InputStreamReader isr = new InputStreamReader(connection.getErrorStream(), "UTF-8");
//				// System.out.println("[BUILD] - httpRead -MAL2-");
//				in = new BufferedReader(isr);
//				// System.out.println("[BUILD] - httpRead -MAL3-");
//				while ((responseString = in.readLine()) != null) {
//					outputString += responseString;
//				}
//				response = outputString;
//
//			} // if-else
//		} finally {
//			try {
//				if (in != null) {
//					in.close();
//				}
//			} catch (Exception e) {
//				System.out.println(this.currentTimeString + " FlowOneLog | Error in close connection");
//			}
//		}
//		return response;
//
//	}// httpRead
//
//	private String createRequest(String module, String description, String details, String sessionId, String MSISDN, String IMSI, String ICCID, String instanceId) {
//		String request =  "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.orchestrator.asa.ats_connection.com/\">\n"
//		 				+ "<soapenv:Header/>\n"
//						+	"<soapenv:Body>\n"
//						+	"<ser:activityLog>\n"
//		         		+		"<module>"+module+"</module>\n"
//						+         "<description>"+description+"</description>\n"
//						+         "<details>"+details+"</details>\n"
//						+         "<logLevel>1</logLevel>\n"
//						+         "<category>INFO</category>\n"
//						+         "<logDate></logDate>\n"
//						+         "<sessionId>"+sessionId+"</sessionId>\n"
//						+         "<MSISDN>"+MSISDN+"</MSISDN>\n"
//						+         "<IMSI>"+IMSI+"</IMSI>\n"
//						+       "<ICCID>"+ICCID+"</ICCID>\n"
//						+        "<instanceId>"+instanceId+"</instanceId>\n"
//						+	"</ser:activityLog>\n"
//						+	"</soapenv:Body>\n"
//						+"</soapenv:Envelope>";
//		System.out.println("Cree request");
//		
//		return request;
//	}
//}
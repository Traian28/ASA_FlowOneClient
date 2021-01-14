package main.java.com.comviva.flowone.client.interfacerest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.comviva.common.*;

import main.java.com.comviva.flowone.client.CreateRequest;
import main.java.com.comviva.flowone.client.DeleteRequest;
import main.java.com.comviva.flowone.client.InstantLinkRequest;
import main.java.com.comviva.flowone.client.Parameter;
import main.java.com.comviva.flowone.client.RequestHeader;
import main.java.com.comviva.flowone.kpi.FOKpiApplication;
import main.java.com.comviva.flowone.logger.DBLogger;
import main.java.com.comviva.flowone.logger.DBLoggerThread;


public class ClientPlugin implements CommandExecutor {

	private static final String CONST_SPLIT_1 = ";";
	private static final String CONST_SPLIT_2 = "=";
	private static final int CONST_ZERO = 0;
	private static final int CONST_ONE = 1;
	private static final int CONST_TWO = 2;
	private static final int CONST_THOUSAND = 1000;
	private static final String CONST_PARAM_1 = "param01";
	private static final String CONST_PARAM_2 = "param02";
	private static final String CONST_PARAM_3 = "param03";
	private static final String CONST_RETRIES = "retryExtSyst";
	private static final String CONST_TIMEOUT_MS = "wsTimeout_ms";
	private static final String CONST_TIMEOUT_SEG = "wsTimeout_seg";
	private static final String ERROR_CODE = "errorCode";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String CONST_MODULE = "asa_logger_module";
	private static final String CONST_INSTANCE_ID = "asa_logger_instance";
	private static final String CONST_ADDRESS = "address";
	private static final String CONST_LOGGER_WSDL = "logger_endpoint";
	private static final String CONST_LOGGER_SRVC = "logger_service";
	private static final String CONST_LOGGER_PORT = "logger_port";
	private static final String DEFAULT_SERVICE_WSDL = "http://10.232.15.123:8081/ASA_Orchestrator/LoggerService?wsdl";
	private static final String DEFAULT_SERVICE = "http://service.orchestrator.asa.ats_connection.com/";
	private static final String DEFAULT_SERVICE_PORT = "LoggerPort";
	
	private DBLogger asaLogs;
	private String instanceID;
	private String endpointAddress;
	private String currentTimeString;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private String module;

	/** Request de FLOWONE Client */
	CreateRequest createRequestFlowOne;
	/** Request de FLOWONE Client */
	DeleteRequest deleteRequestFlowOne;

	/** Medicion de Kpi */
	private FOKpiApplication measureFO;
	
	public CreateRequest getCreateRequestFlowOneClient() {
		return createRequestFlowOne;
	}

	public DeleteRequest getDeleteRequestFlowOneClient() {
		return deleteRequestFlowOne;
	}

	public void setCreateRequestFlowOneClient(CreateRequest requestFO) {
		this.createRequestFlowOne = requestFO;
	}

	public void setDeleteRequestFlowOneClient(DeleteRequest requestFO) {
		this.deleteRequestFlowOne = requestFO;
	}

	public FOKpiApplication getMeasureFO() {
		return measureFO;
	}

	public void setMeasureFO(FOKpiApplication measureFO) {
		this.measureFO = measureFO;
	}

	public ClientPlugin() {
		super();
		this.currentTimeString = this.format.format(new Date());
		System.out.println(this.currentTimeString + " FlowOnePlugin | Start plugin");
	}

	@Override
	public GeneralBodyResponse executeCommandA(GeneralBodyRequest gbRq, CommandExecutor ce, Map<String, String> map) {
		this.currentTimeString = this.format.format(new Date());
		System.out.println(this.currentTimeString + " FlowOnePlugin | execute commandA");

		this.asaLogs = new DBLogger(map.get(CONST_LOGGER_PORT), map.get(CONST_LOGGER_SRVC), map.get(CONST_LOGGER_WSDL));
		this.instanceID 	 = map.get(CONST_INSTANCE_ID);
		this.module 	 = map.get("asa_logger_module");
		
		this.asaLogs.setInstanceID(instanceID);
		this.asaLogs.setModule(this.module);
		String[] createValues = new String[3]; // add imsi, msisdn and ki for createRequest
		String[] deleteValues = new String[2]; // add imsi, msisdn for deleteREquest
		String[] actdeacValues = new String[5];
		

		if (gbRq.getServiceInfoOperationName().equals("CREATE")) {
			createValues = getCreateValues(gbRq);
		} else if (gbRq.getServiceInfoOperationName().equals("DELETE")) {
				deleteValues = getDeleteValues(gbRq);
		} else if (gbRq.getServiceInfoOperationName().equals("CREATE_AUC")
				|| gbRq.getServiceInfoOperationName().equals("ACTIVATE_PREPAID_HRD")
				|| gbRq.getServiceInfoOperationName().equals("DEACTIVATE_PREPAID_HRD")
				|| gbRq.getServiceInfoOperationName().equals("DEACTIVATE")) {
			actdeacValues = parseActDeactRequest(gbRq);
		} 
		
		GeneralBodyResponse gbRe = sendAcknowledge(gbRq);
		System.out.println(this.currentTimeString + " FlowOnePlugin | Send acknowledge");

		System.out.println(this.currentTimeString + " FlowOnePlugin | Start Request");

		Integer retry = Integer.parseInt(gbRq.getParameters().get(CONST_RETRIES));

		this.endpointAddress = map.get(CONST_ADDRESS);

		if (gbRq.getServiceInfoOperationName().equals("CREATE")) {
			FlowOneCreateThread foCreateThread = new FlowOneCreateThread(ce, gbRq, retry, asaLogs, endpointAddress, createValues[0], createValues[1], createValues[2]);
			foCreateThread.setMeasureFO(getMeasureFO());
			(new Thread(foCreateThread)).start();
		} else if (gbRq.getServiceInfoOperationName().equals("DELETE")) {
			FlowOneDeleteThread foOneDeleteThread = new FlowOneDeleteThread(ce, gbRq, retry, asaLogs, endpointAddress, deleteValues[0], deleteValues[1]); 
			foOneDeleteThread.setMeasureFO(getMeasureFO());
			(new Thread(foOneDeleteThread)).start();
		} else if (gbRq.getServiceInfoOperationName().equals("CREATE")
				|| gbRq.getServiceInfoOperationName().equals("CREATE_AUC")
				|| gbRq.getServiceInfoOperationName().equals("ACTIVATE_PREPAID_HRD")
				|| gbRq.getServiceInfoOperationName().equals("DEACTIVATE_PREPAID_HRD")
				|| gbRq.getServiceInfoOperationName().equals("DEACTIVATE")) {
			FlowOneActvDctvThread foActvDctvThread = new FlowOneActvDctvThread(ce, gbRq, retry, this.asaLogs, endpointAddress, actdeacValues[0], actdeacValues[1], actdeacValues[2], actdeacValues[3], actdeacValues[4]);
			foActvDctvThread.setMeasureFO(getMeasureFO());
			(new Thread(foActvDctvThread)).start();
		}

		return gbRe;
	}

	@Override
	public GeneralBodyResponse executeCommandB(GeneralBodyResponse gbRe) {
		// just to compile
		// Used by ServerSide
		this.currentTimeString = this.format.format(new Date());
		System.out.println(this.currentTimeString + " FlowOnePlugin | " + gbRe.toString());
		return gbRe;
	}

	private Map<String, String> getRequestParameters(String parameter) {
		Map<String, String> response = new HashMap<>();
		if (parameter != null && !parameter.trim().isEmpty()) {
			String[] firstSplit = parameter.split(";");
			if (firstSplit.length > 0)
				for (String string : firstSplit) {
					String[] secondSplit = string.split("=");
					if (secondSplit.length == 2 && secondSplit[0] != null &&

							!secondSplit[0].trim().isEmpty() && secondSplit[1] != null &&

							!secondSplit[1].trim().isEmpty() && !response.containsKey(secondSplit[0].trim()))
						response.put(secondSplit[0].trim(), secondSplit[1].trim());
				}
		}
		return response;
	}
	
	private String[] getCreateValues(GeneralBodyRequest gbr) {
		this.currentTimeString = this.format.format(new Date());
		String[] result = new String[3];
		
		Map<String, String> parameters1 = getRequestParameters((String) gbr.getParameters().get("param01"));
		Map<String, String> parameters2 = getRequestParameters((String) gbr.getParameters().get("param02"));

		String imsi = parameters1.containsKey("imsi_real") ? parameters1.get("imsi_real") : "-";
		String msisdn = parameters1.containsKey("msisdn_real") ? parameters1.get("msisdn_real") : "-";
		String ki = parameters2.containsKey("ki") ? parameters2.get("ki") : "-";
		
		System.out.println("<SO1_SERVICENAME>PREPAID_HRD<SO1_SERVICENAME>");

		System.out.println("<SO1_ACTIONCODE>" + gbr.getServiceInfoOperationName() + "<SO1_ACTIONCODE>");

		System.out.println("<IMSI>" + imsi + "<IMSI>");

		System.out.println("<MSISDN>" + msisdn + "<MSISDN>");

		System.out.println("<PROFILE>PREPAID<PROFILE>");

		System.out.println("<KIND>2<KIND>");

		System.out.println("<KI>" + ki + "<KI>");

		System.out.println("<FSETIND>2<FSETIND>");

		System.out.println("<PREPAID_TYPE>OCS<PREPAID_TYPE>");

		System.out.println("<ReqUser>bss<ReqUser>");
		
		result[0] = imsi;
		result[1] = msisdn;
		result[2] = ki;
		
		return result;
	}
	
	private String[] getDeleteValues(GeneralBodyRequest gbr) {
		this.currentTimeString = this.format.format(new Date());
		String[] result = new String[2];
		
		Map<String, String> parameters1 = getRequestParameters((String) gbr.getParameters().get("param01"));
		// Map<String, String> parameters2 = getRequestParameters((String)
		// gbr.getParameters().get("param02"));

		String imsi = parameters1.containsKey("imsi_real") ? parameters1.get("imsi_real") : "-";
		String msisdn = parameters1.containsKey("msisdn_real") ? parameters1.get("msisdn_real") : "-";
		
		System.out.println("<SO1_SERVICENAME>PREPAID_HRD<SO1_SERVICENAME>");

		System.out.println("<SO1_ACTIONCODE>" + gbr.getServiceInfoOperationName() + "<SO1_ACTIONCODE>");

		System.out.println("<IMSI>" + imsi + "<IMSI>");

		System.out.println("<MSISDN>" + msisdn + "<MSISDN>");
		
		result[0] = imsi;
		result[1] = msisdn;
		
		return result;
	}

	private String[] parseActDeactRequest(GeneralBodyRequest gbr) {
		this.currentTimeString = this.format.format(new Date());

		System.out.println(this.currentTimeString + " FlowOnePlugin | Creating Request");

		Map<String, String> parameters1 = getRequestParameters((String) gbr.getParameters().get("param01"));
		Map<String, String> parameters2 = getRequestParameters((String) gbr.getParameters().get("param02"));

		String operationName = gbr.getServiceInfoOperationName();
		if (operationName.equals("ACTIVATE_PREPAID_HRD"))
			operationName = "ACTIVATE";
		else if (operationName.equals("DEACTIVATE_PREPAID_HRD"))
			operationName = "DEACTIVATE";
		
		String imsi = parameters1.containsKey("imsi_real") ? parameters1.get("imsi_real") : "-";
		String msisdn = parameters1.containsKey("msisdn_real") ? parameters1.get("msisdn_real") : "-";
		String ki = parameters2.containsKey("encrypted_ki") ? parameters2.get("encrypted_ki") : "-";
		String kind = parameters2.containsKey("transport_key_index") ? parameters2.get("transport_key_index") : "-";
		
		String [] response = new String[5];
		response [0] = imsi;
		response [1] = msisdn;
		response [2] = ki;
		response [3] = kind;
		response [4] = operationName;
		
		return response;
		
	}

	private CreateRequest parseCreateRequest(GeneralBodyRequest gbr) {
		this.currentTimeString = this.format.format(new Date());
		CreateRequest request = new CreateRequest();

		System.out.println(this.currentTimeString + " FlowOnePlugin | Creating Request");
		// Map<String, String> parameters3 =
		// getRequestParameters(gbr.getParameters().get(CONST_PARAM_3));

		Map<String, String> parameters1 = getRequestParameters((String) gbr.getParameters().get("param01"));
		Map<String, String> parameters2 = getRequestParameters((String) gbr.getParameters().get("param02"));

		String operationName = (parameters1.containsKey("serviceInfoOperationName")
				? parameters1.get("serviceInfoOperationName")
				: "-");
		String imsi = parameters1.containsKey("imsi_real") ? parameters1.get("imsi_real") : "-";
		String msisdn = parameters1.containsKey("msisdn_real") ? parameters1.get("msisdn_real") : "-";
		String ki = parameters2.containsKey("encrypted_ki") ? parameters2.get("encrypted_ki") : "-";
		String kind = parameters2.containsKey("transport_key_index") ? parameters2.get("transport_key_index") : "-";

		RequestHeader rh = new RequestHeader();
		rh.setNeType("CATALOG");

		if (gbr.getServiceInfoOperationName().equals("ACTIVATE_PREPAID_HRD")) {
			// imsi = parameters1.containsKey("imsi_real") ? parameters1.get("imsi_real") :
			// "-";
			// parameters
			Parameter p1 = new Parameter();
			p1.setName("SO1_SERVICENAME");
			p1.setValue("PREPAID_HRD"); // PREPAID_HRD
			System.out.println("<" + p1.getName() + ">" + p1.getValue() + "<" + p1.getName() + ">");

			Parameter p2 = new Parameter();
			p2.setName("SO1_ACTIONCODE");
			p2.setValue("ACTIVATE");
			System.out.println("<" + p2.getName() + ">" + p2.getValue() + "<" + p2.getName() + ">");
			// p2.setValue(parameters1.containsKey("serviceInfoOperationName") ?
			// parameters1.get("serviceInfoOperationName") : "-"); //ACTIVATE

			Parameter p3 = new Parameter();
			p3.setName("IMSI");
			p3.setValue(imsi);
			System.out.println("<" + p3.getName() + ">" + p3.getValue() + "<" + p3.getName() + ">");

			Parameter p4 = new Parameter();
			p4.setName("MSISDN");
			p4.setValue(msisdn);
			System.out.println("<" + p4.getName() + ">" + p4.getValue() + "<" + p4.getName() + ">");

			Parameter p5 = new Parameter();
			p5.setName("PROFILE");
			p5.setValue("PREPAID"); // obtenido de Fluxos_HRD_FlowOne_v3
			System.out.println("<" + p5.getName() + ">" + p5.getValue() + "<" + p5.getName() + ">");

			Parameter p6 = new Parameter();
			p6.setName("KIND");
			p6.setValue(parameters2.containsKey("transport_key_index") ? parameters2.get("transport_key_index") : "-");
			System.out.println("<" + p6.getName() + ">" + p6.getValue() + "<" + p6.getName() + ">");

			Parameter p7 = new Parameter();
			p7.setName("KI");
			p7.setValue(parameters2.containsKey("encrypted_ki") ? parameters2.get("encrypted_ki") : "-");
			System.out.println("<" + p7.getName() + ">" + p7.getValue() + "<" + p7.getName() + ">");

			Parameter p8 = new Parameter();
			p8.setName("FSETIND");
			p8.setValue("2"); // valor obtenido de planilla FlowOneIntegracaoASA
			System.out.println("<" + p8.getName() + ">" + p8.getValue() + "<" + p8.getName() + ">");

			Parameter p9 = new Parameter();
			p9.setName("PREPAID_TYPE");
			p9.setValue("OCS"); // allways OCS
			System.out.println("<" + p9.getName() + ">" + p9.getValue() + "<" + p9.getName() + ">");

			Parameter p10 = new Parameter();
			p10.setName("ReqUser");
			p10.setValue("bss"); // allways bss
			System.out.println("<" + p10.getName() + ">" + p10.getValue() + "<" + p10.getName() + ">");

			// cargo los parameters en el request parameters
			InstantLinkRequest.RequestParameters rp = new InstantLinkRequest.RequestParameters();
			rp.getParameter().add(p1);
			rp.getParameter().add(p2);
			rp.getParameter().add(p3);
			rp.getParameter().add(p4);
			rp.getParameter().add(p5);
			rp.getParameter().add(p6);
			rp.getParameter().add(p7);
			rp.getParameter().add(p8);
			rp.getParameter().add(p9);
			rp.getParameter().add(p10);

			request.setRequestHeader(rh);
			request.setRequestParameters(rp);

		} else if (gbr.getServiceInfoOperationName().equals("CREATE_AUC")) {
			Parameter p1 = new Parameter();
			p1.setName("SO1_SERVICENAME");
			p1.setValue("CREATE_AUC_HRD"); // PREPAID_HRD
			System.out.println("<" + p1.getName() + ">" + p1.getValue() + "<" + p1.getName() + ">");

			Parameter p2 = new Parameter();
			p2.setName("SO1_ACTIONCODE");
			p2.setValue("CREATE");
			System.out.println("<" + p2.getName() + ">" + p2.getValue() + "<" + p2.getName() + ">");

			Parameter p3 = new Parameter();
			p3.setName("IMSI");
			p3.setValue(imsi);

			Parameter p6 = new Parameter();
			p6.setName("KIND");
			p6.setValue("2"); // valor obtenido de planilla FlowOneIntegracaoASA
			System.out.println("<" + p6.getName() + ">" + p6.getValue() + "<" + p6.getName() + ">");

			Parameter p7 = new Parameter();
			p7.setName("KI");
			p7.setValue(parameters2.containsKey("ki") ? parameters2.get("ki") : "-");
			System.out.println("<" + p7.getName() + ">" + p7.getValue() + "<" + p7.getName() + ">");

			Parameter p8 = new Parameter();
			p8.setName("FSETIND");
			p8.setValue("2"); // valor obtenido de planilla FlowOneIntegracaoASA
			System.out.println("<" + p8.getName() + ">" + p8.getValue() + "<" + p8.getName() + ">");

			// cargo los parameters en el request parameters
			InstantLinkRequest.RequestParameters rp = new InstantLinkRequest.RequestParameters();
			rp.getParameter().add(p1);
			rp.getParameter().add(p2);
			rp.getParameter().add(p3);
			rp.getParameter().add(p6);
			rp.getParameter().add(p7);
			rp.getParameter().add(p8);

			request.setRequestHeader(rh);
			request.setRequestParameters(rp);

		} else if (gbr.getServiceInfoOperationName().equals("DEACTIVATE_PREPAID_HRD") || gbr.getServiceInfoOperationName().equals("DEACTIVATE")) {
			Parameter p1 = new Parameter();
			p1.setName("SO1_SERVICENAME");
			// p1.setValue(parameters.containsKey("serviceInfoServiceName") ?
			// parameters.get("serviceInfoServiceName") : "-"); //PREPAID_HRD
			p1.setValue("PREPAID_HRD"); // PREPAID_HRD
			System.out.println("<" + p1.getName() + ">" + p1.getValue() + "<" + p1.getName() + ">");

			Parameter p2 = new Parameter();
			p2.setName("SO1_ACTIONCODE");
			p2.setValue("DEACTIVATE");
			System.out.println("<" + p2.getName() + ">" + p2.getValue() + "<" + p2.getName() + ">");
			// p2.setValue(parameters1.containsKey("serviceInfoOperationName") ?
			// parameters1.get("serviceInfoOperationName") : "-"); //ACTIVATE

			Parameter p3 = new Parameter();
			p3.setName("IMSI");
			p3.setValue(imsi);
			System.out.println("<" + p3.getName() + ">" + p3.getValue() + "<" + p3.getName() + ">");

			Parameter p4 = new Parameter();
			p4.setName("MSISDN");
			p4.setValue(msisdn);
			System.out.println("<" + p4.getName() + ">" + p4.getValue() + "<" + p4.getName() + ">");

			Parameter p5 = new Parameter();
			p5.setName("PROFILE");
			p5.setValue("PREPAID"); // obtenido de Fluxos_HRD_FlowOne_v3
			System.out.println("<" + p5.getName() + ">" + p5.getValue() + "<" + p5.getName() + ">");

			Parameter p6 = new Parameter();
			p6.setName("KIND");
			p6.setValue(parameters2.containsKey("transport_key_index") ? parameters2.get("transport_key_index") : "-");
			System.out.println("<" + p6.getName() + ">" + p6.getValue() + "<" + p6.getName() + ">");

			Parameter p7 = new Parameter();
			p7.setName("KI");
			p7.setValue(parameters2.containsKey("encrypted_ki") ? parameters2.get("encrypted_ki") : "-");
			System.out.println("<" + p7.getName() + ">" + p7.getValue() + "<" + p7.getName() + ">");

			Parameter p8 = new Parameter();
			p8.setName("FSETIND");
			p8.setValue("2"); // valor obtenido de planilla FlowOneIntegracaoASA
			System.out.println("<" + p8.getName() + ">" + p8.getValue() + "<" + p8.getName() + ">");

			Parameter p9 = new Parameter();
			p9.setName("PREPAID_TYPE");
			p9.setValue("OCS"); // allways OCS
			System.out.println("<" + p9.getName() + ">" + p9.getValue() + "<" + p9.getName() + ">");

			Parameter p10 = new Parameter();
			p10.setName("ReqUser");
			p10.setValue("bss"); // allways bss
			System.out.println("<" + p10.getName() + ">" + p10.getValue() + "<" + p10.getName() + ">");

			// cargo los parameters en el request parameters
			InstantLinkRequest.RequestParameters rp = new InstantLinkRequest.RequestParameters();
			rp.getParameter().add(p1);
			rp.getParameter().add(p2);
			rp.getParameter().add(p3);
			rp.getParameter().add(p4);
			rp.getParameter().add(p5);
			rp.getParameter().add(p6);
			rp.getParameter().add(p7);
			rp.getParameter().add(p8);
			rp.getParameter().add(p9);
			rp.getParameter().add(p10);

			request.setRequestHeader(rh);
			request.setRequestParameters(rp);

		}

		System.out.println(this.currentTimeString + " FlowOnePlugin | Request ready for operation: "
				+ gbr.getServiceInfoOperationName());

		// Log en ASA Log
		new Thread(new DBLoggerThread(this.asaLogs, "FlowOne", "Calling to CreateAUCRequest", "InstantLinkWebServices",
				"1", "log", "", parameters1.containsKey("sessionID") ? parameters1.get("sessionID") : "-", msisdn, imsi,
				parameters2.containsKey("iccid") ? parameters2.get("iccid") : "-", this.instanceID)).start();
		
		return request;
	}

	/**
	 * Envía un Recibi OK a la RestApi
	 * 
	 * @param gbr
	 * @return
	 */
	private GeneralBodyResponse sendAcknowledge(GeneralBodyRequest gbr) {
		String resultCode = "0";
		String resultDescription = "Ok";
		Map<String, String> resultData = new HashMap<String, String>();

		return new GeneralBodyResponse(gbr.getServiceInfoServiceName(), gbr.getServiceInfoOperationName(),
				gbr.getServiceInfoUrl(), gbr.getParameters(), resultCode, resultDescription, resultData);
	}
}
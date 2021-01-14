
package com.comviva.executor.restservice;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comviva.executor.body.GeneralBodyResponse;
import com.comviva.executor.body.RestExecutor;
import com.comviva.executor.utils.RestUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.java.com.comviva.flowone.client.interfacerest.ClientPlugin;
import main.java.com.comviva.flowone.kpi.FOKpiApplication;

@RestController
// @EnableConfigurationProperties(CustomProperties.class)
public class GeneralController {

	@Value("${spring.data.plugin.server}")
	private String URL;
	
	RestExecutor restExecutor;
	
	//Add you plugin class here
	ClientPlugin plugin = new ClientPlugin();
	/** Medicion de Kpi */
	private FOKpiApplication measureFO = FOKpiApplication.getInstance();
	
	Map<String, String> mapConfig = new HashMap<String, String>();
	CustomProperties myProperties;
	GeneralBodyResponse request;
	
	private static final String MODULE_VERSION      = "moduleVersion";
	
	private static final Logger logger = LogManager.getLogger(GeneralController.class);

	@PostMapping("${spring.data.rest.basePath}/request")
	public GeneralBodyResponse postRequest(
			@RequestBody(required = false) com.comviva.common.GeneralBodyRequest requestBody,
			@RequestParam(value = "dataJson", defaultValue = "{}") String dataJson) {

		logger.info("General Controller | Starting request");
		restExecutor = new RestExecutor(URL);

		if ((requestBody != null) && (requestBody.getServiceInfoUrl() != null)
				&& (requestBody.getServiceInfoServiceName() != null)
				&& (requestBody.getServiceInfoOperationName() != null)) {

			logger.info("General Controller | Execute postRequest");

			request = new GeneralBodyResponse();

			mapConfig = myProperties.parameters;
			
			measureFO.setMODULE_VERSION(mapConfig.get(MODULE_VERSION));
			plugin.setMeasureFO(measureFO);
			plugin.executeCommandA(requestBody, restExecutor, mapConfig);

			logger.info("General Controller | Successful Command A execution, with the following parameters: ");

			logger.info("Request Parameters: ServiceInfoUrl: " + request.getServiceInfoUrl()
					+ ", ServiceInfoServiceName: " + request.getServiceInfoServiceName()
					+ ", ServiceInfoOperationName: " + request.getServiceInfoOperationName() + ", Parameters: {"
					+ request.getParameters() + "}");

			logger.info("General Controller | MapConfig parameters: " + mapConfig);

			request.setServiceInfoUrl(requestBody.getServiceInfoUrl());
			request.setServiceInfoServiceName(requestBody.getServiceInfoServiceName());
			request.setServiceInfoOperationName(requestBody.getServiceInfoOperationName());
			request.setParameters(requestBody.getParameters());
			request.setResultDescription("Communication successful");
			request.setResultCode("0");

			logger.info("General Controller | Communication with FlowOneProcess was successful.");
		} else {
			request.setResultDescription("Bad Request");
			request.setResultCode("-1");

			if (requestBody.getServiceInfoOperationName() != null
					&& requestBody.getServiceInfoOperationName().trim().equals("")) {
				logger.warn("General Controller | Error in ServiceInfoOperationName, this parameters is null");
			}
			if (requestBody.getServiceInfoServiceName() != null
					&& requestBody.getServiceInfoServiceName().trim().equals("")) {
				logger.warn("General Controller | Error in ServiceInfoServiceName, this parameters is null");
			}
			if (requestBody.getServiceInfoUrl() != null && requestBody.getServiceInfoUrl().trim().equals("")) {
				logger.warn("General Controller | Error in ServiceInfoUrl, this parameters is null");
			}
		}
		return request;
	}
	
	Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	int errorCount = 0;
	Map<String, String> result = new HashMap<String, String>();

	public com.comviva.common.GeneralBodyResponse postResponse(
			com.comviva.common.GeneralBodyResponse arg0,
			String dataJson, String urlCallback) {
		logger.info("General Controller | Execute postResponse.");

		//logger.debug("General Controller | The URL config is: " + urlCallback);
		
		logger.info("General Controller | The JSON request is: " + gson.toJson(arg0));

		try {
			
			result = RestUtils.sendPostRequest(urlCallback, gson.toJson(arg0), 1000);
			
			if (result.get("CONST_STATUS_CODE")!= null) {
				while (errorCount < 3) {
					if (result.get("CONST_STATUS_CODE").equals("404")) {
						errorCount++;
						logger.warn("General Controller | Ocurred an error, error_retry:" + errorCount);
						// System.out.println("Ocurred an error, error_retry:" + errorCount);
						result = RestUtils.sendPostRequest(urlCallback, gson.toJson(arg0), 1000);
					} else {
						errorCount = 4;
						logger.info("General Controller | "+result);
					}
				}
			} else {
				logger.warn("General Controller | Ocurred an error on http PostResponse");
			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}

		return arg0;
	}
	
	public String getUrl() {
		return this.URL;
	}
}

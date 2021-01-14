
package com.comviva.executor.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.comviva.executor.restservice.GeneralController;

@SuppressWarnings("deprecation")
public class RestUtils {

	public static final String CONST_RESPONSE_PROTOCOL_VERSION = "CONST_PROTOCOL_VERSION";
	public static final String CONST_RESPONSE_REASON_PHRASE = "CONST_REASON_PHRASE";
	public static final String CONST_RESPONSE_STATUS_CODE = "CONST_STATUS_CODE";
	public static final String CONST_RESPONSE_JSON = "CONST_RESPONSE_JSON";
	private static final Logger logger = LogManager.getLogger(GeneralController.class);

	public static Map<String, String> sendPostRequest(String url, String jsonStr, int timeoutMilliseconds)
			throws Exception {
		Map<String, String> response = new HashMap<String, String>();
		StringBuffer strBuffer = new StringBuffer();

		final HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, timeoutMilliseconds);
		DefaultHttpClient client = new DefaultHttpClient(httpParams);
		// DefaultHttpClient client = new DefaultHttpClient();

		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type", "application/json");
		StringEntity input = new StringEntity(jsonStr);
		post.setEntity(input);
		HttpResponse responseHttp = null;
		try {
			responseHttp = client.execute(post);
		} catch (Exception e) {
			logger.debug("Error on httpPost");
			logger.debug(e.getMessage());
		}
		if ((responseHttp != null) && (responseHttp.getEntity() != null)) {

			StatusLine statusLine = responseHttp.getStatusLine();
			ProtocolVersion protocolVersion = statusLine.getProtocolVersion();
			response.put(CONST_RESPONSE_PROTOCOL_VERSION, protocolVersion.toString());
			response.put(CONST_RESPONSE_STATUS_CODE, String.valueOf(statusLine.getStatusCode()));
			response.put(CONST_RESPONSE_REASON_PHRASE, statusLine.getReasonPhrase());

			BufferedReader rd = new BufferedReader(new InputStreamReader(responseHttp.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				strBuffer.append(line);
			}

		}

		response.put(CONST_RESPONSE_JSON, strBuffer.toString());

		return response;
	}

	public static Map<String, String> sendPutRequest(String url, String jsonStr, int timeoutMilliseconds)
			throws Exception {
		Map<String, String> response = new HashMap<String, String>();
		StringBuffer strBuffer = new StringBuffer();

		final HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, timeoutMilliseconds);
		DefaultHttpClient client = new DefaultHttpClient(httpParams);
		// DefaultHttpClient client = new DefaultHttpClient();

		HttpPut put = new HttpPut(url);
		put.addHeader("Content-Type", "application/json");
		StringEntity input = new StringEntity(jsonStr);
		put.setEntity(input);
		HttpResponse responseHttp = client.execute(put);
		if ((responseHttp != null) && (responseHttp.getEntity() != null)) {

			StatusLine statusLine = responseHttp.getStatusLine();
			ProtocolVersion protocolVersion = statusLine.getProtocolVersion();
			response.put(CONST_RESPONSE_PROTOCOL_VERSION, protocolVersion.toString());
			response.put(CONST_RESPONSE_STATUS_CODE, String.valueOf(statusLine.getStatusCode()));
			response.put(CONST_RESPONSE_REASON_PHRASE, statusLine.getReasonPhrase());

			BufferedReader rd = new BufferedReader(new InputStreamReader(responseHttp.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				strBuffer.append(line);
			}
		}

		response.put(CONST_RESPONSE_JSON, strBuffer.toString());

		return response;
	}

	public static Map<String, String> sendGetRequest(String url, int timeoutMilliseconds) throws Exception {
		Map<String, String> response = new HashMap<String, String>();
		StringBuffer strBuffer = new StringBuffer();

		final HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, timeoutMilliseconds);
		DefaultHttpClient client = new DefaultHttpClient(httpParams);
		// DefaultHttpClient client = new DefaultHttpClient();

		HttpGet get = new HttpGet(url);
		HttpResponse responseHttp = client.execute(get);
		if ((responseHttp != null) && (responseHttp.getEntity() != null)) {

			StatusLine statusLine = responseHttp.getStatusLine();
			ProtocolVersion protocolVersion = statusLine.getProtocolVersion();
			response.put(CONST_RESPONSE_PROTOCOL_VERSION, protocolVersion.toString());
			response.put(CONST_RESPONSE_STATUS_CODE, String.valueOf(statusLine.getStatusCode()));
			response.put(CONST_RESPONSE_REASON_PHRASE, statusLine.getReasonPhrase());

			BufferedReader rd = new BufferedReader(new InputStreamReader(responseHttp.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				strBuffer.append(line);
			}
		}
		response.put(CONST_RESPONSE_JSON, strBuffer.toString());

		return response;
	}
}

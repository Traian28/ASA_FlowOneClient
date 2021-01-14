
package com.comviva.executor.restservice;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
// @ConfigurationProperties
//@PropertySource(value = "/dynamic_config.properties", ignoreResourceNotFound = true)
@Component
public class CustomProperties {

	private static final Logger logger = LogManager.getLogger(CustomProperties.class);

	@Value("${spring.data.plugin.parameters.1.key}")
	private String key1;

	@Value("${spring.data.plugin.parameters.2.key}")
	private String key2;

	@Value("${spring.data.plugin.parameters.3.key}")
	private String key3;

	@Value("${spring.data.plugin.parameters.4.key}")
	private String key4;

	@Value("${spring.data.plugin.parameters.5.key}")
	private String key5;

	@Value("${spring.data.plugin.parameters.6.key}")
	private String key6;
	
	@Value("${spring.data.plugin.parameters.7.key}")
	private String key7;

	@Value("${spring.data.plugin.parameters.8.key}")
	private String key8;
	
	@Value("${spring.data.plugin.parameters.1.value}")
	private String value1;

	@Value("${spring.data.plugin.parameters.2.value}")
	private String value2;

	@Value("${spring.data.plugin.parameters.3.value}")
	private String value3;

	@Value("${spring.data.plugin.parameters.4.value}")
	private String value4;

	@Value("${spring.data.plugin.parameters.5.value}")
	private String value5;

	@Value("${spring.data.plugin.parameters.6.value}")
	private String value6;
	
	@Value("${spring.data.plugin.parameters.7.value}")
	private String value7;
	
	@Value("${spring.data.plugin.parameters.8.value}")
	private String value8;
	
	@Value("${spring.data.plugin.server.name}")
	private String serverClass;
	
	@Value("${spring.data.plugin.java_executor}")
	private String clientClass;
	
	@Value("${spring.data.plugin.server}")
	private String URL;
	
	public static final String[] additionalValues = new String [3];
	
	public static final Map<String, String> parameters = new HashMap<>();
	
	@Autowired
	protected void setParameters() {
		logger.debug("Configuration reading begins.");

		parameters.put(key1, value1);
		logger.debug("Key_1: " + key1 + ", parameter: " + value1);
		//System.out.println(key1 + value1);

		parameters.put(key2, value2);
		logger.debug("Key_2: " + key2 + ", parameter: " + value2);
		//System.out.println(key2 + value2);

		parameters.put(key3, value3);
		logger.debug("Key_1: " + key3 + ", parameter: " + value3);
		//System.out.println(key3 + value3);

		parameters.put(key4, value4);
		logger.debug("Key_1: " + key4 + ", parameter: " + value4);
		//System.out.println(key4 + value4);

		parameters.put(key5, value5);
		logger.debug("Key_1: " + key5 + ", parameter: " + value5);
		//System.out.println(key5 + value5);

		parameters.put(key6, value6);
		logger.debug("Key_1: " + key6 + ", parameter: " + value6);
		//System.out.println(key6 + value6);
		
		parameters.put(key7, value7);
		logger.debug("Key_1: " + key7 + ", parameter: " + value7);
		//System.out.println(key6 + value6);

		parameters.put(key8, value8);
		logger.debug("Key_8: " + key8 + ", parameter: " + value8);
		
		logger.debug("Finished reading configuration.");
	}
	
	@Autowired
	protected void setAditionalValues() {
		additionalValues[0] = serverClass;
		additionalValues[1] = URL;
		additionalValues[2] = clientClass;
	}

	public Map<String, String> getProperties() {
		return this.parameters;
	}	

	@Autowired
	protected String getUrl () {
		return this.additionalValues[0];
	}
	
	@Autowired
	protected String getServerClass() {
		return this.additionalValues[1];
	}
	
	@Autowired
	protected String getClientClass() {
		return this.additionalValues[2];
	}

}
package com.comviva.executor.body;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.comviva.executor.restservice.CustomProperties;

@RestController
public class ServerExecutor {
	
	String serverClass;
	CustomProperties myProperties;
	Map<String, String> mapConfig = new HashMap<String, String>();
	String urlCallback;
	RestExecutor restExecutor;
	
	public ServerExecutor () {
		mapConfig = myProperties.parameters;
		this.serverClass = myProperties.additionalValues[0];
		System.out.println (this.serverClass);
		mapConfig = myProperties.parameters;
		this.urlCallback = myProperties.additionalValues[1];
		
		if ( !this.serverClass.equals("-") && !this.serverClass.equals(null) && !this.serverClass.equals(""))
			startServer();
		else {
			try {
				finalize();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected void finalize() throws Throwable{
		  //System.out.println("Finalizando el Objeto");
		  super.finalize();
	}
	
	public void startServer () {
		
		//Uncomment this lines if you are deploying a server executor
		
//		restExecutor = new RestExecutor(this.urlCallback);
//		FlowOneServer wsServer = new FlowOneServer();
//		wsServer.startupWebServiceServer(mapConfig, restExecutor);
		
	}
	
}

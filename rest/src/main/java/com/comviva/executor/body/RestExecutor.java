
package com.comviva.executor.body;

import java.util.Map;

import com.comviva.common.CommandExecutor;
import com.comviva.common.GeneralBodyRequest;
import com.comviva.common.GeneralBodyResponse;
import com.comviva.executor.restservice.GeneralController;

public class RestExecutor implements CommandExecutor {

	GeneralController gralController;
	
	String urlCallback;
	
	public RestExecutor (String urlCallback) {
		this.urlCallback = urlCallback;
	}

	@Override
	public GeneralBodyResponse executeCommandA(GeneralBodyRequest arg0, CommandExecutor arg1,
			Map<String, String> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeneralBodyResponse executeCommandB(GeneralBodyResponse arg0) {

		gralController = new GeneralController();
		//GeneralController.this.postResponse(arg0, null, this.urlCallback);
		gralController.postResponse(arg0, null, this.urlCallback);

		return arg0;
	}

}

package main.java.com.comviva.flowone.client.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.junit.Test;

import com.comviva.common.GeneralBodyRequest;
import com.comviva.common.GeneralBodyResponse;
import com.mahindracomviva.cms.common.BaseApplication;
import com.mahindracomviva.cms.common.monitoring.CapacityMeasure;
import com.mahindracomviva.cms.common.monitoring.GenericMeasure;
import com.mahindracomviva.cms.common.monitoring.MeasureItem;
import com.mahindracomviva.cms.common.monitoring.MeasureUtil;
import com.mahindracomviva.cms.common.monitoring.MetaMeasure;
import com.mahindracomviva.cms.common.monitoring.ModuleStatus;
import com.mahindracomviva.cms.common.monitoring.Status;
import com.mahindracomviva.cms.common.monitoring.TrafficMeasure;
import com.mahindracomviva.cms.common.monitoring.Status.Code;

import ats.dbLogger.sources.LoggerPortBindingStub;
import ats.dbLogger.sources.LoggerServiceLocator;
import ats.dbLogger.sources.SOAPException;
import ats.dbLogger.sources.WsAckRsp;
import main.java.com.comviva.flowone.client.interfacerest.ClientPlugin;
import main.java.com.comviva.flowone.kpi.FOKpiApplication;


public class TestRequestFlowOneClientWithKpi {
	
	private ClientPlugin flowOneClient;
	private FOKpiApplication testkpi;

	public TestRequestFlowOneClientWithKpi() {
		super();
		this.flowOneClient = new ClientPlugin();
		this.testkpi = FOKpiApplication.getInstance();
	}
	/*
	@Test
	public void testKpi() {
		OmsKpiApplication omskpiApplication = new OmsKpiApplication();
	}*/
	
	@Test
	public void testRequestFlowOne() {
		try {
		System.out.println("testRequestFlowOne - Inicia Test- Hora: " + new Timestamp(System.currentTimeMillis()));	
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("param01", "sessionID=OrchBE;creationDate=\"2020-02-03\";updateDate=\"2020-01-14\";changeStateDate=-;imsi_fake=724032629999999;msisdn_fake=5511921454180;imsi_real=252525;imsi_roaming=48999;msisdn_real=614614164;iccid=1212121;loci=-;imei=-;state=-;areaCode=5498;activationType=1268;transactionID=-;orchestratorID=-;originActivation=originA;origin=origin;userType=-;cardType=-;opKey=-;opc=-;virtualNetwork=-;application=-;AuxData=-");
		parameters.put("param02", "iccid=1562;epfCode=-;networkAccessMode=-;msisdn_t=-;retryCounter=-;add=-;imei=-;category=-;blockedStatus=-;imsi_d=-;iccid=-;msisdn_d=-;pin1=1010;puk1=20200101;card_type=I234@@@@;initialCredit=0.00;productId=-;prevSstCode=-;sstCode=-;sstCodeChange=-;traceLevel=-;ki=-;transportKey=;electricalProfile=-;chipBatch=-;rechargeMix=-;masterKey=-;filIdInsert=-;filIdUpdate=-;filIdLockUnlock=-;plnCode=71236;plcCode=-;nwtCode=-;lastUpdate=-;imsiRoaming=-;origin=origin;areaCode=291;userType=11;opc=-;virtualNetwork=-;subApplication=-;cardType=-;operatorKey=-");
		parameters.put("param03", "messageID=mi.omsclient.sesion.test;operationName=CREATE;originName=origin;serviceName=InstantLinkWs;siteName=BahiaBlanca");
		parameters.put("retryExtSyst", "3");
		parameters.put("wsTimeout_seg", "8000");
		parameters.put("retryDelay", "10000");
		
		String endpoints = "http://10.46.157.218:8081/InstantLinkActivate/InstantLink?WSDL";//;http://192.168.10.11:8080/hrdSubInstallSimpFailed/mockHRDSubInstallSimp?wsdl";
		
		GeneralBodyRequest gbRq = new GeneralBodyRequest("InstantLinkWs","CREATE",endpoints, parameters);
		System.out.println("testRequestFlowOne - Llamada a executeCommandA. Hora: " + new Timestamp(System.currentTimeMillis()));	
		Map<String, String> params = new HashMap<String, String>();
		params.put("asa_logger_module", "OmsClient");
		params.put("asa_logger_instance", "WSProxy");
		params.put("origin", "OrchB");
		params.put("address", "http://localhost:9081/localHuaweiService");
		params.put("logger_endpoint", "http://10.232.15.123:8081/ASA_Orchestrator/LoggerService?wsdl");
		params.put("logger_service", "http://service.orchestrator.asa.ats_connection.com");
		params.put("logger_port", "LoggerPort");
		
		flowOneClient.setMeasureFO(this.testkpi);
		GeneralBodyResponse gbRe = flowOneClient.executeCommandA(gbRq, this.flowOneClient, params);
		
		//assertEquals(gbRe.getResultCode(), "1");
			System.out.println("testRequestFlowOne: Result después de executeCommandA " + gbRe.getResultCode() + " - Description: " + gbRe.getResultDescription());
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("testRequestFlowOne: Result Fin Test " + gbRe.getResultCode() + " - Description: " + gbRe.getResultDescription()+" Hora: " + new Timestamp(System.currentTimeMillis()));
		
		} catch (Exception e) {
			System.out.println("testRequestFlowOne: Falla" + e.getMessage()); 
		}

	}

}

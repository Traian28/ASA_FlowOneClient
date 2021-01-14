package main.java.com.comviva.flowone.logger;

public class DBLoggerThread implements Runnable{
	
	DBLogger asaLogs;
	String module;
	String description;
	String details;
	String logLevel;
	String category;
	String logDate;
	String sessionId;
	String MSISDN;
	String IMSI;
	String ICCID;
	String instanceId;
	
	public DBLoggerThread (DBLogger asaLogs, String module, String description, String details, String logLevel, String category, String logDate, String sessionId, String MSISDN, String IMSI, String ICCID, String instanceId) {
		this.asaLogs = asaLogs;
		this.module = module;
		this.description = description;
		this.details = details;
		this.logLevel = logLevel;
		this.category = category;
		this.logDate = logDate;
		this.sessionId = sessionId;
		this.MSISDN = MSISDN;
		this.IMSI = IMSI;
		this.ICCID = ICCID;
		this.instanceId = instanceId;
	}

	@Override
	public void run() {
		
		asaLogs.log(this.module, this.description, this.details, this.logLevel, this.category, this.logDate, this.sessionId, this.MSISDN, this.IMSI, this.ICCID, this.instanceId);
	
	}

}
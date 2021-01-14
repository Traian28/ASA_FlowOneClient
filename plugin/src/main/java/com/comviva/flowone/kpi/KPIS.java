package main.java.com.comviva.flowone.kpi;

public enum KPIS {
	
	//TPS("Tps"),
	CreatePrepaidRSA("CreatePrepaidRSA"),
	DeletetePrepaidRSA("DeletetePrepaidRSA"),
	ACTIVATE_PREPAID_HRD("ACTIVATE_PREPAID_HRD"),
	CreateAuCRSA("CreateAuCRSA"),
	DEACTIVATE_PREPAID_HRD("DEACTIVATE_PREPAID_HRD");
	
	String name;

	private KPIS(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

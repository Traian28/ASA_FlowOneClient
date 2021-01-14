package main.java.com.comviva.flowone.kpi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.mahindracomviva.cms.common.BaseApplication;
import com.mahindracomviva.cms.common.monitoring.CapacityMeasure;
import com.mahindracomviva.cms.common.monitoring.GenericMeasure;
import com.mahindracomviva.cms.common.monitoring.KpiGeneralStatus;
import com.mahindracomviva.cms.common.monitoring.KpiStatus;
import com.mahindracomviva.cms.common.monitoring.MeasureUtil;
import com.mahindracomviva.cms.common.monitoring.MetaMeasure;
import com.mahindracomviva.cms.common.monitoring.ModuleError;
import com.mahindracomviva.cms.common.monitoring.ModuleStatus;
import com.mahindracomviva.cms.common.monitoring.Status;
import com.mahindracomviva.cms.common.monitoring.TrafficMeasure;
import com.mahindracomviva.cms.common.monitoring.Status.Code;


public class FOKpiApplication extends BaseApplication  {

	// Constant KPI
	private static final String MODULE_NAME 		= "wsFlowOne.Client";
	private String MODULE_VERSION 					= "1.4.2";
	
	private static FOKpiApplication instance = new FOKpiApplication();
	
	List<ModuleError> moduleErrorList;
	
	private String currentTimeString;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public FOKpiApplication() {
		super(true);
	}
	
	public static FOKpiApplication getInstance() {
		return instance;
	}

	public void setMODULE_VERSION(String mODULE_VERSION) {
		MODULE_VERSION = mODULE_VERSION;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return MODULE_NAME;
	}
	
	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return MODULE_VERSION;
	}
	

	//Metodos de Kpi
	@Override
	public ModuleStatus getModuleStatus() {
		final Set<CapacityMeasure> capacity  = new LinkedHashSet<>();
		final Set<MetaMeasure> meta = new LinkedHashSet<>();
		final Set<TrafficMeasure> traffic = new LinkedHashSet<>();
		final Set<GenericMeasure> generic = new LinkedHashSet<>();
		
		String maxCapacity = "500";//environment.getProperty("kpi.max.capacity");
		
		MeasureUtil.resetAccumulators(maxCapacity, capacity, meta, traffic, generic);
		
		//Cuenta errores especificos para sumarizar
		analizeStatus(capacity, meta, traffic, generic);
				
		//Determina el estado general
		KpiGeneralStatus gralStatus = MeasureUtil.getStatusFromKpi(capacity, meta, traffic, generic);
				
		//Construye los errores del modulo a partir del status general
		buildModuleErrors(gralStatus);
				
		//Saca el estado general para informar
		KpiStatus kpiStatus = gralStatus.getGeneralStatus();
				
		Status status = new Status(MODULE_NAME, MODULE_VERSION, setCodeStatus(kpiStatus), kpiStatus.getDescription());
		
		fillEmptyKPIs(generic);
		
		return new ModuleStatus(status, gralStatus, moduleErrorList, generic, traffic, capacity, meta);	
	}

	private void analizeStatus(Set<CapacityMeasure> capacity, Set<MetaMeasure> meta, Set<TrafficMeasure> traffic, Set<GenericMeasure> generic) {
		
		int erroCount = 0, keyElemError = -1;
		
		//Contar los errores y sumarlos a Error
		Iterator itGe = generic.iterator();
		GenericMeasure genMe;
		int cantGenMe = 0;
		while (itGe.hasNext()) {
			genMe = (GenericMeasure)itGe.next();
			if (genMe.getRowId().contains("101.Count") || genMe.getRowId().contains("102.Count") ||
					genMe.getRowId().contains("103.Count") || genMe.getRowId().contains("106.Count") ||
					genMe.getRowId().contains("107.Count") || genMe.getRowId().contains("108.Count") || genMe.getRowId().contains("110.Count") || 
					genMe.getRowId().contains("206.Count") || genMe.getRowId().contains("207.Count") || genMe.getRowId().contains("208.Count") ||
					genMe.getRowId().contains("500.Count")) {
				erroCount = erroCount + Integer.valueOf(genMe.getValue());
				currentTimeString = format.format(new Date());
				System.out.println(currentTimeString + " Cant Error total es: "+erroCount);
			} else if (genMe.getRowId().contains("ERROR.Count")) 
				keyElemError = cantGenMe;
			cantGenMe++;
		}
		
		if (keyElemError != -1) {//Actualizo errores si se produjeron errores especificos
			itGe = generic.iterator();
			boolean fin = false;
			while (itGe.hasNext() && !fin) {
				genMe = (GenericMeasure)itGe.next();
				if (genMe.getRowId().contains("ERROR.Count")) { 
					genMe.setValue(String.valueOf(erroCount));
					//Update measure ERROR.Count
					generic.remove(Integer.valueOf(keyElemError));
					generic.add(genMe);
					fin = true;
					System.out.println(currentTimeString + " Cant Error total es: "+genMe.getValue());
				}
			}
		}
	}
	
	//Se asumen como 100 los errores de FO para Flujo de Preactivation 100 a 110
	//Se asumen como 200 los errores de FO para Flujo de Activation 200 a 210
	private Code setCodeStatus(KpiStatus kpi) {
		return (kpi.getStatus().equalsIgnoreCase("OK") ? Code.OK : 
		(kpi.getStatus().equalsIgnoreCase("WARNING") ? Code.WARNING : Code.ERROR));
		
	}
	
	private void buildModuleErrors(KpiGeneralStatus gralStatus) {
		// TODO Auto-generated method stub
		moduleErrorList = new ArrayList<>();
		gralStatus.getListStatus().forEach((key, value) ->
			moduleErrorList.add(new ModuleError(value.getStatus(), value.getName(), value.getDescription()))
		);
	}
	private void fillEmptyKPIs(Set<GenericMeasure> generic) {

		for (final KPIS kpiEnum : KPIS.values()) {
			final String kpi = kpiEnum.getName();
			addIfNotContainGeneric(generic, kpi + ".Total.Count");
			addIfNotContainGeneric(generic, kpi + ".Total.AvgTime");
			addIfNotContainGeneric(generic, kpi + ".Total.MinTime");
			addIfNotContainGeneric(generic, kpi + ".Total.MaxTime");
			addIfNotContainGeneric(generic, kpi + ".OK.Count");
			addIfNotContainGeneric(generic, kpi + ".OK.AvgTime");
			addIfNotContainGeneric(generic, kpi + ".OK.MaxTime");
			addIfNotContainGeneric(generic, kpi + ".OK.MinTime");
			addIfNotContainGeneric(generic, kpi + ".Error.Count");
			addIfNotContainGeneric(generic, kpi + ".Error.AvgTime");
			addIfNotContainGeneric(generic, kpi + ".Error.MaxTime");
			addIfNotContainGeneric(generic, kpi + ".Error.MinTime");
			addIfNotContainGeneric(generic, kpi + ".TimeOut.Count");
			addIfNotContainGeneric(generic, kpi + ".TimeOut.AvgTime");
			addIfNotContainGeneric(generic, kpi + ".TimeOut.MaxTime");
			addIfNotContainGeneric(generic, kpi + ".TimeOut.MinTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.101.Count");
		    addIfNotContainGeneric(generic, kpi + ".Error.101.AvgTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.101.MaxTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.101.MinTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.102.Count");
		    addIfNotContainGeneric(generic, kpi + ".Error.102.AvgTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.102.MaxTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.102.MinTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.103.Count");
		    addIfNotContainGeneric(generic, kpi + ".Error.103.AvgTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.103.MaxTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.103.MinTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.106.Count");
		    addIfNotContainGeneric(generic, kpi + ".Error.106.AvgTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.106.MaxTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.106.MinTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.107.Count");
		    addIfNotContainGeneric(generic, kpi + ".Error.107.AvgTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.107.MaxTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.107.MinTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.108.Count");
		    addIfNotContainGeneric(generic, kpi + ".Error.108.AvgTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.108.MaxTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.108.MinTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.110.Count");
		    addIfNotContainGeneric(generic, kpi + ".Error.110.AvgTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.110.MaxTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.110.MinTime");		    
		    addIfNotContainGeneric(generic, kpi + ".Error.206.Count");
		    addIfNotContainGeneric(generic, kpi + ".Error.206.AvgTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.206.axTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.206.MinTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.207.Count");
		    addIfNotContainGeneric(generic, kpi + ".Error.207.AvgTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.207.MaxTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.207.MinTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.208.Count");
		    addIfNotContainGeneric(generic, kpi + ".Error.208.AvgTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.208.MaxTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.208.MinTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.500.Count");
		    addIfNotContainGeneric(generic, kpi + ".Error.500.AvgTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.500.MaxTime");
		    addIfNotContainGeneric(generic, kpi + ".Error.500.MinTime");		    
		}
	}

	private void addIfNotContainGeneric(Set<GenericMeasure> generic, String kpiName) {
		String unit = (kpiName.endsWith("Count") || kpiName.endsWith("Error.Count") || kpiName.endsWith("OK.Count"))
				? "qty"
				: "ms";
		if (!generic.contains(kpiName)) {
			generic.add(new GenericMeasure(kpiName, "0", unit));
		}
	}

}

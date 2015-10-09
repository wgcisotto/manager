package br.com.wgengenharia.manager.report.factory;

import br.com.wgengenharia.manager.model.StudentPayments;
import br.com.wgengenharia.manager.report.facade.ManagerReportFacade;
import br.com.wgengenharia.manager.report.model.ManagerReport;

public class ManagerContractFactory {
	
	
	
	
	public static ManagerReportFacade newInstance(StudentPayments studentPayments){
		
		
		return new ManagerReportFacade(new ManagerReport());
	}
	

}

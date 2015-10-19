package br.com.wgengenharia.manager.report.factory;

import br.com.wgengenharia.manager.model.Student;
import br.com.wgengenharia.manager.model.StudentPayments;
import br.com.wgengenharia.manager.report.facade.ManagerReportFacade;
import br.com.wgengenharia.manager.report.model.ContractReport;

public class ManagerReportFactory {
	
	
	public static ManagerReportFacade newInstanceStudentContract(Student student){
		return new ManagerReportFacade(new ContractReport());
	}
	
	public static ManagerReportFacade newInstanceStudentPayments(StudentPayments studentPayments){
		return new ManagerReportFacade(new ContractReport());
	}
	

}

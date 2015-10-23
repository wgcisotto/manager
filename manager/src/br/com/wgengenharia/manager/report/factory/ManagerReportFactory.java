package br.com.wgengenharia.manager.report.factory;

import br.com.wgengenharia.manager.model.Student;
import br.com.wgengenharia.manager.model.StudentPayments;
import br.com.wgengenharia.manager.report.model.ContractReporter;
import br.com.wgengenharia.manager.report.model.ManagerReport;
import br.com.wgengenharia.manager.report.model.PaymentReporter;

public class ManagerReportFactory {
	
	
	public static ManagerReport newInstanceContract(Student student){
		return new ContractReporter(student);
	}
	
	public static ManagerReport newInstancePayment(StudentPayments studentPayments){
		return new PaymentReporter(studentPayments);
	}
	

}

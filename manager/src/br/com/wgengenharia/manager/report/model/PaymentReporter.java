package br.com.wgengenharia.manager.report.model;

import java.io.InputStream;

import br.com.wgengenharia.manager.model.StudentPayments;

public class PaymentReporter extends AbstractManagerReport implements ManagerReport{

	private StudentPayments studentPayments;
	
	public PaymentReporter(StudentPayments studentPayments) {
		this.studentPayments = studentPayments;
	}
	
	@Override
	public InputStream generateReport() {
		
		
		return null;
	}
	

}

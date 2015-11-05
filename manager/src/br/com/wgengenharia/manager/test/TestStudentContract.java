package br.com.wgengenharia.manager.test;

import net.sf.jasperreports.engine.JRException;
import br.com.wgengenharia.manager.model.Student;
import br.com.wgengenharia.manager.report.ManagerReport;
import br.com.wgengenharia.manager.report.factory.ManagerReportFactory;
import br.com.wgengenharia.manager.report.model.entity.StudentContract;

public class TestStudentContract {

	public static void main(String[] args) {
		
		StudentContract studentContract = new StudentContract();
		
		ManagerReport manager = ManagerReportFactory.newInstanceContract(new Student());
		try {
			manager.generateReport();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}

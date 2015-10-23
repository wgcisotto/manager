package br.com.wgengenharia.manager.test;

import br.com.wgengenharia.manager.model.Student;
import br.com.wgengenharia.manager.report.ManagerReporter;
import br.com.wgengenharia.manager.report.ManagerReporterInterface;

public class Main {

	
	public static void main(String[] args) {
		
		
		Student student = new Student();
		
		student.setStudent_name("Joao");
		try {
			ManagerReporterInterface rel = new ManagerReporter();
			rel.generate(student);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		
		
	}
	
	
}

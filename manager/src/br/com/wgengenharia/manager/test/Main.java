package br.com.wgengenharia.manager.test;

import java.util.ArrayList;
import java.util.List;

import br.com.wgengenharia.manager.model.Student;
import br.com.wgengenharia.manager.report.StudentREL;

public class Main {

	
	public static void main(String[] args) {
		
		
		Student student = new Student();
		
//		student.setNome("Joao");
		
		List<Student> students = new ArrayList<>();
		
		students.add(student);
		
		StudentREL rel = new StudentREL();
		
		try {
			rel.imprimir(students);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		
		
	}
	
	
}

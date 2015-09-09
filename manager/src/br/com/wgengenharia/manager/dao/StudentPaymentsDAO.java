package br.com.wgengenharia.manager.dao;

import java.util.List;

import br.com.wgengenharia.manager.dao.model.DAOInterface;
import br.com.wgengenharia.manager.model.Student;
import br.com.wgengenharia.manager.model.StudentPayments;

public interface StudentPaymentsDAO extends DAOInterface<StudentPayments, Integer> {
	
	public List<StudentPayments> listStudentPayments(Student student);

}

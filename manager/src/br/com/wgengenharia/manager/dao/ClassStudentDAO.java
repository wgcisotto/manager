package br.com.wgengenharia.manager.dao;

import java.util.List;

import br.com.wgengenharia.manager.dao.model.DAOInterface;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.ClassStudent;
import br.com.wgengenharia.manager.model.Company;
import br.com.wgengenharia.manager.model.Student;

public interface ClassStudentDAO extends DAOInterface<ClassStudent, Integer> {
	
	
	public List<ClassStudent> listClassStudentsByCompany(Company company);
	
	public List<ClassStudent> listClassStudentsByCompanyAndBranch(Company company, Branch branch);
	
	public Integer findClassIdByStudent(Student student);
	

}

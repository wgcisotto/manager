package br.com.wgengenharia.manager.dao;

import java.util.List;

import br.com.wgengenharia.manager.dao.model.DAOInterface;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Company;
import br.com.wgengenharia.manager.model.Student;

public interface StudentDAO extends DAOInterface<Student, Integer> {
	
	public List<Student> listStudentByCompany(Company company);
	
	public List<Student> listStudentByCompanyAndBranch(Company company, Branch branch);
	

}

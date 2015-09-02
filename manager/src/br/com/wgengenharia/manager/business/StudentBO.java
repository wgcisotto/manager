package br.com.wgengenharia.manager.business;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.StudentDAO;
import br.com.wgengenharia.manager.dao.StudentDAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Company;
import br.com.wgengenharia.manager.model.Student;

public class StudentBO implements StudentDAO {

	private StudentDAO DAO;
	
	public StudentBO(EntityManager em) {
		DAO = new StudentDAOImpl(em);
	}
	
	@Override
	public void insert(Student student) {
		DAO.insert(student);
	}

	@Override
	public void update(Student student) {
		DAO.update(student);
	}

	@Override
	public void delete(Student student) {
		DAO.delete(student);
	}

	@Override
	public Student findById(Integer id) {
		return DAO.findById(id);
	}

	@Override
	public List<Student> listStudentByCompany(Company company) {
		return DAO.listStudentByCompany(company);
	}

	@Override
	public List<Student> listStudentByCompanyAndBranch(Company company,
			Branch branch) {
		return DAO.listStudentByCompanyAndBranch(company, branch);
	}

}
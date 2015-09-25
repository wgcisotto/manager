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
	private AddressBO BO;
	
	public StudentBO(EntityManager em) {
		DAO = new StudentDAOImpl(em);
		BO = new AddressBO(em);
	}
	
	@Override
	public void insert(Student student) {
		BO.insert(student.getResp_address());
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

	@Override
	public List<Student> listByBranch(Branch branch) {
		return DAO.listByBranch(branch);
	}

}

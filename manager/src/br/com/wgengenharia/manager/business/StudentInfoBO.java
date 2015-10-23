package br.com.wgengenharia.manager.business;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.StudentInfoDAO;
import br.com.wgengenharia.manager.dao.StudentInfoImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.StudentInfo;

public class StudentInfoBO implements StudentInfoDAO {
	
	private StudentInfoDAO DAO;
	
	public StudentInfoBO(EntityManager em) {
		DAO = new StudentInfoImpl(em);
	}
	

	@Override
	public void insert(StudentInfo studentInfo) {
		DAO.insert(studentInfo);
	}

	@Override
	public void update(StudentInfo studentInfo) {
		DAO.update(studentInfo);
	}

	@Override
	public void delete(StudentInfo studentInfo) {
		DAO.delete(studentInfo);
	}

	@Override
	public StudentInfo findById(Integer id) {
		return DAO.findById(id);
	}

	@Override
	public List<StudentInfo> listByBranch(Branch branch) {
		return DAO.listByBranch(branch);
	}

}

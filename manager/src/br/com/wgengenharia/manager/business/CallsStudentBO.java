package br.com.wgengenharia.manager.business;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.BranchDAO;
import br.com.wgengenharia.manager.dao.CallStudentDAO;
import br.com.wgengenharia.manager.dao.CallStudentDAOImpl;
import br.com.wgengenharia.manager.model.CallStudent;

public class CallsStudentBO implements CallStudentDAO {

	private CallStudentDAO DAO;
	
	public CallsStudentBO(EntityManager em) {
		DAO = new CallStudentDAOImpl(em);
	}
	
	@Override
	public void insert(CallStudent entity) {
		DAO.insert(entity);
	}

	@Override
	public void update(CallStudent entity) {
		DAO.update(entity);
	}

	@Override
	public void delete(CallStudent entity) {
		DAO.delete(entity);
	}

	@Override
	public CallStudent findById(Integer id) {
		return DAO.findById(id);
	}

}

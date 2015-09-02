package br.com.wgengenharia.manager.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.CallStudent;

public class CallStudentDAOImpl extends DAOImpl<CallStudent, Integer> implements CallStudentDAO{

	public CallStudentDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

}

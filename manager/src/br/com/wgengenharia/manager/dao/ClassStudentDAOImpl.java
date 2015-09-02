package br.com.wgengenharia.manager.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.ClassStudent;

public class ClassStudentDAOImpl extends DAOImpl<ClassStudent, Integer> implements ClassStudentDAO{

	public ClassStudentDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

}

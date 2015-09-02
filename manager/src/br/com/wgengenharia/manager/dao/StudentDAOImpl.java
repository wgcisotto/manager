package br.com.wgengenharia.manager.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Student;

public class StudentDAOImpl extends DAOImpl<Student, Integer> implements StudentDAO{

	public StudentDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

}

package br.com.wgengenharia.manager.business;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.ClassStudentDAO;
import br.com.wgengenharia.manager.dao.ClassStudentDAOImpl;
import br.com.wgengenharia.manager.model.ClassStudent;

public class ClassStudentBO implements ClassStudentDAO{

	private ClassStudentDAO DAO;
	
	public ClassStudentBO(EntityManager em) {
		DAO = new ClassStudentDAOImpl(em);
	}
	
	
	@Override
	public void insert(ClassStudent entity) {
		DAO.insert(entity);
	}

	@Override
	public void update(ClassStudent entity) {
		DAO.update(entity);
	}

	@Override
	public void delete(ClassStudent entity) {
		DAO.delete(entity);
	}

	@Override
	public ClassStudent findById(Integer id) {
		return DAO.findById(id);
	}

}

package br.com.wgengenharia.manager.coffe.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.coffe.dao.model.DAOImpl;
import br.com.wgengenharia.manager.coffe.model.Category;

public class CategoryDAOImpl extends DAOImpl<Category, Integer> implements CategoryDAO {

	public CategoryDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}
	

}

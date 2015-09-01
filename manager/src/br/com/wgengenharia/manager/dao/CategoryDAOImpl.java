package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Category;

public class CategoryDAOImpl extends DAOImpl<Category, Integer> implements CategoryDAO {

	public CategoryDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Category> listCategories() {
		TypedQuery<Category> query = em.createQuery("from Category", Category.class);
    return query.getResultList();
	}

}

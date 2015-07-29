package br.com.wgengenharia.manager.coffe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.coffe.dao.model.DAOImpl;
import br.com.wgengenharia.manager.coffe.model.Category;
import br.com.wgengenharia.manager.coffe.model.Product;

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

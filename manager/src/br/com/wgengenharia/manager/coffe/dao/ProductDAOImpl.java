package br.com.wgengenharia.manager.coffe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.coffe.dao.model.DAOImpl;
import br.com.wgengenharia.manager.coffe.model.Product;


public class ProductDAOImpl extends DAOImpl<Product, Integer> implements ProductDAO{

	public ProductDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Product> listProducts() {
		TypedQuery<Product> query = em.createQuery("from Product", Product.class);
    return query.getResultList();
	}
	


}

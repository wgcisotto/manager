package br.com.wgengenharia.manager.coffe.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.coffe.dao.model.DAOImpl;
import br.com.wgengenharia.manager.coffe.model.Product;


public class ProductDAOImpl extends DAOImpl<Product, Integer> implements ProductDAO{

	public ProductDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}
	


}

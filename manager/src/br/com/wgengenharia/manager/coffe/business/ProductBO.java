package br.com.wgengenharia.manager.coffe.business;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.coffe.dao.ProductDAO;
import br.com.wgengenharia.manager.coffe.dao.ProductDAOImpl;
import br.com.wgengenharia.manager.coffe.dao.model.DAOInterface;
import br.com.wgengenharia.manager.coffe.model.Product;

public class ProductBO implements DAOInterface<Product, Integer>{

	private ProductDAO DAO;
	
	public ProductBO(EntityManager em) {
		DAO = new ProductDAOImpl(em);
	}
	
	@Override
	public void insert(Product product) {
		DAO.insert(product);
	}

	@Override
	public void update(Product product) {
		DAO.update(product);
	}

	@Override
	public void delete(Product product) {
		DAO.delete(product);
	}

	@Override
	public Product findById(Integer id) {
		return DAO.findById(id);
	}
	
}

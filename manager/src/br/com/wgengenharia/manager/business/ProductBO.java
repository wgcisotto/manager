package br.com.wgengenharia.manager.business;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.ProductDAO;
import br.com.wgengenharia.manager.dao.ProductDAOImpl;
import br.com.wgengenharia.manager.model.Product;

public class ProductBO implements ProductDAO{

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

	@Override
	public List<Product> listProducts() {
		return DAO.listProducts();
	}
	
}

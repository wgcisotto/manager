package br.com.wgengenharia.manager.coffe.dao;

import java.util.List;

import br.com.wgengenharia.manager.coffe.dao.model.DAOInterface;
import br.com.wgengenharia.manager.coffe.model.Product;

public interface ProductDAO extends DAOInterface<Product, Integer> {
	
	public List<Product> listProducts();

}

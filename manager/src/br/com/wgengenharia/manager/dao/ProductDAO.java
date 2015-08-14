package br.com.wgengenharia.manager.dao;

import java.util.List;

import br.com.wgengenharia.manager.dao.model.DAOInterface;
import br.com.wgengenharia.manager.model.Product;

public interface ProductDAO extends DAOInterface<Product, Integer> {
	
	public List<Product> listProducts();

}

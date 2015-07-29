package br.com.wgengenharia.manager.coffe.dao;

import java.util.List;

import br.com.wgengenharia.manager.coffe.dao.model.DAOInterface;
import br.com.wgengenharia.manager.coffe.model.Category;

public interface CategoryDAO extends DAOInterface<Category, Integer> {

	public List<Category> listCategories();
	
}

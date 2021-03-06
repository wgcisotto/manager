package br.com.wgengenharia.manager.dao;

import java.util.List;

import br.com.wgengenharia.manager.dao.model.DAOInterface;
import br.com.wgengenharia.manager.model.Category;

public interface CategoryDAO extends DAOInterface<Category, Integer> {

	public List<Category> listCategories();
	
}

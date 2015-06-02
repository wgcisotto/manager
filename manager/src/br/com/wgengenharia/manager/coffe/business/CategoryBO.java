package br.com.wgengenharia.manager.coffe.business;

import br.com.wgengenharia.manager.coffe.dao.CategoryDAO;
import br.com.wgengenharia.manager.coffe.dao.CategoryDAOImpl;
import br.com.wgengenharia.manager.coffe.dao.model.DAOInterface;
import br.com.wgengenharia.manager.coffe.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.coffe.model.Category;

public class CategoryBO implements DAOInterface<Category, Integer> {

	private CategoryDAO DAO;
	
	public CategoryBO() {
		DAO = new CategoryDAOImpl(EntityManagerFactorySingleton.getInstance().createEntityManager());
	}
	
	@Override
	public void insert(Category category) {
		DAO.insert(category);
	}

	@Override
	public void update(Category category) {
		DAO.update(category);
	}

	@Override
	public void delete(Category category) {
		DAO.delete(category);
	}

	@Override
	public Category findById(Integer id) {
		return DAO.findById(id);
	}

}

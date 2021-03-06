package br.com.wgengenharia.manager.business;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.CategoryDAO;
import br.com.wgengenharia.manager.dao.CategoryDAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Category;

public class CategoryBO implements CategoryDAO {

	private CategoryDAO DAO;
	
	public CategoryBO(EntityManager em) {
		DAO = new CategoryDAOImpl(em);
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

	@Override
	public List<Category> listCategories() {
		return DAO.listCategories();
	}

	@Override
	public List<Category> listByBranch(Branch branch) {
		return DAO.listByBranch(branch);
	}

}

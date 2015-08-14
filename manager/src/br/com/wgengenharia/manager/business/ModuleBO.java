package br.com.wgengenharia.manager.business;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.ModuleDAO;
import br.com.wgengenharia.manager.dao.ModuleDAOImpl;
import br.com.wgengenharia.manager.model.Module;

public class ModuleBO implements ModuleDAO {

	private ModuleDAO DAO;
	
	public ModuleBO(EntityManager em) {
		DAO = new ModuleDAOImpl(em);
	}
	
	
	@Override
	public void insert(Module module) {
		DAO.insert(module);
	}

	@Override
	public void update(Module module) {
		DAO.update(module);
	}

	@Override
	public void delete(Module module) {
		DAO.delete(module);
	}

	@Override
	public Module findById(Integer id) {
		return DAO.findById(id);
	}

}

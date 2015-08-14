package br.com.wgengenharia.manager.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Module;

public class ModuleDAOImpl extends DAOImpl<Module, Integer> implements ModuleDAO {

	public ModuleDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}

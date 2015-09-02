package br.com.wgengenharia.manager.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.ClassModule;

public class ClassModuleDAOImpl extends DAOImpl<ClassModule, Integer> implements ClassModuleDAO{

	public ClassModuleDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

}

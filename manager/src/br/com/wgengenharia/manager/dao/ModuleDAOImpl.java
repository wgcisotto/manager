package br.com.wgengenharia.manager.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Module;
import br.com.wgengenharia.manager.model.Product;
import br.com.wgengenharia.manager.model.Profile;

public class ModuleDAOImpl extends DAOImpl<Module, Integer> implements ModuleDAO {

	public ModuleDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Module findByName(String moduleName) {
		TypedQuery<Module> query = em.createQuery("from Module m where m.type like :moduleName", Module.class);
		query.setParameter("moduleName", moduleName);
    return query.getSingleResult();
	}

}

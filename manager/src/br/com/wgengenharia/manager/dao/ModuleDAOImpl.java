package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Module;

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

	@Override
	public List<Module> listByBranch(Branch branch) {
		TypedQuery<Module> query = em.createQuery("from Module o where o.branch = :branch", Module.class);
		query.setParameter("branch", branch);
    return query.getResultList();
	}

}

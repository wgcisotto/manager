package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.ClassModule;
import br.com.wgengenharia.manager.model.Company;

public class ClassModuleDAOImpl extends DAOImpl<ClassModule, Integer> implements ClassModuleDAO{

	public ClassModuleDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<ClassModule> listModulesByCompany(Company company) {
		TypedQuery<ClassModule> query = em.createQuery("from ClassModule cm where cm.company = :company", ClassModule.class);
		query.setParameter("company", company);
    return query.getResultList();
	}

	@Override
	public List<ClassModule> listModulesByCompanyAndBranch(Company company,
			Branch branch) {
		TypedQuery<ClassModule> query = em.createQuery("from ClassModule cm where cm.company = :company and cm.branch = :branch", ClassModule.class);
		query.setParameter("company", company);
		query.setParameter("brach", branch);
    return query.getResultList();
	}

	@Override
	public List<ClassModule> listByBranch(Branch branch) {
		TypedQuery<ClassModule> query = em.createQuery("from ClassModule o where o.branch = :branch", ClassModule.class);
		query.setParameter("branch", branch);
    return query.getResultList();
	}

	@Override
	public ClassModule findByClassModuleSequence(Integer sequenceNumber) {
		TypedQuery<ClassModule> query = em.createQuery("from ClassModule o where o.sequence = :sequenceNumber", ClassModule.class);
		query.setParameter("sequenceNumber", sequenceNumber);
    return query.getSingleResult();
	}

	

}

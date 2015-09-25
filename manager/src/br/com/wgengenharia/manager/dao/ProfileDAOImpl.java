package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Company;
import br.com.wgengenharia.manager.model.Profile;

public class ProfileDAOImpl extends DAOImpl<Profile, Integer> implements ProfileDAO{

	public ProfileDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Profile findByNameAndCompany(String profileName, Company company) {
		TypedQuery<Profile> query = em.createQuery("from Profile p where p.name like :profileName and p.company = :company", Profile.class);
		query.setParameter("profileName", profileName);
		query.setParameter("company", company);
    return query.getSingleResult();
	}

	@Override
	public List<Profile> findByCompany(Company company) {
		TypedQuery<Profile> query = em.createQuery("from Profile p where p.company = :company", Profile.class);
		query.setParameter("company", company);
    return query.getResultList();
	}

	@Override
	public List<Profile> listByBranch(Branch branch) {
		TypedQuery<Profile> query = em.createQuery("from Profile o where o.branch = :branch", Profile.class);
		query.setParameter("branch", branch);
    return query.getResultList();
	}

}


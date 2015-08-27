package br.com.wgengenharia.manager.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Employee;
import br.com.wgengenharia.manager.model.Profile;

public class ProfileDAOImpl extends DAOImpl<Profile, Integer> implements ProfileDAO{

	public ProfileDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Profile findByName(String profileName) {
		TypedQuery<Profile> query = em.createQuery("from Profile p where p.name like :profileName", Profile.class);
		query.setParameter("profileName", profileName);
    return query.getSingleResult();
	}

}

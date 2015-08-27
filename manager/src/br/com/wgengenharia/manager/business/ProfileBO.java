package br.com.wgengenharia.manager.business;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.ProfileDAO;
import br.com.wgengenharia.manager.dao.ProfileDAOImpl;
import br.com.wgengenharia.manager.model.Profile;

public class ProfileBO implements ProfileDAO {

	private ProfileDAO DAO;
	
	public ProfileBO(EntityManager em) {
		DAO = new ProfileDAOImpl(em);
	}
	
	
	@Override
	public void insert(Profile profile) {
		DAO.insert(profile);
	}

	@Override
	public void update(Profile profile) {
		DAO.update(profile);
	}

	@Override
	public void delete(Profile profile) {
		DAO.delete(profile);
	}

	@Override
	public Profile findById(Integer id) {
		return DAO.findById(id);	}


	@Override
	public Profile findByName(String profileName) {
		return DAO.findByName(profileName);
	}

}

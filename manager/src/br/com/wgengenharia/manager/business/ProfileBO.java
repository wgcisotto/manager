package br.com.wgengenharia.manager.business;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.ProfileDAO;
import br.com.wgengenharia.manager.dao.ProfileDAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Company;
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
	public Profile findByNameAndCompany(String profileName, Company company) {
		return DAO.findByNameAndCompany(profileName, company);
	}


	@Override
	public List<Profile> findByCompany(Company company) {
		return DAO.findByCompany(company);
	}


	@Override
	public List<Profile> listByBranch(Branch branch) {
		return DAO.listByBranch(branch);
	}

}

package br.com.wgengenharia.manager.dao;

import java.util.List;

import br.com.wgengenharia.manager.dao.model.DAOInterface;
import br.com.wgengenharia.manager.model.Company;
import br.com.wgengenharia.manager.model.Profile;

public interface ProfileDAO extends DAOInterface<Profile, Integer> {
	
	public Profile findByNameAndCompany(String profileName, Company company);
	
	public List<Profile> findByCompany(Company company);

}

package br.com.wgengenharia.manager.dao;

import br.com.wgengenharia.manager.dao.model.DAOInterface;
import br.com.wgengenharia.manager.model.Profile;

public interface ProfileDAO extends DAOInterface<Profile, Integer> {
	
	public Profile findByName(String profileName);

}

package br.com.wgengenharia.manager.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.User;

public class UserDAOImpl extends DAOImpl<User, Integer> implements UserDAO {

	public UserDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

}

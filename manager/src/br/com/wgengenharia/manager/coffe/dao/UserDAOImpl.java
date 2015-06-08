package br.com.wgengenharia.manager.coffe.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.coffe.dao.model.DAOImpl;
import br.com.wgengenharia.manager.coffe.model.User;

public class UserDAOImpl extends DAOImpl<User, Integer> implements UserDAO {

	public UserDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

}

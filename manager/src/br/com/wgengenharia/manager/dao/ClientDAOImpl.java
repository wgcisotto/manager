package br.com.wgengenharia.manager.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Client;

public class ClientDAOImpl extends DAOImpl<Client, Integer> implements ClientDAO {

	public ClientDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

}

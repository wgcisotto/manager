package br.com.wgengenharia.manager.coffe.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.coffe.dao.model.DAOImpl;
import br.com.wgengenharia.manager.coffe.model.Client;

public class ClientDAOImpl extends DAOImpl<Client, Integer> implements ClientDAO {

	public ClientDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

}

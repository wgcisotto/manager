package br.com.wgengenharia.manager.coffe.business;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.coffe.dao.ClientDAO;
import br.com.wgengenharia.manager.coffe.dao.ClientDAOImpl;
import br.com.wgengenharia.manager.coffe.dao.model.DAOInterface;
import br.com.wgengenharia.manager.coffe.model.Client;

public class ClientBO implements DAOInterface<Client, Integer> {

	private ClientDAO clientDAO;
	
	public ClientBO(EntityManager em) {
		clientDAO = new ClientDAOImpl(em);
	}
	
	@Override
	public void insert(Client client) {
		clientDAO.insert(client);
	}

	@Override
	public void update(Client client) {
		clientDAO.update(client);
	}

	@Override
	public void delete(Client client) {
		clientDAO.delete(client);
	}

	@Override
	public Client findById(Integer id) {
		return clientDAO.findById(id);
	}

}

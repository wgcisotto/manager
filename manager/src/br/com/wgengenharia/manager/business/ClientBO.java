package br.com.wgengenharia.manager.business;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.ClientDAO;
import br.com.wgengenharia.manager.dao.ClientDAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Client;

public class ClientBO implements ClientDAO {

	private ClientDAO DAO;
	
	public ClientBO(EntityManager em) {
		DAO = new ClientDAOImpl(em);
	}
	
	@Override
	public void insert(Client client) {
		DAO.insert(client);
	}

	@Override
	public void update(Client client) {
		DAO.update(client);
	}

	@Override
	public void delete(Client client) {
		DAO.delete(client);
	}

	@Override
	public Client findById(Integer id) {
		return DAO.findById(id);
	}

	@Override
	public List<Client> listByBranch(Branch branch) {
		return DAO.listByBranch(branch);
	}

}

package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Client;

public class ClientDAOImpl extends DAOImpl<Client, Integer> implements ClientDAO {

	public ClientDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Client> listByBranch(Branch branch) {
		TypedQuery<Client> query = em.createQuery("from Client o where o.branch = :branch", Client.class);
		query.setParameter("branch", branch);
    return query.getResultList();
	}

}

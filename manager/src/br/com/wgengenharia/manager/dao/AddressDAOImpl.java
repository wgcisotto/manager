package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Address;
import br.com.wgengenharia.manager.model.Branch;

public class AddressDAOImpl extends DAOImpl<Address, Integer> implements AddressDAO{

	public AddressDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Address> listByBranch(Branch branch) {
		TypedQuery<Address> query = em.createQuery("from Address o where o.branch = :branch", Address.class);
		query.setParameter("branch", branch);
    return query.getResultList();
	}

}

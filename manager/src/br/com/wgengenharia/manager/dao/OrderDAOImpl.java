package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Order;

public class OrderDAOImpl extends DAOImpl<Order, Integer> implements OrderDAO {

	public OrderDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Order> listByBranch(Branch branch) {
		TypedQuery<Order> query = em.createQuery("from Order o where o.branch = :branch", Order.class);
		query.setParameter("branch", branch);
    return query.getResultList();
	}

	
	
}

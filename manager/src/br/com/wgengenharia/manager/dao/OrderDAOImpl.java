package br.com.wgengenharia.manager.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Order;

public class OrderDAOImpl extends DAOImpl<Order, Integer> implements OrderDAO {

	public OrderDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	
	
}

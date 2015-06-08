package br.com.wgengenharia.manager.coffe.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.coffe.dao.model.DAOImpl;
import br.com.wgengenharia.manager.coffe.model.Order;

public class OrderDAOImpl extends DAOImpl<Order, Integer> implements OrderDAO {

	public OrderDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	
	
}

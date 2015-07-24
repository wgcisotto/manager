package br.com.wgengenharia.manager.coffe.business;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.coffe.dao.OrderDAO;
import br.com.wgengenharia.manager.coffe.dao.OrderDAOImpl;
import br.com.wgengenharia.manager.coffe.model.Order;

public class OrderBO implements OrderDAO{

	private OrderDAO orderDAO;
	
	public OrderBO(EntityManager em) {
		orderDAO = new OrderDAOImpl(em);
	}
	
	@Override
	public void insert(Order order) {
		orderDAO.insert(order);
	}

	@Override
	public void update(Order order) {
		orderDAO.update(order);
	}

	@Override
	public void delete(Order order) {
		orderDAO.delete(order);
	}

	@Override
	public Order findById(Integer id) {
		return orderDAO.findById(id);
	}

	
	
}

package br.com.wgengenharia.manager.business;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.OrderDAO;
import br.com.wgengenharia.manager.dao.OrderDAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Order;

public class OrderBO implements OrderDAO{

	private OrderDAO DAO;
	
	public OrderBO(EntityManager em) {
		DAO = new OrderDAOImpl(em);
	}
	
	@Override
	public void insert(Order order) {
		DAO.insert(order);
	}

	@Override
	public void update(Order order) {
		DAO.update(order);
	}

	@Override
	public void delete(Order order) {
		DAO.delete(order);
	}

	@Override
	public Order findById(Integer id) {
		return DAO.findById(id);
	}

	@Override
	public List<Order> listByBranch(Branch branch) {
		return DAO.listByBranch(branch);
	}

	
	
}

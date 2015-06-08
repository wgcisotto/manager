package br.com.wgengenharia.manager.coffe.business;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.coffe.dao.UserDAO;
import br.com.wgengenharia.manager.coffe.dao.UserDAOImpl;
import br.com.wgengenharia.manager.coffe.dao.model.DAOInterface;
import br.com.wgengenharia.manager.coffe.model.User;

public class UserBO implements DAOInterface<User, Integer> {

	private UserDAO userDAO;
	
	public UserBO(EntityManager em) {
		userDAO = new UserDAOImpl(em);
	}
	
	@Override
	public void insert(User user) {
		userDAO.insert(user);
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	public void delete(User user) {
		userDAO.delete(user);
	}

	@Override
	public User findById(Integer id) {
		return userDAO.findById(id);
	}

}

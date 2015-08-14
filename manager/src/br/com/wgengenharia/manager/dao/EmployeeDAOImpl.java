package br.com.wgengenharia.manager.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Employee;

public class EmployeeDAOImpl extends DAOImpl<Employee, Integer> implements EmployeeDAO {

	public EmployeeDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}

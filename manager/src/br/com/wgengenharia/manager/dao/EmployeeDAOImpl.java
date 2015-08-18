package br.com.wgengenharia.manager.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Employee;

public class EmployeeDAOImpl extends DAOImpl<Employee, Integer> implements EmployeeDAO {

	public EmployeeDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Employee findByEmail(String email) {
		TypedQuery<Employee> query = em.createQuery("from Employee e where e.user like :email", Employee.class);
		query.setParameter("email", email);
    return query.getSingleResult();
	}


}

package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Company;
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

	@Override
	public List<Employee> findByCompany(Company company) {
		TypedQuery<Employee> query = em.createQuery("from Employee e where e.company = :company", Employee.class);
		query.setParameter("company", company);
    return query.getResultList();
	}

	@Override
	public List<Employee> findByCompanyAndBranch(Company company,Branch branch) {
		TypedQuery<Employee> query = em.createQuery("from Employee e where e.company = :company and e.branch = :branch", Employee.class);
		query.setParameter("company", company);
		query.setParameter("branch", branch);
    return query.getResultList();
	}

}

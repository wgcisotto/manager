package br.com.wgengenharia.manager.business;

import java.util.List;

import br.com.wgengenharia.manager.dao.EmployeeDAO;
import br.com.wgengenharia.manager.dao.EmployeeDAOImpl;
import br.com.wgengenharia.manager.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Company;
import br.com.wgengenharia.manager.model.Employee;

public class EmployeeBO implements EmployeeDAO {

	
	private EmployeeDAO DAO;
	private AddressBO BO;
	
	public EmployeeBO() {
		BO = new AddressBO(EntityManagerFactorySingleton.getInstance().createEntityManager());
		DAO = new EmployeeDAOImpl(EntityManagerFactorySingleton.getInstance().createEntityManager());
	}

	@Override
	public void insert(Employee employee) {
		BO.insert(employee.getAddress());
		DAO.insert(employee);
	}

	@Override
	public void update(Employee employee) {
		DAO.update(employee);
	}

	@Override
	public void delete(Employee employee) {
		DAO.delete(employee);
	}

	@Override
	public Employee findById(Integer id) {
		return DAO.findById(id);
	}

	@Override
	public Employee findByEmail(String email) {
		return DAO.findByEmail(email);
	}

	@Override
	public List<Employee> findByCompany(Company company) {
		return DAO.findByCompany(company);
	}

	@Override
	public List<Employee> findByCompanyAndBranch(Company company, Branch branch) {
		return DAO.findByCompanyAndBranch(company, branch);
	}

	
}

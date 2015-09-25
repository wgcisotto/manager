package br.com.wgengenharia.manager.business;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.CompanyDAO;
import br.com.wgengenharia.manager.dao.CompanyDAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Company;

public class CompanyBO implements CompanyDAO {

	
	private CompanyDAO DAO;
	
	public CompanyBO(EntityManager em) {
		DAO = new CompanyDAOImpl(em);
	}
	
	@Override
	public void insert(Company company) {
		DAO.insert(company);
	}

	@Override
	public void update(Company company) {
		DAO.update(company);
	}

	@Override
	public void delete(Company company) {
		DAO.delete(company);
	}

	@Override
	public Company findById(Integer id) {
		return DAO.findById(id);
	}

	@Override
	public List<Company> listByBranch(Branch branch) {
		return DAO.listByBranch(branch);
	}

}

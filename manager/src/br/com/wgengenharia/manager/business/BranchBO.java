package br.com.wgengenharia.manager.business;

import java.util.List;

import javax.persistence.EntityManager;


import br.com.wgengenharia.manager.dao.BranchDAO;
import br.com.wgengenharia.manager.dao.BranchDAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Company;

public class BranchBO implements BranchDAO {
	
	private BranchDAO DAO;
	private AddressBO BO;
	public BranchBO(EntityManager em) {
		DAO = new BranchDAOImpl(em);
		BO = new AddressBO(em);
	}

	@Override
	public void insert(Branch branch) {
		BO.insert(branch.getAddress());
		DAO.insert(branch);
	}

	@Override
	public void update(Branch branch) {
		DAO.update(branch);
	}

	@Override
	public void delete(Branch branch) {
		DAO.delete(branch);
	}

	@Override
	public Branch findById(Integer id) {
		return DAO.findById(id);
	}

	@Override
	public List<Branch> findByCompany(Company company) {
		return DAO.findByCompany(company);
	}

	@Override
	public List<Branch> listByBranch(Branch branch) {
		return DAO.listByBranch(branch);
	}

}

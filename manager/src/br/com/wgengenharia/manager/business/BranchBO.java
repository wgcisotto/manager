package br.com.wgengenharia.manager.business;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.BranchDAO;
import br.com.wgengenharia.manager.dao.BranchDAOImpl;
import br.com.wgengenharia.manager.model.Branch;

public class BranchBO implements BranchDAO {
	
	private BranchDAO DAO;
	
	public BranchBO(EntityManager em) {
		DAO = new BranchDAOImpl(em);
	}

	@Override
	public void insert(Branch branch) {
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

}

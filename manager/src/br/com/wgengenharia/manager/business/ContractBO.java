package br.com.wgengenharia.manager.business;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.ContractDAO;
import br.com.wgengenharia.manager.dao.ContractDAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Contract;

public class ContractBO implements ContractDAO {
	

	private ContractDAO DAO;
	
	public ContractBO(EntityManager em) {
		DAO = new ContractDAOImpl(em);
	}

	@Override
	public void insert(Contract contract) {
		DAO.insert(contract);
	}

	@Override
	public void update(Contract contract) {
		DAO.update(contract);
	}

	@Override
	public void delete(Contract contract) {
		DAO.delete(contract);
	}

	@Override
	public Contract findById(Integer id) {
		return findById(id);
	}

	@Override
	public List<Contract> listByBranch(Branch branch) {
		return DAO.listByBranch(branch);
	}

}

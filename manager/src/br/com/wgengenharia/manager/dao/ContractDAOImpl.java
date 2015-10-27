package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Contract;

public class ContractDAOImpl extends DAOImpl<Contract, Integer> implements ContractDAO{

	public ContractDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Contract> listByBranch(Branch branch) {
		// nao implementado
		return null;
	}

}

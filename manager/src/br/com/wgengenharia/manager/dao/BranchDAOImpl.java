package br.com.wgengenharia.manager.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;

public class BranchDAOImpl extends DAOImpl<Branch, Integer> implements BranchDAO {

	public BranchDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}

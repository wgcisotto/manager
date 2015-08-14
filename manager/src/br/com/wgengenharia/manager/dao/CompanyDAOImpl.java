package br.com.wgengenharia.manager.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Company;

public class CompanyDAOImpl extends DAOImpl<Company, Integer> implements CompanyDAO {

	public CompanyDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}

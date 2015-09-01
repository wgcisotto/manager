package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Company;

public class BranchDAOImpl extends DAOImpl<Branch, Integer> implements BranchDAO {

	public BranchDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Branch> findByCompany(Company company) {
		TypedQuery<Branch> query = em.createQuery("from Branch b where b.company = :company", Branch.class);
		query.setParameter("company", company);
    return query.getResultList();
	}

}

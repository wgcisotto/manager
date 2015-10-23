package br.com.wgengenharia.manager.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Sale;

public class SaleDAOImpl extends DAOImpl<Sale, Integer> implements SaleDAO{

	public SaleDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Sale> listSalesDayByBranch(Date day, Branch branch) {
		TypedQuery<Sale> query = em.createQuery("from Sale s where s.date like :day and s.branch = :branch", Sale.class);
		query.setParameter("day", day);
		query.setParameter("branch", branch);
    return query.getResultList();
	}

	@Override
	public List<Sale> listSales() {
		TypedQuery<Sale> query = em.createQuery("from Sale", Sale.class);
    return query.getResultList();
	}

	@Override
	public List<Sale> listByBranch(Branch branch) {
		TypedQuery<Sale> query = em.createQuery("from Sale o where o.branch = :branch", Sale.class);
		query.setParameter("branch", branch);
    return query.getResultList();
	}

	@Override
	public List<Sale> listSalesFiltered(Date begin, Date end, Branch branch) {
		TypedQuery<Sale> query = em.createQuery("from Sale s where s.branch = :branch and s.date between :begin and :end", Sale.class);
		query.setParameter("begin", begin);
		query.setParameter("end", end);
		query.setParameter("branch", branch);
    return query.getResultList();
	}
}

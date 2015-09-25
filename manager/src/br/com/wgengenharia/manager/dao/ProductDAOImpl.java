package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Product;


public class ProductDAOImpl extends DAOImpl<Product, Integer> implements ProductDAO{

	public ProductDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Product> listProducts() {
		TypedQuery<Product> query = em.createQuery("from Product", Product.class);
    return query.getResultList();
	}

	@Override
	public List<Product> listByBranch(Branch branch) {
		TypedQuery<Product> query = em.createQuery("from Product o where o.branch = :branch", Product.class);
		query.setParameter("branch", branch);
    return query.getResultList();
	}
	


}


package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Card;

public class CardDAOImpl extends DAOImpl<Card, Integer> implements CardDAO{

	public CardDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Card> listCards() {
		TypedQuery<Card> query = em.createQuery("from Card", Card.class);
    return query.getResultList();
	}

	@Override
	public List<Card> listByBranch(Branch branch) {
		TypedQuery<Card> query = em.createQuery("from Card o where o.branch = :branch", Card.class);
		query.setParameter("branch", branch);
    return query.getResultList();
	}

}

package br.com.wgengenharia.manager.coffe.dao;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.coffe.dao.model.DAOImpl;
import br.com.wgengenharia.manager.coffe.model.Card;

public class CardDAOImpl extends DAOImpl<Card, Integer> implements CardDAO{

	public CardDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

}

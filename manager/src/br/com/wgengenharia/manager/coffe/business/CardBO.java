package br.com.wgengenharia.manager.coffe.business;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.coffe.dao.CardDAO;
import br.com.wgengenharia.manager.coffe.dao.CardDAOImpl;
import br.com.wgengenharia.manager.coffe.dao.model.DAOInterface;
import br.com.wgengenharia.manager.coffe.model.Card;

public class CardBO implements DAOInterface<Card, Integer> {

	private CardDAO DAO;
	
	public CardBO(EntityManager em) {
		DAO = new CardDAOImpl(em);
	}
	
	@Override
	public void insert(Card card) {
		DAO.insert(card);
	}

	@Override
	public void update(Card card) {
		DAO.update(card);
	}

	@Override
	public void delete(Card card) {
		DAO.delete(card);
	}

	@Override
	public Card findById(Integer id) {
		return DAO.findById(id);
	}

}

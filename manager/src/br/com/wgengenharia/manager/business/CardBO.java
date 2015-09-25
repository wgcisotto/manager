package br.com.wgengenharia.manager.business;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.CardDAO;
import br.com.wgengenharia.manager.dao.CardDAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Card;

public class CardBO implements CardDAO {

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

	@Override
	public List<Card> listCards() {
		return DAO.listCards();
	}

	@Override
	public List<Card> listByBranch(Branch branch) {
		return DAO.listByBranch(branch);
	}

}

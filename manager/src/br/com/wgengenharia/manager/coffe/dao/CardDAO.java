package br.com.wgengenharia.manager.coffe.dao;

import java.util.List;

import br.com.wgengenharia.manager.coffe.dao.model.DAOInterface;
import br.com.wgengenharia.manager.coffe.model.Card;

public interface CardDAO extends DAOInterface<Card, Integer>{

	public List<Card> listCards();
	
}

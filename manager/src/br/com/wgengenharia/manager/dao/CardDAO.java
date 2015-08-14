package br.com.wgengenharia.manager.dao;

import java.util.List;

import br.com.wgengenharia.manager.dao.model.DAOInterface;
import br.com.wgengenharia.manager.model.Card;

public interface CardDAO extends DAOInterface<Card, Integer>{

	public List<Card> listCards();
	
}

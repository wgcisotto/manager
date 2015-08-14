package br.com.wgengenharia.manager.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.facade.ManagerSaleFacade;
import br.com.wgengenharia.manager.facade.ManagerSaleFacadeInterface;
import br.com.wgengenharia.manager.model.Card;
import br.com.wgengenharia.manager.model.Sale;

public abstract class ManagerSaleFactory {
	
	
	public static ManagerSaleFacadeInterface newInstance(Card card, EntityManager em){
		Sale sale = new Sale();
		
		sale.getProducts().addAll(card.getProducts());
		sale.setTotal(card.getTotal());
		sale.setDate(Calendar.getInstance());
		sale.setTime(Calendar.getInstance().getTime());
//		sale.set
		
		return new ManagerSaleFacade(sale,em);
	}
	

}

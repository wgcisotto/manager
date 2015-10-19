package br.com.wgengenharia.manager.factory;

import java.util.Date;

import br.com.wgengenharia.manager.facade.ManagerSaleFacade;
import br.com.wgengenharia.manager.facade.ManagerSaleFacadeInterface;
import br.com.wgengenharia.manager.model.Card;
import br.com.wgengenharia.manager.model.Sale;

public abstract class ManagerSaleFactory {
	
	
	public static ManagerSaleFacadeInterface newInstance(Card card){
		Sale sale = new Sale();
		
		sale.getProducts().addAll(card.getProducts());
		sale.setTotal(card.getTotal());
		sale.setDate(new Date());
		sale.setTime(new Date());
		sale.setBranch(card.getBranch());
//		sale.set
		
		return new ManagerSaleFacade(sale);
	}
	

}

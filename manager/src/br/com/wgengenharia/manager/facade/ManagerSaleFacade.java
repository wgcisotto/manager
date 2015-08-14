package br.com.wgengenharia.manager.facade;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.business.SaleBO;
import br.com.wgengenharia.manager.model.Sale;
import br.com.wgengenharia.manager.view.bean.ManagerBean;

public class ManagerSaleFacade implements ManagerSaleFacadeInterface {
	
	private Sale sale;
	private EntityManager em;
	
	public ManagerSaleFacade(Sale sale, EntityManager em) {
		this.sale =  sale;
		this.em = em;
	}
	
	
	public void persistSale(){
		SaleBO bo = new SaleBO(em);
		bo.insert(sale);
	}
	
	
	

}
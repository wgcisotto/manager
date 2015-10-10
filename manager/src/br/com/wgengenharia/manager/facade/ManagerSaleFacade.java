package br.com.wgengenharia.manager.facade;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.business.ProductBO;
import br.com.wgengenharia.manager.business.SaleBO;
import br.com.wgengenharia.manager.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.model.Product;
import br.com.wgengenharia.manager.model.Sale;

public class ManagerSaleFacade implements ManagerSaleFacadeInterface {
	
	private Sale sale;
	private EntityManager em;
	
	public ManagerSaleFacade(Sale sale) {
		this.sale =  sale;
		this.em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	}
	
	
	public void persistSale(){
		SaleBO bo = new SaleBO(em);
		ProductBO productBO = new ProductBO(em);
		
		for (Product prd : sale.getProducts()) {
			productBO.update(prd);
		}
		
		
		bo.insert(sale);
	}
	
	
	

}

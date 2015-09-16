package br.com.wgengenharia.manager.factory;

import java.util.Calendar;

import br.com.wgengenharia.manager.facade.ManagerSaleFacade;
import br.com.wgengenharia.manager.facade.ManagerSaleFacadeInterface;
import br.com.wgengenharia.manager.model.Sale;
import br.com.wgengenharia.manager.model.StudentPayments;

public class ManagerPaymentFactory {
	
	public static ManagerSaleFacadeInterface newInstance(StudentPayments payment){
		Sale sale = new Sale();
		
		sale.setTotal(payment.getPaid());
		sale.setDate(Calendar.getInstance());
		sale.setTime(Calendar.getInstance().getTime());
		
		
		return new ManagerSaleFacade(sale);
	}
	
}

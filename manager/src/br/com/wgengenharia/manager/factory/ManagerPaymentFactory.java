package br.com.wgengenharia.manager.factory;

import java.util.Date;

import br.com.wgengenharia.manager.facade.ManagerSaleFacade;
import br.com.wgengenharia.manager.facade.ManagerSaleFacadeInterface;
import br.com.wgengenharia.manager.model.Sale;
import br.com.wgengenharia.manager.model.StudentPayments;
import br.com.wgengenharia.manager.utils.AuthenticationUtil;

public class ManagerPaymentFactory {
	
	
	
	public static ManagerSaleFacadeInterface newInstance(StudentPayments payment){
		Sale sale = new Sale();
		
		sale.setTotal(payment.getPaid());
		sale.setDate(new Date());
		sale.setTime(new Date());
		sale.setDescription("Pagamento de boleto: " + payment.getBarcode() + " Aluno:" + payment.getStudent().getStudent_name());
		sale.setBranch(AuthenticationUtil.getUserInfo().currentBranch);
		
		
		return new ManagerSaleFacade(sale);
	}
	
}

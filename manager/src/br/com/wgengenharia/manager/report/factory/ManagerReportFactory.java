package br.com.wgengenharia.manager.report.factory;

import java.util.List;

import br.com.wgengenharia.manager.model.Sale;
import br.com.wgengenharia.manager.model.Student;
import br.com.wgengenharia.manager.report.ManagerReport;
import br.com.wgengenharia.manager.report.model.ContractReporter;
import br.com.wgengenharia.manager.report.model.SalesReporter;

public class ManagerReportFactory {
	
	public static ManagerReport newInstanceContract(Student student){
		return new ContractReporter(student);
	}
	
	public static ManagerReport newInstanceSales(List<Sale> sales){
		return new SalesReporter(sales);
	}

}

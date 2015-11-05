package br.com.wgengenharia.manager.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.wgengenharia.manager.model.Product;
import br.com.wgengenharia.manager.model.Sale;
import br.com.wgengenharia.manager.report.ManagerReport;
import br.com.wgengenharia.manager.report.factory.ManagerReportFactory;

public class Main {

	
	public static void main(String[] args) {
		
		Product prd1 = new Product();
		prd1.setName("Teste 1");
		prd1.setPrice(5.0);
		Product prd2 = new Product();
		prd2.setName("Teste 1");
		prd2.setPrice(5.0);
		
		List<Product> prods = new ArrayList<>();
		prods.add(prd1);
		prods.add(prd2);
		
		Sale sale1 = new Sale();
		sale1.setDate(new Date());
		sale1.setProducts(prods);
		sale1.setTotal(10.0);
		
		Sale sale2 = new Sale();
		sale2.setDate(new Date());
		sale2.setProducts(new ArrayList<Product>());
		sale2.setTotal(10.0);
		
		List<Sale> sales = new ArrayList<Sale>();
		sales.add(sale1);
		sales.add(sale2);
		
		try {
			ManagerReport rel1 =  ManagerReportFactory.newInstanceSales(sales);
			rel1.generateReport();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}

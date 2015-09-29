package br.com.wgengenharia.manager.dao;

import java.util.Calendar;
import java.util.List;

import br.com.wgengenharia.manager.dao.model.DAOInterface;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Sale;


public interface SaleDAO extends DAOInterface<Sale, Integer>{

	public List<Sale> listSalesDayByBranch(Calendar day, Branch branch);
	
	public List<Sale> listSales();
	
}

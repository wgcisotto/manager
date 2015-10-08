package br.com.wgengenharia.manager.dao;

import java.util.Date;
import java.util.List;

import br.com.wgengenharia.manager.dao.model.DAOInterface;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Sale;


public interface SaleDAO extends DAOInterface<Sale, Integer>{

	public List<Sale> listSalesDayByBranch(Date day, Branch branch);
	
	public List<Sale> listSales();
	
}

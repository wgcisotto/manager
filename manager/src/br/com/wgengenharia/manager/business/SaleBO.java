package br.com.wgengenharia.manager.business;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.SaleDAO;
import br.com.wgengenharia.manager.dao.SaleDAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Sale;

public class SaleBO implements SaleDAO{

	private SaleDAO DAO;
	
	public SaleBO(EntityManager em) {
		DAO = new SaleDAOImpl(em);
	}
	
	
	@Override
	public void insert(Sale entity) {
		DAO.insert(entity);
	}

	@Override
	public void update(Sale entity) {
		DAO.update(entity);
	}

	@Override
	public void delete(Sale entity) {
		DAO.delete(entity);
	}

	@Override
	public Sale findById(Integer id) {
		return DAO.findById(id);
	}


	@Override
	public List<Sale> listSalesDayByBranch(Date day, Branch branch) {
		return DAO.listSalesDayByBranch(day, branch);
	}


	@Override
	public List<Sale> listSales() {
		return DAO.listSales();
	}


	@Override
	public List<Sale> listByBranch(Branch branch) {
		return DAO.listByBranch(branch);
	}


	@Override
	public List<Sale> listSalesFiltered(Date begin, Date end, Branch branch) {
		return DAO.listSalesFiltered(begin, end, branch);
	}
	

}

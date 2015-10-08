package br.com.wgengenharia.manager.dao;

import java.util.List;

import br.com.wgengenharia.manager.dao.model.DAOInterface;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Company;

public interface BranchDAO extends DAOInterface<Branch, Integer> {
	
	public List<Branch> listByCompany(Company company);
	

}

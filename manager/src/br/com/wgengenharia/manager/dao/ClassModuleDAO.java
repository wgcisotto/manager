package br.com.wgengenharia.manager.dao;

import java.util.List;

import br.com.wgengenharia.manager.dao.model.DAOInterface;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.ClassModule;
import br.com.wgengenharia.manager.model.Company;

public interface ClassModuleDAO extends DAOInterface<ClassModule, Integer> {

	
	public List<ClassModule> listModulesByCompany(Company company);
	
	public List<ClassModule> listModulesByCompanyAndBranch(Company company, Branch branch);
	
}

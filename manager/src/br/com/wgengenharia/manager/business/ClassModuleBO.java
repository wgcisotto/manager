package br.com.wgengenharia.manager.business;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.ClassModuleDAO;
import br.com.wgengenharia.manager.dao.ClassModuleDAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.ClassModule;
import br.com.wgengenharia.manager.model.Company;

public class ClassModuleBO implements ClassModuleDAO {

	private ClassModuleDAO DAO;
	
	public ClassModuleBO(EntityManager em) {
		DAO = new ClassModuleDAOImpl(em);
	}
	
	@Override
	public void insert(ClassModule entity) {
		DAO.insert(entity);
	}

	@Override
	public void update(ClassModule entity) {
		DAO.update(entity);
	}

	@Override
	public void delete(ClassModule entity) {
		DAO.delete(entity);
	}

	@Override
	public ClassModule findById(Integer id) {
		return DAO.findById(id);
	}

	@Override
	public List<ClassModule> listModulesByCompany(Company company) {
		return DAO.listModulesByCompany(company);
	}

	@Override
	public List<ClassModule> listModulesByCompanyAndBranch(Company company,
			Branch branch) {
		return DAO.listModulesByCompanyAndBranch(company, branch);
	}

	@Override
	public List<ClassModule> listByBranch(Branch branch) {
		return DAO.listByBranch(branch);
	}

}

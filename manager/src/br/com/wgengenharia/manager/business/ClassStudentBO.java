package br.com.wgengenharia.manager.business;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.wgengenharia.manager.dao.ClassStudentDAO;
import br.com.wgengenharia.manager.dao.ClassStudentDAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.ClassModule;
import br.com.wgengenharia.manager.model.ClassStudent;
import br.com.wgengenharia.manager.model.Company;
import br.com.wgengenharia.manager.model.Student;

public class ClassStudentBO implements ClassStudentDAO{

	private ClassStudentDAO DAO;
	private ClassModuleBO BO;
	
	public ClassStudentBO(EntityManager em) {
		DAO = new ClassStudentDAOImpl(em);
		BO = new ClassModuleBO(em);
	}
	
	
	@Override
	public void insert(ClassStudent entity) {
		DAO.insert(entity);
	}

	@Override
	public void update(ClassStudent entity) {
		if(entity.getQuantity_call() > entity.getClass_module().getQuantity_class()){
			try {
				ClassModule newModule = BO.findByClassModuleSequence(entity.getClass_module().getNextSequence());
				entity.setClass_module(newModule);
				entity.setQuantity_call(1);
			} catch (NoResultException e) {
				entity.alterStatusClass();
			}
		}
		DAO.update(entity);
	}

	@Override
	public void delete(ClassStudent entity) {
		DAO.delete(entity);
	}

	@Override
	public ClassStudent findById(Integer id) {
		return DAO.findById(id);
	}


	@Override
	public List<ClassStudent> listClassStudentsByCompany(Company company) {
		return DAO.listClassStudentsByCompany(company);
	}


	@Override
	public List<ClassStudent> listClassStudentsByCompanyAndBranch(
			Company company, Branch branch) {
		return DAO.listClassStudentsByCompanyAndBranch(company, branch);
	}


	@Override
	public Integer findClassIdByStudent(Student student) {
		return DAO.findClassIdByStudent(student);
	}


	@Override
	public List<ClassStudent> listByBranch(Branch branch) {
		return DAO.listByBranch(branch);
	}
}

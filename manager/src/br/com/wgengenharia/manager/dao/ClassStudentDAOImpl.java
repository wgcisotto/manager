package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.ClassStudent;
import br.com.wgengenharia.manager.model.Company;

public class ClassStudentDAOImpl extends DAOImpl<ClassStudent, Integer> implements ClassStudentDAO{

	public ClassStudentDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<ClassStudent> listClassStudentsByCompany(Company company) {
		TypedQuery<ClassStudent> query = em.createQuery("from ClassStudent cs where cs.company = :company", ClassStudent.class);
		query.setParameter("company", company);
    return query.getResultList();
	}

	@Override
	public List<ClassStudent> listClassStudentsByCompanyAndBranch(Company company,
			Branch branch) {
		TypedQuery<ClassStudent> query = em.createQuery("from ClassStudent cs where cs.company = :company and cs.branch = :branch", ClassStudent.class);
		query.setParameter("company", company);
		query.setParameter("brach", branch);
    return query.getResultList();
	}

}

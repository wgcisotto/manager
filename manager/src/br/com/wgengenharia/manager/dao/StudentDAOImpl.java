package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.ClassModule;
import br.com.wgengenharia.manager.model.Company;
import br.com.wgengenharia.manager.model.Student;

public class StudentDAOImpl extends DAOImpl<Student, Integer> implements StudentDAO{

	public StudentDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Student> listStudentByCompany(Company company) {
		TypedQuery<Student> query = em.createQuery("from Student s where s.company = :company", Student.class);
		query.setParameter("company", company);
    return query.getResultList();
	}

	@Override
	public List<Student> listStudentByCompanyAndBranch(Company company,
			Branch branch) {
		TypedQuery<Student> query = em.createQuery("from Student s where s.company = :company and s.branch = :branch", Student.class);
		query.setParameter("company", company);
		query.setParameter("brach", branch);
    return query.getResultList();
	}

}

package br.com.wgengenharia.manager.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.ClassStudent;
import br.com.wgengenharia.manager.model.Company;
import br.com.wgengenharia.manager.model.Student;

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

	@Override
	public Integer findClassIdByStudent(Student student) {
		Query q = em.createNativeQuery("SELECT CL.ID_CLASS_STUDENT FROM TAB_CLASS_STUDENT CS, TAB_CLASS CL WHERE CS.ID_STUDENT = "+student.getId_student()+" AND CL.ID_CLASS_STUDENT = CS.ID_CLASS_STUDENT");
		if(q.getResultList().size() == 0){
			return -1;
		}
		BigDecimal result = (BigDecimal)q.getSingleResult();
		return result.intValue();
	}

	@Override
	public List<ClassStudent> listByBranch(Branch branch) {
		TypedQuery<ClassStudent> query = em.createQuery("from ClassStudent o where o.branch = :branch", ClassStudent.class);
		query.setParameter("branch", branch);
    return query.getResultList();
	}

}

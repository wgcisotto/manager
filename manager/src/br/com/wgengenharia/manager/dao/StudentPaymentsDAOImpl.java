package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Student;
import br.com.wgengenharia.manager.model.StudentPayments;

public class StudentPaymentsDAOImpl extends DAOImpl<StudentPayments, Integer> implements StudentPaymentsDAO {

	public StudentPaymentsDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<StudentPayments> listStudentPayments(Student student) {
		TypedQuery<StudentPayments> query = em.createQuery("from StudentPayments sp where sp.student = :student", StudentPayments.class);
		query.setParameter("student", student);
    return query.getResultList();
	}

}

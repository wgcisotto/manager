package br.com.wgengenharia.manager.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
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

	@Override
	public List<StudentPayments> listByBranch(Branch branch) {
		TypedQuery<StudentPayments> query = em.createQuery("from StudentPayments o where o.branch = :branch", StudentPayments.class);
		query.setParameter("branch", branch);
    return query.getResultList();
	}

	@Override
	public List<StudentPayments> listStudentPaymentsLate(Calendar day,
			Branch branch) {
		TypedQuery<StudentPayments> query = em.createQuery("from StudentPayments o where o.branch = :branch and o.expiry_date < :day and o.paid = 0", StudentPayments.class);
		query.setParameter("branch", branch);
		query.setParameter("day", day.getTime());
    return query.getResultList();
	}

	@Override
	public StudentPayments findByBarcode(String barcode) {
		TypedQuery<StudentPayments> query = em.createQuery("from StudentPayments o where o.barcode = :barcode", StudentPayments.class);
		query.setParameter("barcode", barcode);
    return query.getSingleResult();
	}

}

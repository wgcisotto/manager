package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.FollowUp;
import br.com.wgengenharia.manager.model.Student;

public class FollowUpDAOImpl extends DAOImpl<FollowUp, Integer> implements FollowUpDAO {

	public FollowUpDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<FollowUp> listFollowUpByStudent(Student student) {
		TypedQuery<FollowUp> query = em.createQuery("from FollowUp f where f.student = :student", FollowUp.class);
		query.setParameter("student", student);
    return query.getResultList();
	}

	@Override
	public List<FollowUp> listByBranch(Branch branch) {
		TypedQuery<FollowUp> query = em.createQuery("from FollowUp o where o.branch = :branch", FollowUp.class);
		query.setParameter("branch", branch);
    return query.getResultList();
	}

}

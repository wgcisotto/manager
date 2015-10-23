package br.com.wgengenharia.manager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.wgengenharia.manager.dao.model.DAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.StudentInfo;

public class StudentInfoImpl extends DAOImpl<StudentInfo, Integer> implements StudentInfoDAO {

	public StudentInfoImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<StudentInfo> listByBranch(Branch branch) {
		TypedQuery<StudentInfo> query = em.createQuery("from StudentInfo s where s.branch = :branch", StudentInfo.class);
		query.setParameter("branch", branch);
    return query.getResultList();
	}

}

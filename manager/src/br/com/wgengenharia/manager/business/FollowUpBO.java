package br.com.wgengenharia.manager.business;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.FollowUpDAO;
import br.com.wgengenharia.manager.dao.FollowUpDAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.FollowUp;
import br.com.wgengenharia.manager.model.Student;

public class FollowUpBO implements FollowUpDAO {


	private FollowUpDAO DAO;
	
	public FollowUpBO(EntityManager em) {
		DAO = new FollowUpDAOImpl(em);
	}
	
	@Override
	public void insert(FollowUp entity) {
		DAO.insert(entity);
	}

	@Override
	public void update(FollowUp entity) {
		DAO.update(entity);
	}

	@Override
	public void delete(FollowUp entity) {
		DAO.delete(entity);
	}

	@Override
	public FollowUp findById(Integer id) {
		return DAO.findById(id);
	}

	@Override
	public List<FollowUp> listFollowUpByStudent(Student student) {
		return DAO.listFollowUpByStudent(student);
	}

	@Override
	public List<FollowUp> listByBranch(Branch branch) {
		return DAO.listByBranch(branch);
	}

}

package br.com.wgengenharia.manager.dao;

import java.util.List;

import br.com.wgengenharia.manager.dao.model.DAOInterface;
import br.com.wgengenharia.manager.model.FollowUp;
import br.com.wgengenharia.manager.model.Student;

public interface FollowUpDAO extends DAOInterface<FollowUp, Integer> {
	
	public List<FollowUp> listFollowUpByStudent(Student student);

}

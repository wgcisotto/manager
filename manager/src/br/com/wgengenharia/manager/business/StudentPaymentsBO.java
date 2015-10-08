package br.com.wgengenharia.manager.business;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.wgengenharia.manager.dao.StudentPaymentsDAO;
import br.com.wgengenharia.manager.dao.StudentPaymentsDAOImpl;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Student;
import br.com.wgengenharia.manager.model.StudentPayments;

public class StudentPaymentsBO implements StudentPaymentsDAO {

	private StudentPaymentsDAO DAO;
	
	public StudentPaymentsBO(EntityManager em) {
		DAO = new StudentPaymentsDAOImpl(em);
	}
	
	@Override
	public void insert(StudentPayments entity) {
		DAO.insert(entity);
	}

	@Override
	public void update(StudentPayments entity) {
		DAO.update(entity);
	}

	@Override
	public void delete(StudentPayments entity) {
		DAO.delete(entity);
	}

	@Override
	public StudentPayments findById(Integer id) {
		return DAO.findById(id);
	}

	@Override
	public List<StudentPayments> listStudentPayments(Student student) {
		return DAO.listStudentPayments(student);
	}

	@Override
	public List<StudentPayments> listByBranch(Branch branch) {
		return DAO.listByBranch(branch);
	}

	@Override
	public List<StudentPayments> listStudentPaymentsLate(Calendar day,Branch branch) {
		return DAO.listStudentPaymentsLate(day, branch);
	}

	@Override
	public StudentPayments findByBarcode(String barcode) {
		return DAO.findByBarcode(barcode);
	}

	
	
	
}

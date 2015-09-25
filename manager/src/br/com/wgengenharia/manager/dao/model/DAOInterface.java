package br.com.wgengenharia.manager.dao.model;

import java.util.List;

import br.com.wgengenharia.manager.model.Branch;



public interface DAOInterface<T,K> {

	void insert(T entity);
	
	void update(T entity);
	
	void delete (T entity);
		
	T findById(K id);
	
	List<T> listByBranch(Branch branch);
	
}

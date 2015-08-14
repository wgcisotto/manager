package br.com.wgengenharia.manager.dao.model;



public interface DAOInterface<T,K> {

	void insert(T entity);
	
	void update(T entity);
	
	void delete (T entity);
		
	T findById(K id);
	
}

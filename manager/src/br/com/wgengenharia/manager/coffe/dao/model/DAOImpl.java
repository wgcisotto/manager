package br.com.wgengenharia.manager.coffe.dao.model;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;


public abstract class DAOImpl<T,K> implements DAOInterface<T,K> {

	protected EntityManager em;
	
	private Class<T> entityClass;
	
	//construtor
	@SuppressWarnings("all")
	public DAOImpl(EntityManager entityManager){
		this.em = entityManager;
		this.entityClass = (Class) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	};
	
	
	
	@Override
	public void insert(T entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
	}

	@Override
	public void delete(T entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}

	@Override
	public void update(T entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public T findById(K id) {
		return em.find(entityClass, id);
	}
	
	
	

}

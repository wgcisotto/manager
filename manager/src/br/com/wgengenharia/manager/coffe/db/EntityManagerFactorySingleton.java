package br.com.wgengenharia.manager.coffe.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {

	private static EntityManagerFactory factory;
	
	private EntityManagerFactorySingleton(){
		super();
	};
	
	public static EntityManagerFactory getInstance(){
		if (factory == null){
			factory = Persistence.createEntityManagerFactory("PERSISTENCIA_ORACLE");
		}
		return factory;
	}
}

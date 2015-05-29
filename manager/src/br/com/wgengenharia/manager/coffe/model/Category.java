package br.com.wgengenharia.manager.coffe.model;

import java.io.Serializable;

public class Category implements CategoryInterface, Serializable {
	
	private static final long serialVersionUID = 1709126584637764224L;
	
	private int id;
	private String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

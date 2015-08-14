package br.com.wgengenharia.manager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TAB_CATEGORY")
@SequenceGenerator(name="seqCategory", sequenceName="SEQ_CATEGORY",allocationSize=1)
public class Category implements Serializable {
	
	private static final long serialVersionUID = 1709126584637764224L;
	
	@Id
	@Column(name="ID_CATEGORY")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqCategory")
	private int id_category;
	@Column(name="NAME", length=100)
	private String name;
	@Column(name="DESCRIPTION", length=300)
	private String description;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId_category() {
		return id_category;
	}

	public void setId_category(int id_category) {
		this.id_category = id_category;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}

package br.com.wgengenharia.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_CLASS_MODULE")
//@SequenceGenerator(name="seqClassModule", sequenceName="SEQ_CLASSMODULE",allocationSize=1)
public class ClassModule {

	@Id
	@Column(name="ID_CLASS_MODULE")
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqClassModule")
	private int id_class_module;
	
	@Column(name="NAME", length=100)
	private String name;
	
	@Column(name="DESCRIPTION", length=100)
	private String description;
	
	@Column(name="QUANTITY_CLASS")
	private int quantity_class;
	
	@Column(name="CLASS_SEQUENCE")
	private int sequence;

	@ManyToOne
	private Company company;
	
	@ManyToOne
	private Branch branch;
	

	
	public int getId_class_module() {
		return id_class_module;
	}
	public void setId_class_module(int id_class_module) {
		this.id_class_module = id_class_module;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity_class() {
		return quantity_class;
	}
	public void setQuantity_class(int quantity_class) {
		this.quantity_class = quantity_class;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
}

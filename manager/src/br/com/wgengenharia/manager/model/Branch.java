package br.com.wgengenharia.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_BRANCH")
//@SequenceGenerator(name="seqBranch", sequenceName="SEQ_BRANCH",allocationSize=1)
public class Branch {
	
	public Branch() {
		this.address = new Address();
	}

	@Id
	@Column(name="ID_BRANCH")
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqBranch")
	private int id_branch;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PHONE")
	private String phone;
	
	@ManyToOne
	private Company company;
	
	@ManyToOne
	private Address address;

	
	public int getId_branch() {
		return id_branch;
	}
	public void setId_branch(int id_branch) {
		this.id_branch = id_branch;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}

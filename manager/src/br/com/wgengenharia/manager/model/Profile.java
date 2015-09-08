package br.com.wgengenharia.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_PROFILE")
//@SequenceGenerator(name="seqProfile", sequenceName="SEQ_PROFILE",allocationSize=1)
public class Profile {
	
	
	@Id
	@Column(name="ID_PROFILE")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqProfile")
	private int id_profile;
	@Column(name="NAME")
	private String name;
	@ManyToOne
	private Company company;

	public int getId_profile() {
		return id_profile;
	}
	public void setId_profile(int id_profile) {
		this.id_profile = id_profile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
}

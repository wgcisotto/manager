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
@Table(name = "TAB_STUDENT")
@SequenceGenerator(name="seqStudent", sequenceName="SEQ_STUDENT",allocationSize=1)
public class Student {
	@Id
	@Column(name="ID_STUDENT")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqStudent")
	private int id_student;
	
	@Column(name="NAME", length=100)
	private String name;
	
	@Column(name="PHONE", length=100)
	private String phone;
	
	@ManyToOne
	private Address address;

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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}

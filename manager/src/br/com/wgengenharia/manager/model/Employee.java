package br.com.wgengenharia.manager.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TAB_EMPLOYEE")
@SequenceGenerator(name="seqEmployee", sequenceName="SEQ_EMPLOYEE",allocationSize=1)
public class Employee {

	private static final String EMPLOYEE_ADMIN = "Administrador"; 
	
	
	public Employee() {
		this.address = new Address();
	}
	
	@Id
	@Column(name="ID_EMPLOYEE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqEmployee")
	private int id_employee;

	@Column(name="NAME", nullable=false)
	private String name;
	
	@Column(name="PHONE", nullable=false)
	private String phone;

	@ManyToOne
	private Company company;
	
	@ManyToOne
	private Branch branch;
	
	@Column(name="USER_NAME", nullable=false)
	private String user;
	
	@Column(name="PASS", nullable=false )
	private String pass;
	
	@ManyToOne
	private Profile profile;
	
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getId_employee() {
		return id_employee;
	}
	public void setId_employee(int id_employee) {
		this.id_employee = id_employee;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public boolean getIsAdmin(){
		if(profile.getName().equals(EMPLOYEE_ADMIN)) return true;
		return false;
	}
	
}

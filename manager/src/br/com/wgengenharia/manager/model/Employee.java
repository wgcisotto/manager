package br.com.wgengenharia.manager.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TAB_EMPLOYEE")
@SequenceGenerator(name="seqEmployee", sequenceName="SEQ_EMPLOYEE",allocationSize=1)
public class Employee {

	public Employee() {
		this.branchs = new ArrayList<Branch>();
	}
	
	@Id
	@Column(name="ID_EMPLOYEE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqEmployee")
	private int id_employee;

	@Column(name="NAME", nullable=false)
	private String name;

  @OneToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name="COMPANY", nullable=false)
	private Company company;
  
  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
  @JoinTable(name="TAB_EMPLOYEE_BRANCH",joinColumns={@JoinColumn(name="EMPLOYEE_ID", referencedColumnName="ID_EMPLOYEE")}, inverseJoinColumns={@JoinColumn(name="BRANCH_ID", referencedColumnName="ID_BRANCH")})
	private List<Branch> branchs;
	
	@Column(name="USER_NAME", nullable=false)
	private String user;
	
	@Column(name="PASS", nullable=false )
	private String pass;

	
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
	public List<Branch> getBranchs() {
		return branchs;
	}
	public void setBranchs(List<Branch> branchs) {
		this.branchs = branchs;
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

	//metodos
	
	public void addBranch(Branch branch){
		this.branchs.add(branch);
	}
	
}

package br.com.wgengenharia.manager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TAB_STUDENT")
//@SequenceGenerator(name="seqStudent", sequenceName="SEQ_STUDENT",allocationSize=1)
public class Student {
	
	public Student() {
		this.resp_address = new Address();
	}
	
	@Id
	@Column(name="ID_STUDENT")
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqStudent")
	private int id_student;
	
	@Column(name="STUDENT_NAME", length=100)
	private String student_name;
	
	@Temporal(TemporalType.DATE)
	private Date student_birth_date;
	
	@Column(name="STUDENT_EMAIL", length=100)
	private String email;
	
	@Column(name="RESP_NAME", length=100)
	private String resp_Name;
	
	@Column(name="RESP_RG", length=100)
	private String resp_rg;
	
	@Column(name="RESP_CPF", length=100)
	private String resp_cpf;
	
	@Temporal(TemporalType.DATE)
	private Date resp_birth_date;
	
	@ManyToOne
	private Address resp_address;
	
	@Column(name="RESP_HOME_PHONE", length=100)
	private String resp_home_phone;

	
	@Column(name="RESP_OFFICE_PHONE", length=100)
	private String resp_office_phone;

	@ManyToOne
	private Company company;
	
	@ManyToOne
	private Branch branch;

	public int getId_student() {
		return id_student;
	}
	public void setId_student(int id_student) {
		this.id_student = id_student;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public Date getStudent_birth_date() {
		return student_birth_date;
	}
	public void setStudent_birth_date(Date student_birth_date) {
		this.student_birth_date = student_birth_date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getResp_Name() {
		return resp_Name;
	}
	public void setResp_Name(String resp_Name) {
		this.resp_Name = resp_Name;
	}
	public String getResp_rg() {
		return resp_rg;
	}
	public void setResp_rg(String resp_rg) {
		this.resp_rg = resp_rg;
	}
	public String getResp_cpf() {
		return resp_cpf;
	}
	public void setResp_cpf(String resp_cpf) {
		this.resp_cpf = resp_cpf;
	}
	public Date getResp_birth_date() {
		return resp_birth_date;
	}
	public void setResp_birth_date(Date resp_birth_date) {
		this.resp_birth_date = resp_birth_date;
	}
	public Address getResp_address() {
		return resp_address;
	}
	public void setResp_address(Address resp_address) {
		this.resp_address = resp_address;
	}
	public String getResp_home_phone() {
		return resp_home_phone;
	}
	public void setResp_home_phone(String resp_home_phone) {
		this.resp_home_phone = resp_home_phone;
	}
	public String getResp_office_phone() {
		return resp_office_phone;
	}
	public void setResp_office_phone(String resp_office_phone) {
		this.resp_office_phone = resp_office_phone;
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

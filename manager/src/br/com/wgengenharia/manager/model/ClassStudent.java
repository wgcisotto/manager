package br.com.wgengenharia.manager.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TAB_CLASS")
//@SequenceGenerator(name="seqClassStudent", sequenceName="SEQ_CLASSSTUDENT",allocationSize=1)
public class ClassStudent {

	@Id
	@Column(name="ID_CLASS_STUDENT")
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqClassStudent")
	private int id_class_student;
	
	@Column(name="CLASS_NAME", length=100)
	private String class_name;
	
	@ManyToMany
  @JoinTable(name="TAB_CLASS_STUDENT", joinColumns={@JoinColumn(name="ID_CLASS_STUDENT")}, inverseJoinColumns={@JoinColumn(name="ID_STUDENT")})
	private List<Student> students;
	
	@Temporal(TemporalType.DATE)
	private Date start_date;
	
	@Temporal(TemporalType.TIME)
	private Date begin_time;
	
	@Temporal(TemporalType.TIME)
	private Date end_time;
	
	@ManyToOne
	private ClassModule class_module;

	@ManyToOne
	private Company company;
	
	@ManyToOne
	private Branch branch;

	public int getId_class_student() {
		return id_class_student;
	}

	public void setId_class_student(int id_class_student) {
		this.id_class_student = id_class_student;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public ClassModule getClass_module() {
		return class_module;
	}

	public void setClass_module(ClassModule class_module) {
		this.class_module = class_module;
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

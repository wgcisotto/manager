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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_CLASS")
@SequenceGenerator(name="seqClassStudent", sequenceName="SEQ_CLASSSTUDENT",allocationSize=1)
public class ClassStudent {

	@Id
	@Column(name="ID_CLASS_STUDENT")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqClassStudent")
	private int id_class_student;
	
	@Column(name="MODULE_NAME", length=100)
	private String module_name;
	
	@ManyToMany
  @JoinTable(name="TAB_CLASS_STUDENT", joinColumns={@JoinColumn(name="ID_CLASS_STUDENT")}, inverseJoinColumns={@JoinColumn(name="ID_STUDENT")})
	private List<Student> students;
	
	@Column(name="START_DATE", length=100)
	private Date start_date;
	
	@Column(name="BEGIN_TIME", length=100)
	private Date begin_time;
	
	@Column(name="END_TIME", length=100)
	private Date end_time;
	
	@ManyToOne
	private ClassModule class_module;

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
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
}

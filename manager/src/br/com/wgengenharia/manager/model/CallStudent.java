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
@Table(name = "TAB_CALLS")
@SequenceGenerator(name="seqCalls", sequenceName="SEQ_CALLS",allocationSize=1)
public class CallStudent {


	@Id
	@Column(name="ID_CALLS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqCalls")
	private int id_calls;

	@ManyToOne
	private ClassStudent class_Student;
	
	@ManyToMany
  @JoinTable(name="TAB_CALLS_STUDENT_PRESENT", joinColumns={@JoinColumn(name="ID_CLASS_STUDENT")}, inverseJoinColumns={@JoinColumn(name="ID_STUDENT")})
	private List<Student> students_present;
	
	@ManyToMany
  @JoinTable(name="TAB_CALLS_STUDENT", joinColumns={@JoinColumn(name="ID_CALS_STUDENT")}, inverseJoinColumns={@JoinColumn(name="ID_STUDENT")})
	private List<Student> students;
	
	@Column(name="DATE", length=100)
	private Date date;
	
	@ManyToOne
	private Employee teacher;

	public ClassStudent getClass_Student() {
		return class_Student;
	}

	public void setClass_Student(ClassStudent class_Student) {
		this.class_Student = class_Student;
	}

	public List<Student> getStudents_present() {
		return students_present;
	}

	public void setStudents_present(List<Student> students_present) {
		this.students_present = students_present;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Employee getTeacher() {
		return teacher;
	}

	public void setTeacher(Employee teacher) {
		this.teacher = teacher;
	}
	
}
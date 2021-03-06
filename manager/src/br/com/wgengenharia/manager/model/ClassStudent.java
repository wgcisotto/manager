package br.com.wgengenharia.manager.model;

import java.util.ArrayList;
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

	private static final int QUANTITY_CLASS_DEFAULT = 1;
	private static final boolean CONCLUDED_FALSE = false;
	private static final boolean CONCLUDED_TRUE = true;
	
	public ClassStudent() {
		this.students = new ArrayList<Student>();
		this.calls = new ArrayList<CallStudent>();
		this.quantity_call = QUANTITY_CLASS_DEFAULT;
		this.concluded = CONCLUDED_FALSE;
	}
	
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
	
	@ManyToMany
  @JoinTable(name="TAB_CLASS_CALL", joinColumns={@JoinColumn(name="CLASS_STUDENT_ID")}, inverseJoinColumns={@JoinColumn(name="CALL_ID")})
	private List<CallStudent> calls;
	
	@Temporal(TemporalType.DATE)
	private Date start_date;
	
	@Temporal(TemporalType.TIME)
	private Date begin_time;
	
	@Temporal(TemporalType.TIME)
	private Date end_time;
	
	@Column(name="QUANTITY_CALL", nullable=true)
	private Integer quantity_call;
	
	@Column(name="CLASS_CONCLUDED")
	private boolean concluded;
	
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
	
	public List<CallStudent> getCalls() {
		return calls;
	}

	public void setCalls(List<CallStudent> calls) {
		this.calls = calls;
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
	
	public Integer getQuantity_call() {
		return quantity_call;
	}

	public void setQuantity_call(Integer quantity_call) {
		this.quantity_call = quantity_call;
	}
	
	public boolean isConcluded() {
		return concluded;
	}

	public void setConcluded(boolean concluded) {
		this.concluded = concluded;
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
	
	public void addStudent(Student student){
		this.students.add(student);
	}
	
	public void removeStudent(Student student){
		if(students.contains(student))
			this.students.remove(student);
	}
	
	public void addCall(CallStudent call){
		this.calls.add(call);
	}
	
	public void updateClassControl(){
		this.quantity_call +=1;
	}
	
	public boolean gethasNoStudents(){
		if(students.size() > 0) return false;
		return true;
	}

	public void alterStatusClass(){
		this.concluded = CONCLUDED_TRUE;
	}
	
}
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


@Entity
@Table(name = "TAB_CALL")
//@SequenceGenerator(name="seqCalls", sequenceName="SEQ_CALLS",allocationSize=1)
public class CallStudent {

	public CallStudent() {
		students_info = new ArrayList<StudentInfo>();
	}

	@Id
	@Column(name="ID_CALL")
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqCalls")
	private int id_calls;

	@ManyToMany
  @JoinTable(name="TAB_CALL_STUDENT_INFO",
  	joinColumns={@JoinColumn(name="CALL_ID")}, 
  	inverseJoinColumns={@JoinColumn(name="INFO_ID")})
	private List<StudentInfo> students_info;
	
	@Column(name="CALL_DATE", length=100)
	private Date call_date;
	
	@ManyToOne
	private Employee teacher;
	
	@ManyToOne
	private Branch branch;

	public int getId_calls() {
		return id_calls;
	}

	public void setId_calls(int id_calls) {
		this.id_calls = id_calls;
	}

	public List<StudentInfo> getStudents_info() {
		return students_info;
	}

	public void setStudents_info(List<StudentInfo> students_info) {
		this.students_info = students_info;
	}

	public Date getCall_date() {
		return call_date;
	}

	public void setCall_date(Date call_date) {
		this.call_date = call_date;
	}

	public Employee getTeacher() {
		return teacher;
	}

	public void setTeacher(Employee teacher) {
		this.teacher = teacher;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	
	public void addStudentsInfo(List<Student> student){
		for (Student stud : student) {
			StudentInfo studentInfo = new StudentInfo();
			studentInfo.setStudent_name(stud.getStudent_name());
			students_info.add(studentInfo);
		}
	}
	
	
}

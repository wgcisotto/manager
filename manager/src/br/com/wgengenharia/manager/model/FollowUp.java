package br.com.wgengenharia.manager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "TAB_FOLLOWUP")
//@SequenceGenerator(name="seqFollowup", sequenceName="SEQ_FOLLOWUP",allocationSize=1)
public class FollowUp {
	
	@Id
	@Column(name="ID_FOLLOWUP")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqFollowup")
	private int id_followup;
	@Temporal(TemporalType.DATE)
	private Date date_followup;
	@Column(name="FOLLOWUP")
	private String followup;
	@ManyToOne
	private Student student;
	@ManyToOne
	private Employee employee;
	
	public int getId_followup() {
		return id_followup;
	}
	public void setId_followup(int id_followup) {
		this.id_followup = id_followup;
	}
	public Date getDate_followup() {
		return date_followup;
	}
	public void setDate_followup(Date date_followup) {
		this.date_followup = date_followup;
	}
	public String getFollowup() {
		return followup;
	}
	public void setFollowup(String followup) {
		this.followup = followup;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}

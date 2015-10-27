package br.com.wgengenharia.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_STUDENT_INFO")
public class StudentInfo {
	
	
	private static final String STATUS_STUDENT_AUSENTE = "Ausente";
	private static final String STATUS_STUDENT_PRESENTE = "Presente";
	
	
	public StudentInfo() {
		this.status = STATUS_STUDENT_AUSENTE;
		this.info  = "";
	}

	@Id
	@Column(name="ID_INFO")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_student_info;
	
	@Column(name="STUDENT_NAME")
	private String student_name;
	
	@Column(name="INFO")
	private String info;
	
	@Column(name="STATUS")
	private String status;

	public int getId_student_info() {
		return id_student_info;
	}
	public void setId_student_info(int id_student_info) {
		this.id_student_info = id_student_info;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void alterStatus(){
		this.status = STATUS_STUDENT_PRESENTE;
	}
}

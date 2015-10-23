package br.com.wgengenharia.manager.report.model;

public class PaymentReport {

	private int id_contract;
	private String student_name;
	private String expiry_date;
	private String phone;
	private String curso;
	private String parcel;
	private String value;
	
	public int getId_contract() {
		return id_contract;
	}
	public void setId_contract(int id_contract) {
		this.id_contract = id_contract;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getParcel() {
		return parcel;
	}
	public void setParcel(String parcel) {
		this.parcel = parcel;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

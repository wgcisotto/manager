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
@Table(name = "TAB_STUDENT_PAYMENTS")
public class StudentPayments {
	
	private static final String STATUS_PENDENTE = "PENDENTE";
	private static final String STATUS_ATRASADO = "ATRASADO";
	private static final String STATUS_PAGO = "PAGO";
	
	@Id
	@Column(name="ID_STUDENT_PAYMENT")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_student_payments;
	
	@Temporal(TemporalType.DATE)
	private Date expiry_date;

	@Column(name="PRICE")
	private double price;
	
	@Column(name="NUMBER_PARCEL")
	private int number_parcel;
	
	@Column(name="PAID")
	private double paid;
	
	@Column(name="PAYMENT_DATE")
	private Date payment_date;
	
	@ManyToOne
	private Student student;
	
	@ManyToOne
	private Company company;
	
	@ManyToOne
	private Branch branch;

	public int getId_student_payments() {
		return id_student_payments;
	}
	public void setId_student_payments(int id_student_payments) {
		this.id_student_payments = id_student_payments;
	}
	public Date getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNumber_parcel() {
		return number_parcel;
	}
	public void setNumber_parcel(int number_parcel) {
		this.number_parcel = number_parcel;
	}
	public double getPaid() {
		return paid;
	}
	public void setPaid(double paid) {
		this.paid = paid;
	}
	public Date getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
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
	
	public String getStatus(){
		Date date = new Date();
		if(payment_date != null || paid > 0) return STATUS_PAGO;
		if(expiry_date.after(date)) return STATUS_PENDENTE;
		if(expiry_date.before(date)) return STATUS_ATRASADO;
		return "";
	}
	
}
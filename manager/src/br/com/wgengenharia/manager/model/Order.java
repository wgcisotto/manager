package br.com.wgengenharia.manager.model;

import java.io.Serializable;
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
@Table(name = "TAB_ORDER")
//@SequenceGenerator(name="seqOrder", sequenceName="SEQ_ORDER",allocationSize=1)
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ORDER")
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqOrder")
	private int id_order;
	
	@ManyToMany
  @JoinTable(name="TAB_ORDER_PRODUCT", joinColumns={@JoinColumn(name="ID_ORDER")}, inverseJoinColumns={@JoinColumn(name="ID_PRODUCT")})
	private List<Product> products;
	
	@Column(name="REQUEST_DATE")
	@Temporal(TemporalType.DATE)
	private Date request_date;
	
	@Column(name="DELIVERY_DATE")
	@Temporal(TemporalType.DATE)	
	private Date delivery_date;
	
	@Column(name="TOTAL", length=100)
	private Double total;
	
	@ManyToOne
	private Branch branch;

	public int getId_order() {
		return id_order;
	}
	public void setId_order(int id_order) {
		this.id_order = id_order;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public Date getRequest_date() {
		return request_date;
	}
	public void setRequest_date(Date request_date) {
		this.request_date = request_date;
	}
	public Date getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
}

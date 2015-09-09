package br.com.wgengenharia.manager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TAB_SALE")
//@SequenceGenerator(name="seqSale", sequenceName="SEQ_SALE",allocationSize=1)
public class Sale implements Serializable {
	
	private static final long serialVersionUID = 702365879590135381L;
	
	public Sale() {
		this.products = new ArrayList<Product>();
	}
	
	@Id
	@Column(name="ID_SALE")
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqSale")
	private int id_sale;
	@ManyToMany(cascade=CascadeType.ALL)
  @JoinTable(name="TAB_SALE_PRODUCT",
  	joinColumns={@JoinColumn(name="SALE_ID")}, 
  	inverseJoinColumns={@JoinColumn(name="PRODUCT_ID")})
	private List<Product> products;
	@Column(name = "DATE_SALE")
	@Temporal(TemporalType.DATE)
	private Calendar date;
	@Column(name = "TIME_SALE")
	@Temporal(TemporalType.TIME)
	private Date time;
  @OneToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name="CLIENT", nullable=true)
	private Client client;
	@Column(name = "TOTAL")
	private Double total;
	
	public int getId_sale() {
		return id_sale;
	}
	public void setId_sale(int id_sale) {
		this.id_sale = id_sale;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	
	
	

}

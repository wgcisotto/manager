package br.com.wgengenharia.manager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_PRODUCT")
//@SequenceGenerator(name="seqProduct", sequenceName="SEQ_PRODUCT",allocationSize=1)
public class Product implements Serializable {
	
	private static final long serialVersionUID = -8371022467959133760L;
	
	@Id
	@Column(name="ID_PRODUCT")
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqProduct")
	private int id_product;
	@Column(name="BARCODE", length=100)
	private String barcode;
	@Column(name="NAME", length=50)
	private String name;
	@Column(name="DESCRIPTION", length=100)
	private String description;
	@Column(name="PRICE", length=10)
	private Double price;
	@Column(name="COST", length=10)
	private Double cost;
	@Column(name="LUCRE", length=10)
	private Double lucre;
	@Column(name="QUANTITY", length=10)
	private Integer quantity;
	@Column(name="QUANTITY_ALERT", length=10)
	private Integer quantity_alert;
	@ManyToOne
	private Category Category;
	@ManyToOne
	private Branch branch;

	public int getId_product() {
		return id_product;
	}
	
	public void setId_product(int id_product) {
		this.id_product = id_product;
	}
	
	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getCost() {
		return cost;
	}
	
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	public Double getLucre() {
		return lucre;
	}

	public void setLucre(Double lucre) {
		this.lucre = lucre;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getQuantity_alert() {
		return quantity_alert;
	}

	public void setQuantity_alert(Integer quantity_alert) {
		this.quantity_alert = quantity_alert;
	}

	public Category getCategory() {
		return Category;
	}
	
	public void setCategory(Category category) {
		Category = category;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public void removeFromStock() {
		this.quantity -= 1;
	}

	public void addToStock() {
		this.quantity += 1;
	}
	
	public String getQuant(){
		return quantity.toString();
	}
	
	public void calculateLucre(){
		if(this.price != null && this.cost != null){
			this.lucre = (((price - cost) * 100) / cost);
		}
	}
	
}

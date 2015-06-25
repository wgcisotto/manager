package br.com.wgengenharia.manager.coffe.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_PRODUCT")
@SequenceGenerator(name="seqProduct", sequenceName="SEQ_PRODUCT",allocationSize=1)
public class Product implements ProductInterface, Serializable {
	
	private static final long serialVersionUID = -8371022467959133760L;
	
	@Id
	@Column(name="ID_PRODUCT")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqProduct")
	private int id_product;
	@Column(name="BARCODE", length=100)
	private Integer barcode;
	@Column(name="NAME", length=50, nullable=false)
	private String name;
	@Column(name="DESCRIPTION", length=100)
	private String description;
	@Column(name="PRICE", length=10, nullable=false)
	private Double price;
	@Column(name="COST", length=10, nullable=false)
	private Double cost;
	@Column(name="LUCRE", length=10, nullable=false)
	private Double lucre;
	@Column(name="QUANTITY", length=10, nullable=false)
	private Double quantity;
	@ManyToOne
	private Category Category;

	public int getId_product() {
		return id_product;
	}
	
	public void setId_product(int id_product) {
		this.id_product = id_product;
	}
	
	public Integer getBarcode() {
		return barcode;
	}
	
	public void setBarcode(Integer barcode) {
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

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return Category;
	}
	
	public void setCategory(Category category) {
		Category = category;
	}
	
}

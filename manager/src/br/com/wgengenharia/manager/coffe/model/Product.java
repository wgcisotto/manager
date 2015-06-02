package br.com.wgengenharia.manager.coffe.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "TAB_PRODUCT")
@SequenceGenerator(name="seqProduct", sequenceName="SEQ_PRODUCT",allocationSize=1)
public class Product implements ProductInterface, Serializable {
	
	private static final long serialVersionUID = -8371022467959133760L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqProduct")
	private int id_product;
	@Column(name="barcode", length=100)
	private Integer barcode;
	@Column(name="name", length=50, nullable=false)
	private String name;
	@Column(name="description", length=100)
	private String description;
	@Column(name="price", length=10, nullable=false)
	private Double price;
	@Column(name="cost", length=10, nullable=false)
	private Double cost;
	@Column(name="category")
	private CategoryInterface Category;

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
	
	public CategoryInterface getCategory() {
		return Category;
	}
	
	public void setCategory(CategoryInterface category) {
		Category = category;
	}
	
}

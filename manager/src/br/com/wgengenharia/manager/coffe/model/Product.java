package br.com.wgengenharia.manager.coffe.model;

import java.io.Serializable;

public class Product implements ProductInterface, Serializable {
	
	private static final long serialVersionUID = -8371022467959133760L;
	
	private int id_product;
	private Integer barcode;
	private String name;
	private String description;
	private Double price;
	private Double Cost;
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
		return Cost;
	}
	
	public void setCost(Double cost) {
		Cost = cost;
	}
	
	public CategoryInterface getCategory() {
		return Category;
	}
	
	public void setCategory(CategoryInterface category) {
		Category = category;
	}
	
}

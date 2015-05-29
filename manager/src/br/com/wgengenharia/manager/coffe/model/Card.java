package br.com.wgengenharia.manager.coffe.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Card implements CardInterface, Serializable {
	
	private static final long serialVersionUID = 8533658292055517665L;
	
	private int id;
	private List<Product> products;
	private Double total;
	
	public Card() {
		total = 0.0;
		products = new ArrayList<Product>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void addProduct(Product product){
		products.add(product);
		total = total + product.getPrice();
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

}

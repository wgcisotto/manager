package br.com.wgengenharia.manager.coffe.model;

public class GroupProducts {
	
	private Product product;
	private Integer quantity;
	private Double total;
	
	public GroupProducts(Product product) {
		this.product = product;
		this.quantity = 0;
	}
	
	public Product getProduct() {
		return product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void addProduct(Integer quantity){
		this.quantity += quantity;
	}
	
	public Double getTotal() {
		return (product.getPrice() * quantity);
	}
}

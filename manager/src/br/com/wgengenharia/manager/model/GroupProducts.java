package br.com.wgengenharia.manager.model;

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
	
	public void remove(){
		this.quantity -= 1;
	}
	
	public Double getTotal() {
		return (product.getPrice() * quantity);
	}
}

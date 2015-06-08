package br.com.wgengenharia.manager.coffe.model;


public interface CardInterface {

	public abstract int getId_card();

	public abstract void setId_card(int id_card);

//	public abstract List<Product> getProducts();

//	public abstract void addProduct(Product product);

	public abstract Double getTotal();

	public abstract void setTotal(Double total);

}
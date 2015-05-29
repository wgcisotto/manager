package br.com.wgengenharia.manager.coffe.model;

import java.util.List;

public interface CardInterface {

	public abstract int getId();

	public abstract void setId(int id);

	public abstract List<Product> getProducts();

	public abstract void addProduct(Product product);

	public abstract Double getTotal();

	public abstract void setTotal(Double total);

}
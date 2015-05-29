package br.com.wgengenharia.manager.coffe.model;

public interface ProductInterface {

	public abstract int getId_product();

	public abstract void setId_product(int id_product);

	public abstract Integer getBarcode();

	public abstract void setBarcode(Integer barcode);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getDescription();

	public abstract void setDescription(String description);

	public abstract Double getPrice();

	public abstract void setPrice(Double price);

	public abstract Double getCost();

	public abstract void setCost(Double cost);

	public abstract CategoryInterface getCategory();

	public abstract void setCategory(CategoryInterface category);

}
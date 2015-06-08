package br.com.wgengenharia.manager.coffe.model;

public interface ProductInterface {

	public int getId_product();

	public void setId_product(int id_product);

	public Integer getBarcode();

	public void setBarcode(Integer barcode);

	public String getName();

	public void setName(String name);

	public String getDescription();

	public void setDescription(String description);

	public Double getPrice();

	public void setPrice(Double price);

	public Double getCost();

	public void setCost(Double cost);

  public Category getCategory();

  public void setCategory(Category category);

}
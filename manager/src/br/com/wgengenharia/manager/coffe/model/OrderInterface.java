package br.com.wgengenharia.manager.coffe.model;

import java.util.Date;
import java.util.List;

public interface OrderInterface {

	public int getId_order();

	public void setId_order(int id_order);

	public List<Product> getProducts();

	public void setProducts(List<Product> products);

	public Date getRequest_date();

	public void setRequest_date(Date request_date);

	public Date getDelivery_date();

	public void setDelivery_date(Date delivery_date);

	public Double getTotal();

	public void setTotal(Double total);

}
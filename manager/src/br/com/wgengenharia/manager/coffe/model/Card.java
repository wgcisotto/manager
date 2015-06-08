package br.com.wgengenharia.manager.coffe.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_CARD")
@SequenceGenerator(name="seqCard", sequenceName="SEQ_CARD",allocationSize=1)
public class Card implements CardInterface, Serializable {
	
	private static final long serialVersionUID = 8533658292055517665L;
	
	@Id
	@Column(name="ID_CARD")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqCard")
	private int id_card;
	
	@ManyToMany
  @JoinTable(name="TAB_CARD_PRODUCT", joinColumns={@JoinColumn(name="ID_CARD")}, inverseJoinColumns={@JoinColumn(name="ID_PRODUCT")})
	private List<Product> products;

	@Column(name="TOTAL",length=100)
	private Double total;
	
	public Card() {
		total = 0.0;
		products = new ArrayList<Product>();
	}
	
	public int getId_card() {
		return id_card;
	}
	public void setId_card(int id_card) {
		this.id_card = id_card;
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

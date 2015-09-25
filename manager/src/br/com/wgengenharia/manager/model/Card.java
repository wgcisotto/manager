package br.com.wgengenharia.manager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.primefaces.context.RequestContext;

@Entity
@Table(name = "TAB_CARD")
//@SequenceGenerator(name="seqCard", sequenceName="SEQ_CARD",allocationSize=1)
public class Card implements Serializable {
	
	private static final long serialVersionUID = 8533658292055517665L;
	
	private static final String CARD_CLOSED = "Fechada";
	private static final String CARD_PEND = "Pendente";
	
	public Card() {
		total = 0.0;
		products = new ArrayList<Product>();
		listGrouped = new LinkedHashMap<>();
	}
	
	@Id
	@Column(name="ID_CARD")
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqCard")
	private int id_card;
	
	@ManyToMany
  @JoinTable(name="TAB_CARD_PRODUCT", joinColumns={@JoinColumn(name="ID_CARD")}, inverseJoinColumns={@JoinColumn(name="ID_PRODUCT")})
	private List<Product> products;

	@Column(name="TOTAL",length=100)
	private Double total;
	
	@Transient
	private LinkedHashMap<Integer,GroupProducts> listGrouped;
	
	@ManyToOne
	private Branch branch;
	
	public int getId_card() {
		return id_card;
	}
	public void setId_card(int id_card) {
		this.id_card = id_card;
	}
	public List<Product> getProducts() {
		return products;
	}
	public LinkedHashMap<Integer, GroupProducts> getListGrouped() {
		return listGrouped;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	
	public void addProduct(Product product, Integer qtde){
		
		GroupProducts gp = null;
		if(listGrouped.containsKey(product.getId_product())){
			gp = listGrouped.get(product.getId_product());
			gp.addProduct(qtde);
		}else{
			gp = new GroupProducts(product);
			gp.addProduct(qtde);
			listGrouped.put(product.getId_product(), gp);
		}
		
		for (int i = 0; i < qtde; i++) {
			products.add(product);
			
			// aqui vamos remover os produtos do estoque como esta sendo vendido
			product.removeFromStock();
		}
		
		
	}
	
	
	//metodo criado para a pagina, cashier add produto, nao chamado o addProduct pois pela pagina ao passar o valor 1 
//	teria que mudar o metodo para receber long
	public void add(Product prd){
		addProduct(prd, 1);
		RequestContext.getCurrentInstance().update("formManager:pngListProdCard");
		RequestContext.getCurrentInstance().update("formManager:dtbCardCash");
	}
	
	public void remove(Product prd){
		products.remove(prd);
		prd.addToStock();
		
		if (products.contains(prd)){
			listGrouped.get(prd.getId_product()).remove();
		}else{
			listGrouped.remove(prd.getId_product());
		}
		RequestContext.getCurrentInstance().update("formManager:pngListProdCard");
		RequestContext.getCurrentInstance().update("formManager:dtbCardCash");
	}
	
	
	public void removeAll(Product prd){
		
		while (products.contains(prd)) {
			products.remove(prd);
			prd.addToStock();
		}
		
		listGrouped.remove(prd.getId_product());
		RequestContext.getCurrentInstance().update("formManager:pngListProdCard");
		RequestContext.getCurrentInstance().update("formManager:dtbCardCash");
	}
	
	public void clear(){
		total = 0.0;
		products = new ArrayList<Product>();
		listGrouped = new LinkedHashMap<>();
	}
	
	public Double getTotal() {
		total = 0.0;
		for (Integer key : listGrouped.keySet()) {
			total += listGrouped.get(key).getTotal();
		}
		return total;
	}

	public String getStatus(){
		String status = "";
		if(products.size() == 0){
			status = CARD_CLOSED;
		}else{
			status = CARD_PEND;
		}
		return status;
	}
	
	public String getSize(){
		return String.valueOf(products.size());
	}
	

	public List<Map.Entry<Integer, GroupProducts>> getListGroupProd() {
	    Set<Entry<Integer, GroupProducts>> gpSet = listGrouped.entrySet();
	    return new ArrayList<Map.Entry<Integer,GroupProducts>>(gpSet);
	}
	
	

}

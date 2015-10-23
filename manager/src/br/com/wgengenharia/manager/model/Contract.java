package br.com.wgengenharia.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "TAB_CONTRACT")
public class Contract {
	
	@Id
	@Column(name="ID_CONTRACT")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_contract;
	
	private String type;

	public int getId_contract() {
		return id_contract;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}

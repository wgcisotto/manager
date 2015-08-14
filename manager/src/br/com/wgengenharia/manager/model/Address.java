package br.com.wgengenharia.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_ADDRESS")
@SequenceGenerator(name="seqAddress", sequenceName="SEQ_ADDRESS",allocationSize=1)
public class Address {

	@Id
	@Column(name="ID_ADDRESS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqAddress")
	private int id_address;

	public int getId_address() {
		return id_address;
	}
	public void setId_address(int id_address) {
		this.id_address = id_address;
	}
	
	
}

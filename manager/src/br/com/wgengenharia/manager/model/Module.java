package br.com.wgengenharia.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TAB_MODULE")
//@SequenceGenerator(name="seqModule", sequenceName="SEQ_MODULE",allocationSize=1)
public class Module {

	@Id
	@Column(name="ID_MODULE")
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqModule")
	private int id_module;
	
	@Column(name="TYPE", nullable=false)
	private String type;

	
	public int getId_module() {
		return id_module;
	}
	public void setId_module(int id_module) {
		this.id_module = id_module;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

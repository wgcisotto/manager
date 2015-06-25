package br.com.wgengenharia.manager.coffe.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TAB_CLIENT")
@SequenceGenerator(name="seqClient", sequenceName="SEQ_CLIENT",allocationSize=1)
public class Client implements Serializable, ClientInterface{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_CLIENT")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqClient")
	private int id_client;
	@Column(name="NOME", length=100)
	private String nome;
	@Column(name="TEL", length=100)
	private String tel;
	
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
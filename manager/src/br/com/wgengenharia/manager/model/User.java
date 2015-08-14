package br.com.wgengenharia.manager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_USER")
@SequenceGenerator(name="seqUser", sequenceName="SEQ_USER",allocationSize=1)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_USER")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seqUser")
	private int id_user;
	@Column(name="NAME", length=100, nullable=false)
	private String name;
	@Column(name="LOGIN", length=50, nullable=false)
	private String login;
	@Column(name="PASS", length=100, nullable=false)
	private String pass;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

}

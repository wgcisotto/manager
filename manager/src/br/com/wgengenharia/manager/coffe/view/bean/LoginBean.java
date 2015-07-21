package br.com.wgengenharia.manager.coffe.view.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="login")
@SessionScoped
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	public LoginBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String login(){
		return "cashier";
	}
	

}

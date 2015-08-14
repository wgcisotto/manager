package br.com.wgengenharia.manager.view.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;



@ManagedBean
@RequestScoped
public class DocesBean {

	public DocesBean() {
		// TODO Auto-generated constructor stub
	}
	
	private String valor = "valuado";
	
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}



	public void carregar(){
		System.out.println("teste");
	}
	
	
}

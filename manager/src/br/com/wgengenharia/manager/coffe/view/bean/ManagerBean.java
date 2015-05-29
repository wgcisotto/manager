package br.com.wgengenharia.manager.coffe.view.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="manager")
@RequestScoped
public class ManagerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String teste;
	
	public ManagerBean() {
		teste = "Testeeeeeeeeeeee";
	}
	
	public void setTeste(String manager) {
		this.teste = manager;
	}
	public String getTeste() {
		return teste;
	}
	
	public void executar(String algo){
		System.out.println(algo);
	}

}

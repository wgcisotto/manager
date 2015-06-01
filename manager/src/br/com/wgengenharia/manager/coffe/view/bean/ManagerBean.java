package br.com.wgengenharia.manager.coffe.view.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.wgengenharia.manager.coffe.business.ProductBO;
import br.com.wgengenharia.manager.coffe.model.Product;

@ManagedBean(name="manager")
@RequestScoped
public class ManagerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ProductBO productBO;
	
	private String teste;
	
	public ManagerBean() {
		//new no BO
		productBO = new ProductBO();
		
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
	
	public void insertProduct(){
		productBO.insert(new Product());
	}
	

}

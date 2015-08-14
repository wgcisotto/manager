package br.com.wgengenharia.manager.seguranca.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Company;
import br.com.wgengenharia.manager.model.Employee;


@ManagedBean(name="auth")
@SessionScoped
public class AuthenticationBean {
	
	
	private Company company;
	private List<Branch> brachs;
	private Employee employee;
	
	
	private String user;
	private String pass;
	
	private HttpSession session;
	
	
	public AuthenticationBean() {
		// TODO Auto-generated constructor stub
	}
	
	public void login(){
    FacesContext ctx = FacesContext.getCurrentInstance();
    session = (HttpSession) ctx.getExternalContext().getSession(false);
//    session.setAttribute("usuarioAutenticado", usuarioAutenticado);
    System.out.println(session.getId());
		
	}
	
	public void logout(){
		try {
      FacesContext ctx = FacesContext.getCurrentInstance();
      session = (HttpSession) ctx.getExternalContext().getSession(false);
      session.setAttribute("usuarioAutenticado", null);
//      ctx.getExternalContext().redirect(ctx.getExternalContext().getRequestContextPath() + "/app" + Constantes.PAGINA_INDEX);
      session.invalidate();
  } catch (Exception e) {
  	// lancar excecao na tela
  }
		
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	

}

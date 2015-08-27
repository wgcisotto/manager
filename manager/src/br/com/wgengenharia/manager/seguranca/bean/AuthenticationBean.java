package br.com.wgengenharia.manager.seguranca.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import br.com.wgengenharia.manager.business.AddressBO;
import br.com.wgengenharia.manager.business.BranchBO;
import br.com.wgengenharia.manager.business.CompanyBO;
import br.com.wgengenharia.manager.business.EmployeeBO;
import br.com.wgengenharia.manager.business.ModuleBO;
import br.com.wgengenharia.manager.business.ProfileBO;
import br.com.wgengenharia.manager.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.model.Address;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Company;
import br.com.wgengenharia.manager.model.Employee;
import br.com.wgengenharia.manager.model.Module;
import br.com.wgengenharia.manager.model.Profile;


@ManagedBean(name="auth")
@SessionScoped
public class AuthenticationBean {
	
	private EntityManager em;
	
	private CompanyBO companyBO;
	
	private BranchBO branchBO;
	
	private Employee employee;
	private EmployeeBO  employeeBO;
	
	private String login;
	private String pass;
	
	
	private HttpSession session;
	
	
	public AuthenticationBean() {
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		employeeBO = new EmployeeBO(em);
		companyBO = new CompanyBO(em);
		branchBO = new BranchBO(em);
	}
	
	public String login(){
		if("".equals(login) || login == null){
			FacesContext.getCurrentInstance().addMessage("formLogin:msgLogin", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Nescessario informar o Login."));
		}else if("".equals(pass) || pass ==null){
			FacesContext.getCurrentInstance().addMessage("formLogin:msgLogin", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Nescessario informar a Senha."));
		}else{
			try {
				employee = employeeBO.findByEmail(login);
				
				if(employee.getPass().equals(pass)){
					FacesContext ctx = FacesContext.getCurrentInstance();
			    session = (HttpSession) ctx.getExternalContext().getSession(false);
			    session.setAttribute("user", employee);
			    
//			    setUserInfo();
			    
			    return "cashier?faces-redirect=true";
				}else{
					FacesContext.getCurrentInstance().addMessage("formLogin:msgLogin", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Senha incorreta!"));
				}
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage("formLogin:msgLogin", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Usuario incorreto!"));
			}
		}
		return "";
	}
	
	public String logout(){
		try {
      FacesContext ctx = FacesContext.getCurrentInstance();
      session = (HttpSession) ctx.getExternalContext().getSession(false);
      session.setAttribute("user", null);
      session.invalidate();
  } catch (Exception e) {
//  	FacesContext.getCurrentInstance().addMessage("formLogin:msgLogin", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Usuario informado esta incorreto!"));
  }
		return "login?faces-redirect=true";
	}
	
//	
//	public void setUserInfo(){
//		company = employee.getCompany();
//		branchs = employee.getBranchs();
//	}
//	
	
	
	public void teste(){
		try {
			BranchBO bBO = new BranchBO(em);
			Branch b1 = new Branch();
			b1.setName("ITAP DA SERRA");
			bBO.insert(b1);
			
			Branch b2 = new Branch();
			b2.setName("EMBU DAS ARTES");
			bBO.insert(b2);
			
			ModuleBO mBO = new ModuleBO(em);
			Module m = mBO.findByName("Escolar");
			
			CompanyBO cBO = new CompanyBO(em);
			Company c = new Company();
			c.setName("WGEngenharia");
			c.addBranch(b1);
			c.addBranch(b2);
			c.addModule(m);
			
			cBO.insert(c);
			
			ProfileBO pBO = new ProfileBO(em);
			Profile p = pBO.findByName("Administrador");
			
			Address ad = new Address();
			ad.setStreet("Rua major telles");
			ad.setNumber(171);
			ad.setCitys("Itap da serra");
			ad.setDistrict("Centro");
			ad.setState("SP");
			
			AddressBO aBO = new AddressBO(em);
			aBO.insert(ad);
			
			EmployeeBO eBO = new EmployeeBO(em);
			Employee e = new Employee();
			e.setName("William");
			e.setUser("admin@admin.com.br");
			e.setPass("will00gc");
			e.addBranch(b1);
			e.addBranch(b2);
			e.setProfile(p);
			e.setCompany(c);
			e.setPhone("980845866");
			e.setAddress(ad);
			
			
			
			eBO.insert(e);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	
	
	

//	public Company getCompany() {
//		return company;
//	}
//	public List<Branch> getBranchs() {
//		return branchs;
//	}
	public Employee getEmployee() {
		return employee;
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

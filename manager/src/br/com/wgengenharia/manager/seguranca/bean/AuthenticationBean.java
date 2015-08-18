package br.com.wgengenharia.manager.seguranca.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import br.com.wgengenharia.manager.business.BranchBO;
import br.com.wgengenharia.manager.business.CompanyBO;
import br.com.wgengenharia.manager.business.EmployeeBO;
import br.com.wgengenharia.manager.business.ModuleBO;
import br.com.wgengenharia.manager.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Company;
import br.com.wgengenharia.manager.model.Employee;
import br.com.wgengenharia.manager.model.Module;


@ManagedBean(name="auth")
@SessionScoped
public class AuthenticationBean {
	
	private EntityManager em;
	
	private Company company;
	private CompanyBO companyBO;
	
	private List<Branch> branchs;
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
			    
			    setUserInfo();
			    
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
	
	
	public void setUserInfo(){
		company = employee.getCompany();
		branchs = employee.getBranchs();
	}
	
	
	public void teste(){
		try {
			BranchBO bBO = new BranchBO(em);
			Branch b = new Branch();
			b.setName("ITAP DA SERRA");
			
			bBO.insert(b);
			
			ModuleBO mBO = new ModuleBO(em);
			Module m = new Module();
			m.setType("LANCHONETE");
			
			mBO.insert(m);
			
			CompanyBO cBO = new CompanyBO(em);
			Company c = new Company();
			c.setName("WGEngenharia");
			c.addBranch(b);
			c.addModule(m);
			
			cBO.insert(c);
			
			
			EmployeeBO eBO = new EmployeeBO(em);
			Employee e = new Employee();
			e.setName("William");
			e.setUser("admin");
			e.setPass("will00gc");
			e.addBranch(b);
			e.setCompany(c);
			
			
			eBO.insert(e);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	
	
	

	public Company getCompany() {
		return company;
	}
	public List<Branch> getBranchs() {
		return branchs;
	}
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

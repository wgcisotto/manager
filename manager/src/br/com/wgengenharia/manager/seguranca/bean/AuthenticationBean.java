package br.com.wgengenharia.manager.seguranca.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

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
import br.com.wgengenharia.manager.utils.CompanyUtil;
import br.com.wgengenharia.manager.utils.ManagerUtil;
import br.com.wgengenharia.manager.utils.StudentUtil;
import br.com.wgengenharia.manager.view.bean.CompanyBean;
import br.com.wgengenharia.manager.view.bean.ManagerBean;
import br.com.wgengenharia.manager.view.bean.StudentBean;


@ManagedBean(name="auth")
@SessionScoped
public class AuthenticationBean {
	
	private EntityManager em;

	private BranchBO branchBO;
	
	private Employee employee;
	
	public Branch currentBranch;

	@Inject 
	private EmployeeBO  employeeBO;
	
	private String login;
	private String pass;
	
	
	private String newPass1;
	private String newPass2;
	
	
	//ID de selecao de branch
	private Integer idBranchSelected;
	
	private HttpSession session;
	
	
	public AuthenticationBean() {
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
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
			    
			    updateCurrentBranch();
			    
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
  	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falha ao tentar efetuar o Logout."));
  }
		return "login?faces-redirect=true";
	}
	
	
	public void updateEmployee(){
		try {
			if(!newPass1.equals(newPass2)){
				FacesContext.getCurrentInstance().addMessage("formManager:msgUserInfo", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error!", "Senhas devem ser iguais"));
				return;
			}else{
				if(!"".equals(newPass1) && !"".equals(newPass2)){
					employee.setPass(newPass1);
				}
				employeeBO.update(employee);
				FacesContext.getCurrentInstance().addMessage("formManager:msgUserInfo", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Perfil Atualizado com sucesso"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgUserInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	
	public void updateEmployeeView(){
		try {
			Branch b = branchBO.findById(idBranchSelected);
			employee.setBranchView(b);
			employeeBO.update(employee);
			
			updateCurrentBranch();
			updateUserView();
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgUserInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void updateUserView(){
		CompanyBean companyInfo = CompanyUtil.getCompanyInfo();
		ManagerBean managerInfo = ManagerUtil.getManagerInfo();
		StudentBean studentInfo = StudentUtil.getStudentInfo();
		
		if (companyInfo != null) companyInfo.loadlistsInfo();
		
		if (managerInfo != null) managerInfo.loadListsInfo();
		
		if (studentInfo != null) studentInfo.loadListsInfo();
		
		RequestContext.getCurrentInstance().update("formManager");
	}
	
	public void updateCurrentBranch(){
    if(employee.getBranchView()!=null){
    	currentBranch = employee.getBranchView();
    }else{
    	currentBranch = employee.getBranch();
    }
    idBranchSelected = currentBranch.getId_branch();
	}
	
	
	public void teste(){
		try {
			ModuleBO mBO = new ModuleBO(em);
			Module m = new Module();
			m.setType("Escolar");
			Module m1 = new Module();
			m1.setType("Gerenciador Vendas");
			Module m2 = new Module();
			m2.setType("Lanchonete");
			
			mBO.insert(m);
			mBO.insert(m1);
			mBO.insert(m2);
			
			
			CompanyBO cBO = new CompanyBO(em);
			Company c = new Company();
			c.setName("Advanced");
			c.addModule(m);
			cBO.insert(c);

			Address a1 = new Address();
			a1.setCity("Itapecerica da serra");
			a1.setDistrict("Centro");
			a1.setNumber(171);
			a1.setState("SP");
			a1.setStreet("Rua major telles");
			a1.setZip_code("06850001");
			
			BranchBO bBO = new BranchBO(em);
			Branch b1 = new Branch();
			b1.setName("ADVANCED - ITAP DA SERRA");
			b1.setCompany(c);
			b1.setAddress(a1);
			bBO.insert(b1);
			
			
			ProfileBO pBO = new ProfileBO(em);
			Profile p = new Profile();
			p.setCompany(c);
			p.setName("Administrador");
			p.setBranch(b1);
			
			Profile p3 = new Profile();
			p3.setCompany(c);
			p3.setName("Funcionario");
			p3.setBranch(b1);
			Profile p4 = new Profile();
			p4.setCompany(c);
			p4.setName("Professor");
			p4.setBranch(b1);
		
			pBO.insert(p);
			pBO.insert(p3);
			pBO.insert(p4);
			
			Address ad = new Address();
			ad.setStreet("Rua major telles");
			ad.setNumber(171);
			ad.setZip_code("06850-001");
			ad.setCity("Itapecerica da serra");
			ad.setDistrict("Centro");
			ad.setState("SP");
			
			EmployeeBO eBO = new EmployeeBO();
			Employee e = new Employee();
			e.setName("Administrador Advanced");
			e.setUser("admin@admin.com.br");
			e.setPhone("(11) 46664673");
			e.setPass("admin123");
			e.setBranchView(b1);
			e.setProfile(p);
			e.setCompany(c);
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
	public String getNewPass1() {
		return newPass1;
	}
	public void setNewPass1(String newPass1) {
		this.newPass1 = newPass1;
	}
	public String getNewPass2() {
		return newPass2;
	}
	public void setNewPass2(String newPass2) {
		this.newPass2 = newPass2;
	}
	public Integer getIdBranchSelected() {
		return idBranchSelected;
	}
	public void setIdBranchSelected(Integer idBranchSelected) {
		this.idBranchSelected = idBranchSelected;
	}
}

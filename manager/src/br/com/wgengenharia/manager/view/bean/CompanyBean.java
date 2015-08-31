package br.com.wgengenharia.manager.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.primefaces.event.SelectEvent;

import br.com.wgengenharia.manager.business.BranchBO;
import br.com.wgengenharia.manager.business.CompanyBO;
import br.com.wgengenharia.manager.business.EmployeeBO;
import br.com.wgengenharia.manager.business.ProfileBO;
import br.com.wgengenharia.manager.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Employee;
import br.com.wgengenharia.manager.model.Profile;
import br.com.wgengenharia.manager.seguranca.bean.AuthenticationBean;
import br.com.wgengenharia.manager.utils.AuthenticationUtil;

@ManagedBean(name="company")
@SessionScoped
public class CompanyBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//EMPLOYEES
	private EmployeeBO employeeBO;
	private Employee newEmployee;
	private Employee selectedEmployee;
	private List<Employee> employees;
	private List<Employee> filteredEmployees;
	private String globalFilterEmployee;
	//Employees Profile	
	private List<Profile> profiles;
	
	
	//BRANCHS
	private BranchBO branchBO;
	private Branch newBranch;
	private Branch selectedBranch;
	private List<Branch> branchs;
	private List<Branch> filteredBranchs;
	private String globalFilterBranch;
	
	//COMPANY
	private CompanyBO companyBO;
	
	
	//DEFAULT
	public EntityManager em;
	public ProfileBO profileBO;
	
	
	
	
	private AuthenticationBean userInfo;
	
	public CompanyBean() {
		try {
			userInfo = AuthenticationUtil.getUserInfo();
			em = EntityManagerFactorySingleton.getInstance().createEntityManager();
			
			employeeBO = new EmployeeBO();
			profileBO = new ProfileBO(em);
			branchBO = new BranchBO(em);
			companyBO = new CompanyBO(em);
			
			
			employees = employeeBO.findByCompany(userInfo.getEmployee().getCompany());
			branchs = branchBO.findByCompany(userInfo.getEmployee().getCompany());
			profiles = profileBO.findByCompany(userInfo.getEmployee().getCompany());
		
			newEmployee = new Employee();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//EMPLOYEE
	
	public void newEmployee(){
		this.newEmployee = new Employee();
	}
	
	public void addEmployee(){
		try {
//			Profile profile = profileBO.findByNameAndCompany(selectedProfile.getName(),userInfo.getEmployee().getCompany());
//			newEmployee.setProfile(profile);
//			selectedProfile = null;
			newEmployee.setCompany(userInfo.getEmployee().getCompany());
			employeeBO.insert(newEmployee);
			employees = employeeBO.findByCompany(userInfo.getEmployee().getCompany());// FAZER PERQUISA POR NOME DA EMPRESA E PELO BRANCH  ???
			newEmployee = new Employee();
			FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Funcion�rio Inserido com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	
	public void delEmployee(){
		try {
			if(selectedEmployee!=null){
				employeeBO.delete(selectedEmployee);
				employees = employeeBO.findByCompany(userInfo.getEmployee().getCompany());// FAZER PERQUISA POR NOME DA EMPRESA E PELO BRANCH  ???
				selectedEmployee = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Funcion�rio Excluido com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescess�rio selecionar um Funcion�rio"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
  
	public void onRowSelectEmployee(SelectEvent event) {
		this.selectedEmployee = (Employee) event.getObject();
	}
	
	public void updateEmployee(){
		try {
			if(selectedEmployee!=null){
				employeeBO.update(selectedEmployee);
				employees = employeeBO.findByCompany(userInfo.getEmployee().getCompany());
				selectedEmployee = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Funcionario Atualizada com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescess�rio selecionar um Funcionario"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}

	//BRANCH
	
	public void newBranch(){
		this.newBranch = new Branch();
	}
	
	public void addBranch(){
		try {
			newBranch.setCompany(userInfo.getEmployee().getCompany());
			branchBO.insert(newBranch);
			branchs = branchBO.findByCompany(userInfo.getEmployee().getCompany());
			newBranch = new Branch();
			FacesContext.getCurrentInstance().addMessage("formManager:msgBranch", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Filial Inserido com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgBranch", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void delBranch(){
		try {
			if(selectedBranch!=null){
				branchBO.delete(selectedBranch);
				branchs = branchBO.findByCompany(userInfo.getEmployee().getCompany());
				selectedBranch = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgBranch", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Filial Excluido com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgBranch", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescess�rio selecionar uma Filial"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgBranch", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
  
	public void onRowSelectBranch(SelectEvent event) {
		this.selectedBranch = (Branch) event.getObject();
	}
	
	public void updateBranch(){
		try {
			if(selectedBranch!=null){
				branchBO.update(selectedBranch);
				branchs = branchBO.findByCompany(userInfo.getEmployee().getCompany());
				selectedBranch = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgBranch", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Filial Atualizada com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgBranch", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescess�rio selecionar uma Filial"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgBranch", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	
	
	// COMPANY
	
	public void updateCompany(){
		try {
			if(userInfo.getEmployee().getCompany()!=null){
				companyBO.update(userInfo.getEmployee().getCompany());
				FacesContext.getCurrentInstance().addMessage("formManager:msgCompany", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Informac�es da Empresa Atualizada com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgCompany", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Erro ao atualizar as informacoes da Empresa"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgCompany", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	
	//get and setters
	//EMPLOYEES
	public Employee getNewEmployee() {
		return newEmployee;
	}
	public void setNewEmployee(Employee newEmployee) {
		this.newEmployee = newEmployee;
	}
	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}
	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public List<Employee> getFilteredEmployees() {
		return filteredEmployees;
	}
	public void setFilteredEmployees(List<Employee> filteredEmployees) {
		this.filteredEmployees = filteredEmployees;
	}
	public String getGlobalFilterEmployee() {
		return globalFilterEmployee;
	}
	public void setGlobalFilterEmployee(String globalFilterEmployee) {
		this.globalFilterEmployee = globalFilterEmployee;
	}
	public List<Profile> getProfiles() {
		return profiles;
	}
	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}
	


	//get and setters
	//BRANCH
	public Branch getNewBranch() {
		return newBranch;
	}
	public void setNewBranch(Branch newBranch) {
		this.newBranch = newBranch;
	}
	public Branch getSelectedBranch() {
		return selectedBranch;
	}
	public void setSelectedBranch(Branch selectedBranch) {
		this.selectedBranch = selectedBranch;
	}
	public List<Branch> getBranchs() {
		return branchs;
	}
	public void setBranchs(List<Branch> branchs) {
		this.branchs = branchs;
	}
	public List<Branch> getFilteredBranchs() {
		return filteredBranchs;
	}
	public void setFilteredBranchs(List<Branch> filteredBranchs) {
		this.filteredBranchs = filteredBranchs;
	}
	public String getGlobalFilterBranch() {
		return globalFilterBranch;
	}
	public void setGlobalFilterBranch(String globalFilterBranch) {
		this.globalFilterBranch = globalFilterBranch;
	}
	
}

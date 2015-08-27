package br.com.wgengenharia.manager.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.primefaces.event.SelectEvent;

import br.com.wgengenharia.manager.business.EmployeeBO;
import br.com.wgengenharia.manager.business.ProfileBO;
import br.com.wgengenharia.manager.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.model.Address;
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
	//Employees Address
	private Address newAddress;
	//Employees Profile	
	private String profileName;
	
	
	//DEFAULT
	public EntityManager em;
	public ProfileBO profileBO;
	
	
	
	private AuthenticationBean userInfo;
	
	public CompanyBean() {
		try {
			userInfo = AuthenticationUtil.getUserInfo();
			em = EntityManagerFactorySingleton.getInstance().createEntityManager();
			
			employeeBO = new EmployeeBO(em);
			profileBO = new ProfileBO(em);
			
			employees = employeeBO.findByCompany(userInfo.getEmployee().getCompany());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void newEmployee(){
		this.newEmployee = new Employee();
	}
	
	public void addEmployee(){
		try {
			Profile profile = profileBO.findByName(profileName);
			newEmployee.setProfile(profile);
			newEmployee.setAddress(newAddress);
			profileName = "";
			employeeBO.insert(newEmployee);
//		employees = employeeBO.listEmployees();// FAZER PERQUISA POR NOME DA EMPRESA E PELO BRANCH  ???
			newEmployee = new Employee();
			FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Funcionário Inserido com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void delEmployee(){
		try {
			if(selectedEmployee!=null){
				employeeBO.delete(selectedEmployee);
//				employees = employeeBO.listEmployees();// FAZER PERQUISA POR NOME DA EMPRESA E PELO BRANCH  ???
				selectedEmployee = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Funcionário Excluido com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar um Funcionário"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
  
	public void onRowSelectEmployee(SelectEvent event) {
		this.selectedEmployee = (Employee) event.getObject();
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
	public Address getNewAddress() {
		return newAddress;
	}
	public void setNewAddress(Address newAddress) {
		this.newAddress = newAddress;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
}

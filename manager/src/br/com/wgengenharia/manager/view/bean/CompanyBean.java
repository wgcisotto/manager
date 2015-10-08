package br.com.wgengenharia.manager.view.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

import br.com.wgengenharia.manager.business.BranchBO;
import br.com.wgengenharia.manager.business.CompanyBO;
import br.com.wgengenharia.manager.business.EmployeeBO;
import br.com.wgengenharia.manager.business.ProfileBO;
import br.com.wgengenharia.manager.business.StudentPaymentsBO;
import br.com.wgengenharia.manager.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.facade.ManagerSaleFacadeInterface;
import br.com.wgengenharia.manager.factory.ManagerPaymentFactory;
import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Employee;
import br.com.wgengenharia.manager.model.Profile;
import br.com.wgengenharia.manager.model.StudentPayments;
import br.com.wgengenharia.manager.seguranca.bean.AuthenticationBean;
import br.com.wgengenharia.manager.utils.AuthenticationUtil;

@ManagedBean(name="company")
@SessionScoped
public class CompanyBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//EMPLOYEES
	private EmployeeBO employeeBO;
	private Employee newEmployee;
	private Integer idBranchNewEmployee;
	private Employee selectedEmployee;
	private Integer idBranchSelectedEmployee;
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
	
	
	// ALERTS CONTROL
	// CONTROLE DE BOLETO EM ATRASO
	private List<StudentPayments> alertsPayments;
	private StudentPaymentsBO studentPaymentsBO;
	private StudentPayments selectedPayment;
	
	
	//DEFAULT
	private EntityManager em;
	private ProfileBO profileBO;
	
	
	
	private AuthenticationBean userInfo;
	
	public CompanyBean() {
		try {
			userInfo = AuthenticationUtil.getUserInfo();
			em = EntityManagerFactorySingleton.getInstance().createEntityManager();
			
			employeeBO = new EmployeeBO();
			profileBO = new ProfileBO(em);
			branchBO = new BranchBO(em);
			companyBO = new CompanyBO(em);
			studentPaymentsBO = new StudentPaymentsBO(em);
			
			
			loadlistsInfo();
		
			newEmployee = new Employee();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadlistsInfo(){
		branchs = branchBO.listByCompany(userInfo.getEmployee().getCompany());
		employees = employeeBO.listByBranch(userInfo.currentBranch);
		profiles = profileBO.listByBranch(userInfo.currentBranch);
		alertsPayments =  studentPaymentsBO.listStudentPaymentsLate(Calendar.getInstance(), userInfo.currentBranch);
	}
	
	//EMPLOYEE
	
	public void newEmployee(){
		this.newEmployee = new Employee();
	}
	
	public void addEmployee(){
		try {
			employeeBO.findByEmail(newEmployee.getUser());
			FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!!", "Email ja cadastrado para login."));
			return;
		} catch (NoResultException e) {
			try {
				Branch b;
				if(idBranchNewEmployee != null){
					b = branchBO.findById(idBranchNewEmployee);
				}else{
					b = userInfo.currentBranch;
				}
				
				newEmployee.setCompany(userInfo.getEmployee().getCompany());
				newEmployee.setBranch(b);
				employeeBO.insert(newEmployee);
				employees = employeeBO.listByBranch(userInfo.currentBranch);
				newEmployee = new Employee();
				idBranchNewEmployee = 0;
				FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Funcionário Inserido com sucesso."));
			} catch (Exception ex) {
				FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
			}
		}
	}
	
	
	public void delEmployee(){
		try {
			if(selectedEmployee!=null){
				employeeBO.delete(selectedEmployee);
				employees = employeeBO.listByBranch(userInfo.currentBranch);
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
	
	public void updateEmployee(){
		try {
			if(selectedEmployee!=null){
				Branch b;
				if(idBranchSelectedEmployee != null){
					b = branchBO.findById(idBranchSelectedEmployee);
				}else{
					b = userInfo.currentBranch;
				}
				
				selectedEmployee.setBranch(b);
				employeeBO.update(selectedEmployee);
				employees = employeeBO.listByBranch(userInfo.currentBranch);
				selectedEmployee = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Funcionario Atualizada com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgEmployee", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar um Funcionario"));
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
			branchs = branchBO.listByCompany(userInfo.getEmployee().getCompany());
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
				branchs = branchBO.listByCompany(userInfo.getEmployee().getCompany());
				selectedBranch = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgBranch", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Filial Excluido com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgBranch", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar uma Filial"));
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
				branchs = branchBO.listByCompany(userInfo.getEmployee().getCompany());
				selectedBranch = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgBranch", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Filial Atualizada com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgBranch", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar uma Filial"));
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
				FacesContext.getCurrentInstance().addMessage("formManager:msgCompany", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Informacões da Empresa Atualizada com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgCompany", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Erro ao atualizar as informacoes da Empresa"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgCompany", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void fileUpload(FileUploadEvent event) {
  		try {
  			userInfo.getEmployee().getCompany().setImage(event.getFile().getContents());
  			companyBO.update(userInfo.getEmployee().getCompany());
  		} catch (Exception e) {
  			FacesContext.getCurrentInstance().addMessage("formManager:msgCompany", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
  		}
  }
	
	// ALERTS CONTROL
	
	public void updatePaymentDate(){
		try {
			if(selectedPayment!=null){
				studentPaymentsBO.update(selectedPayment);
				alertsPayments = studentPaymentsBO.listStudentPaymentsLate(Calendar.getInstance(),userInfo.currentBranch);
				selectedPayment = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgCompanyAlert", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Alteração Efetuada com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgCompanyAlert", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Erro ao atualizar data de pagamento"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgCompanyAlert", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void pay(){
		try {
			if(selectedPayment!=null){
				selectedPayment.setPayment_date(new Date());
				selectedPayment.setPaid(selectedPayment.getPrice());
				
				studentPaymentsBO.update(selectedPayment);
				
				ManagerSaleFacadeInterface manager = ManagerPaymentFactory.newInstance(selectedPayment);
				manager.persistSale();

				alertsPayments = studentPaymentsBO.listStudentPaymentsLate(Calendar.getInstance(),userInfo.currentBranch);
				
				selectedPayment = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgCompanyAlert", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Pagamento efetuado com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgCompanyAlert", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar um boleto atrasado"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgCompanyAlert", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
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
	public Integer getIdBranchNewEmployee() {
		return idBranchNewEmployee;
	}
	public void setIdBranchNewEmployee(Integer idBranchNewEmployee) {
		this.idBranchNewEmployee = idBranchNewEmployee;
	}
	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}
	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}
	public Integer getIdBranchSelectedEmployee() {
		return idBranchSelectedEmployee;
	}
	public void setIdBranchSelectedEmployee(Integer idBranchSelectedEmployee) {
		this.idBranchSelectedEmployee = idBranchSelectedEmployee;
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
	public List<StudentPayments> getAlertsPayments() {
		return alertsPayments;
	}
	public void setAlertsPayments(List<StudentPayments> alertsPayments) {
		this.alertsPayments = alertsPayments;
	}
	public StudentPayments getSelectedPayment() {
		return selectedPayment;
	}
	public void setSelectedPayment(StudentPayments selectedPayment) {
		this.selectedPayment = selectedPayment;
	}
}
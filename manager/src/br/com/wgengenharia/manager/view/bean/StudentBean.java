package br.com.wgengenharia.manager.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.primefaces.event.SelectEvent;

import br.com.wgengenharia.manager.business.ClassModuleBO;
import br.com.wgengenharia.manager.business.StudentBO;
import br.com.wgengenharia.manager.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.model.ClassModule;
import br.com.wgengenharia.manager.model.Student;
import br.com.wgengenharia.manager.seguranca.bean.AuthenticationBean;
import br.com.wgengenharia.manager.utils.AuthenticationUtil;

@ManagedBean(name="student")
@SessionScoped
public class StudentBean implements Serializable{

	private static final long serialVersionUID = 1L;
	

	
	//STUDENT
	private StudentBO studentBO;
	private Student newStudent;
	private Student selectedStudent;
	private List<Student> students;
	private List<Student> filteredStudents;
	private String globalFilterStudent;
	
	
	//CLASS MODULE
	private ClassModuleBO classModuleBO;
	private ClassModule newModule;
	private ClassModule selectedModule;
	private List<ClassModule> modules;
	private List<ClassModule> filteredModules;
	private String globalFilterModule;
	
	
	// Default
	private EntityManager em;
	private AuthenticationBean userInfo;
	
	public StudentBean() {
		userInfo = AuthenticationUtil.getUserInfo();
		
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		classModuleBO = new ClassModuleBO(em);
		studentBO = new StudentBO(em);
		
		
		studentBO.listStudentByCompany(userInfo.getEmployee().getCompany());
		classModuleBO.listModulesByCompany(userInfo.getEmployee().getCompany());
	}


	//METODOS PARA CADASTRA ALUNO
	
	public void newStudent(){
		this.newStudent = new Student();
	}
	
	public void addStudent(){
		try {
			newStudent.setCompany(userInfo.getEmployee().getCompany());
			
			studentBO.insert(newStudent);
			students = studentBO.listStudentByCompany(userInfo.getEmployee().getCompany());// FAZER PERQUISA POR NOME DA EMPRESA E PELO BRANCH  ???
			newStudent = new Student();
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudent", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Aluno cadastrado com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudent", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	
	public void delStudent(){
		try {
			if(selectedStudent!=null){
				studentBO.delete(selectedStudent);
				students = studentBO.listStudentByCompany(userInfo.getEmployee().getCompany());// FAZER PERQUISA POR NOME DA EMPRESA E PELO BRANCH  ???
				selectedStudent = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudent", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Aluno Excluido com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudent", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar um Aluno"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudent", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
  
	public void onRowSelectStudent(SelectEvent event) {
		this.selectedStudent = (Student) event.getObject();
	}
	
	public void updateStudent(){
		try {
			if(selectedStudent !=null){
				studentBO.update(selectedStudent);
				students = studentBO.listStudentByCompany(userInfo.getEmployee().getCompany());
				selectedStudent = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudent", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Aluno Atualizada com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudent", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar um Aluno"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudent", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	//metodos para cadastrar os modulos 
	
	public void newModule(){
		this.newModule = new ClassModule();
	}
	
	public void addModule(){
		try {
			newModule.setCompany(userInfo.getEmployee().getCompany());
			
			classModuleBO.insert(newModule);
			modules = classModuleBO.listModulesByCompany(userInfo.getEmployee().getCompany());// FAZER PERQUISA POR NOME DA EMPRESA E PELO BRANCH  ???
			newModule = new ClassModule();
			FacesContext.getCurrentInstance().addMessage("formManager:msgClassModule", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Modulo Inserido com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgClassModule", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	
	public void delModule(){
		try {
			if(selectedModule!=null){
				classModuleBO.delete(selectedModule);
				modules = classModuleBO.listModulesByCompany(userInfo.getEmployee().getCompany());// FAZER PERQUISA POR NOME DA EMPRESA E PELO BRANCH  ???
				selectedModule = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgClassModule", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Modulo Excluido com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgClassModule", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar um Modulo"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgClassModule", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
  
	public void onRowSelectModule(SelectEvent event) {
		this.selectedModule = (ClassModule) event.getObject();
	}
	
	public void updateModule(){
		try {
			if(selectedModule!=null){
				classModuleBO.update(selectedModule);
				modules = classModuleBO.listModulesByCompany(userInfo.getEmployee().getCompany());
				selectedModule = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgClassModule", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Modulo Atualizada com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgClassModule", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar um modulo"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgClassModule", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}


	// gettes and setters 
	
	//ALUNO
	public ClassModule getNewModule() {
		return newModule;
	}
	public Student getNewStudent() {
		return newStudent;
	}
	public void setNewStudent(Student newStudent) {
		this.newStudent = newStudent;
	}
	public Student getSelectedStudent() {
		return selectedStudent;
	}
	public void setSelectedStudent(Student selectedStudent) {
		this.selectedStudent = selectedStudent;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public List<Student> getFilteredStudents() {
		return filteredStudents;
	}
	public void setFilteredStudents(List<Student> filteredStudents) {
		this.filteredStudents = filteredStudents;
	}
	public String getGlobalFilterStudent() {
		return globalFilterStudent;
	}
	public void setGlobalFilterStudent(String globalFilterStudent) {
		this.globalFilterStudent = globalFilterStudent;
	}
	
	// Class module
	public void setNewModule(ClassModule newModule) {
		this.newModule = newModule;
	}
	public ClassModule getSelectedModule() {
		return selectedModule;
	}
	public void setSelectedModule(ClassModule selectedModule) {
		this.selectedModule = selectedModule;
	}
	public List<ClassModule> getModules() {
		return modules;
	}
	public void setModules(List<ClassModule> modules) {
		this.modules = modules;
	}
	public List<ClassModule> getFilteredModules() {
		return filteredModules;
	}
	public void setFilteredModules(List<ClassModule> filteredModules) {
		this.filteredModules = filteredModules;
	}
	public String getGlobalFilterModule() {
		return globalFilterModule;
	}
	public void setGlobalFilterModule(String globalFilterModule) {
		this.globalFilterModule = globalFilterModule;
	}
	
}

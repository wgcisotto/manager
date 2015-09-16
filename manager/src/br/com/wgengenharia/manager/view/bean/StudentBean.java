package br.com.wgengenharia.manager.view.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.primefaces.event.SelectEvent;

import br.com.wgengenharia.manager.business.ClassModuleBO;
import br.com.wgengenharia.manager.business.ClassStudentBO;
import br.com.wgengenharia.manager.business.FollowUpBO;
import br.com.wgengenharia.manager.business.StudentBO;
import br.com.wgengenharia.manager.business.StudentPaymentsBO;
import br.com.wgengenharia.manager.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.facade.ManagerSaleFacadeInterface;
import br.com.wgengenharia.manager.factory.ManagerPaymentFactory;
import br.com.wgengenharia.manager.model.ClassModule;
import br.com.wgengenharia.manager.model.ClassStudent;
import br.com.wgengenharia.manager.model.FollowUp;
import br.com.wgengenharia.manager.model.Student;
import br.com.wgengenharia.manager.model.StudentPayments;
import br.com.wgengenharia.manager.seguranca.bean.AuthenticationBean;
import br.com.wgengenharia.manager.utils.AuthenticationUtil;
import br.com.wgengenharia.manager.utils.DateUtil;

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
	
	//CLASS STUDENT
	private ClassStudentBO classStudentBO;
	private ClassStudent newClassStudent;
	private ClassStudent selectedClassStudent;
	private List<ClassStudent> classStudents;
	private List<ClassStudent> filteredClassStudents;
	private String globalFilterClassStudent;
	
	//STUDENT INFO
	private ClassStudent studentInfoClass;
	private List<FollowUp> followups;
	private FollowUpBO followUpBO;
	private FollowUp newFollowUp;
	private FollowUp selectedFollowUp;
	
	//STUDENT PAYMENTS
	private StudentPaymentsBO studentPaymentsBO; 
	private List<StudentPayments> student_payments;
	private StudentPayments selectedStudentPayments;
	
	private int quantity_parcel;
	private Date expiry_date;
	private double price;

	private Date new_expiry_date;
	private double new_price;
	
	// DEFAULT
	private EntityManager em;
	private AuthenticationBean userInfo;
	
	public StudentBean() {
		userInfo = AuthenticationUtil.getUserInfo();
		
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		classModuleBO = new ClassModuleBO(em);
		studentBO = new StudentBO(em);
		classStudentBO = new ClassStudentBO(em);
		followUpBO = new FollowUpBO(em);
		studentPaymentsBO = new StudentPaymentsBO(em);
		
		
		students = studentBO.listStudentByCompany(userInfo.getEmployee().getCompany());
		modules = classModuleBO.listModulesByCompany(userInfo.getEmployee().getCompany());
		classStudents = classStudentBO.listClassStudentsByCompany(userInfo.getEmployee().getCompany());
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
	
	//METODOS PARA CADASTRAR OS MODULOS 
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
	
	//METODOS PARA CADASTRAR OS MODULOS 
	public void newClassStudent(){
		this.newClassStudent = new ClassStudent();
	}
	
	public void addClassStudent(){
		try {
			newClassStudent.setCompany(userInfo.getEmployee().getCompany());
			
			classStudentBO.insert(newClassStudent);
			classStudents = classStudentBO.listClassStudentsByCompany(userInfo.getEmployee().getCompany());// FAZER PERQUISA POR NOME DA EMPRESA E PELO BRANCH  ???
			newClassStudent = new ClassStudent();
			FacesContext.getCurrentInstance().addMessage("formManager:msgClassStudent", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Turma inserida com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgClassStudent", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void delClassStudent(){
		try {
			if(selectedClassStudent!=null){
				classStudentBO.delete(selectedClassStudent);
				classStudents = classStudentBO.listClassStudentsByCompany(userInfo.getEmployee().getCompany());// FAZER PERQUISA POR NOME DA EMPRESA E PELO BRANCH  ???
				selectedModule = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgClassStudent", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Turma Excluida com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgClassStudent", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar uma turma"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgClassStudent", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
  
	public void onRowSelectClassStudent(SelectEvent event) {
		this.selectedClassStudent = (ClassStudent) event.getObject();
	}
	
	public void updateClassStudent(){
		try {
			if(selectedClassStudent!=null){
				classStudentBO.update(selectedClassStudent);
				classStudents = classStudentBO.listClassStudentsByCompany(userInfo.getEmployee().getCompany());
				selectedClassStudent = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgClassStudent", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Turma Atualizada com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgClassStudent", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar uma Turma"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgClassStudent", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	// METODOS PARA TELA DE STUDENT INFO
	public String openStudentInfo(){
		if(selectedStudent!=null){
			
			followups = followUpBO.listFollowUpByStudent(this.selectedStudent);
			newFollowUp = new FollowUp();
			
			student_payments = studentPaymentsBO.listStudentPayments(this.selectedStudent);
			resetStudentPaymentInfo();

			return "student_info?faces-redirect=true";
		}else{
			return "";
		}
	}
	
	public void addFollowUp(){
		try {
			newFollowUp.setStudent(this.selectedStudent);
			newFollowUp.setEmployee(userInfo.getEmployee());
			newFollowUp.setDate_followup(new Date());
			followUpBO.insert(newFollowUp);
			followups = followUpBO.listFollowUpByStudent(selectedStudent);// FAZER PERQUISA POR NOME DA EMPRESA E PELO BRANCH  ???
			newFollowUp = new FollowUp();
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "FollowUp inserido com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void updateFollowUp(){
		try {
			if(selectedFollowUp!=null){
				selectedFollowUp.setEmployee(userInfo.getEmployee());
				followUpBO.update(selectedFollowUp);
				followups = followUpBO.listFollowUpByStudent(selectedStudent);// FAZER PERQUISA POR NOME DA EMPRESA E PELO BRANCH  ???
				selectedFollowUp = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "FollowUp Atualizada com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar um FollowUp"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void addStudentPayment(){
		try {
			for (int i = 1; i <= quantity_parcel; i++) {
				StudentPayments sp = new StudentPayments();
				
				if(i>1){
					expiry_date = DateUtil.updateDate(expiry_date);
				}
				sp.setExpiry_date(expiry_date);
				sp.setNumber_parcel(i);
				sp.setPrice(price);
				sp.setStudent(this.selectedStudent);
				sp.setBranch(userInfo.getEmployee().getBranch());
				sp.setCompany(userInfo.getEmployee().getCompany());
				
				studentPaymentsBO.insert(sp);
			}

			student_payments = studentPaymentsBO.listStudentPayments(this.selectedStudent);
			resetStudentPaymentInfo();
			
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Falha ao atualizar o financeiro do Aluno"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void resetStudentPaymentInfo(){
		quantity_parcel = 0;
		expiry_date = null;
		price = 0;
	}
	
	public void pay(){
		try {
			if(selectedStudentPayments!=null){
				selectedStudentPayments.setPayment_date(new Date());
				
				studentPaymentsBO.update(selectedStudentPayments);
				
				ManagerSaleFacadeInterface manager = ManagerPaymentFactory.newInstance(selectedStudentPayments);
				manager.persistSale();
				
				if(new_expiry_date != null && new_price > 0){
					newPay();
				}

				student_payments = studentPaymentsBO.listStudentPayments(this.selectedStudent);
				
				selectedStudentPayments = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Pagamento efetuado com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar uma parcela"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void newPay(){
		StudentPayments newStudentPayment = new StudentPayments();
		newStudentPayment.setExpiry_date(new_expiry_date);
		newStudentPayment.setNumber_parcel(selectedStudentPayments.getNumber_parcel());
		newStudentPayment.setStudent(this.selectedStudent);
		newStudentPayment.setPrice(new_price);
		newStudentPayment.setBranch(userInfo.getEmployee().getBranch());
		newStudentPayment.setCompany(userInfo.getEmployee().getCompany());
		
		studentPaymentsBO.insert(newStudentPayment);
	}
	
	
	public void updateStudentClass(){
		try {
			this.studentInfoClass.addStudent(selectedStudent);
			
			classStudentBO.update(studentInfoClass);
			
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Atualizações efetuada com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	
	// METODOS PARA CHAMADAS DE ALUNO 
	
	public String openCallStudent(){
		if(selectedClassStudent!=null){
//			
//			followups = followUpBO.listFollowUpByStudent(this.selectedStudent);
//			newFollowUp = new FollowUp();
			
//			student_payments = studentPaymentsBO.listStudentPayments(this.selectedStudent);
//			resetStudentPaymentInfo();

			return "student_call?faces-redirect=true";
		}else{
			return "";
		}
	}
	

	//GETTES AND SETTERS 
	
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
	
	// CLASS MODULE
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

	//CLASS STUDENT
	public ClassStudent getNewClassStudent() {
		return newClassStudent;
	}
	public void setNewClassStudent(ClassStudent newClassStudent) {
		this.newClassStudent = newClassStudent;
	}
	public ClassStudent getSelectedClassStudent() {
		return selectedClassStudent;
	}
	public void setSelectedClassStudent(ClassStudent selectedClassStudent) {
		this.selectedClassStudent = selectedClassStudent;
	}
	public List<ClassStudent> getClassStudents() {
		return classStudents;
	}
	public void setClassStudents(List<ClassStudent> classStudents) {
		this.classStudents = classStudents;
	}
	public List<ClassStudent> getFilteredClassStudents() {
		return filteredClassStudents;
	}
	public void setFilteredClassStudents(List<ClassStudent> filteredClassStudents) {
		this.filteredClassStudents = filteredClassStudents;
	}
	public String getGlobalFilterClassStudent() {
		return globalFilterClassStudent;
	}
	public void setGlobalFilterClassStudent(String globalFilterClassStudent) {
		this.globalFilterClassStudent = globalFilterClassStudent;
	}
	
	//STUDENT INFO
	public ClassStudent getStudentInfoClass() {
		return studentInfoClass;
	}
	public void setStudentInfoClass(ClassStudent studentInfoClass) {
		this.studentInfoClass = studentInfoClass;
	}
	public List<FollowUp> getFollowups() {
		return followups;
	}
	public void setFollowups(List<FollowUp> followups) {
		this.followups = followups;
	}
	public FollowUp getNewFollowUp() {
		return newFollowUp;
	}
	public void setNewFollowUp(FollowUp newFollowUp) {
		this.newFollowUp = newFollowUp;
	}
	public FollowUp getSelectedFollowUp() {
		return selectedFollowUp;
	}
	public void setSelectedFollowUp(FollowUp selectedFollowUp) {
		this.selectedFollowUp = selectedFollowUp;
	}

	//STUDENT PAYMENTS
	public List<StudentPayments> getStudent_payments() {
		return student_payments;
	}
	public void setStudent_payments(List<StudentPayments> student_payments) {
		this.student_payments = student_payments;
	}
	public StudentPayments getSelectedStudentPayments() {
		return selectedStudentPayments;
	}
	public void setSelectedStudentPayments(StudentPayments selectedStudentPayments) {
		this.selectedStudentPayments = selectedStudentPayments;
	}
	public int getQuantity_parcel() {
		return quantity_parcel;
	}
	public void setQuantity_parcel(int quantity_parcel) {
		this.quantity_parcel = quantity_parcel;
	}
	public Date getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getNew_expiry_date() {
		return new_expiry_date;
	}
	public void setNew_expiry_date(Date new_expiry_date) {
		this.new_expiry_date = new_expiry_date;
	}
	public double getNew_price() {
		return new_price;
	}
	public void setNew_price(double new_price) {
		this.new_price = new_price;
	}
}
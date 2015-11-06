package br.com.wgengenharia.manager.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.wgengenharia.manager.business.CallStudentBO;
import br.com.wgengenharia.manager.business.ClassModuleBO;
import br.com.wgengenharia.manager.business.ClassStudentBO;
import br.com.wgengenharia.manager.business.ContractBO;
import br.com.wgengenharia.manager.business.FollowUpBO;
import br.com.wgengenharia.manager.business.StudentBO;
import br.com.wgengenharia.manager.business.StudentInfoBO;
import br.com.wgengenharia.manager.business.StudentPaymentsBO;
import br.com.wgengenharia.manager.db.EntityManagerFactorySingleton;
import br.com.wgengenharia.manager.facade.ManagerSaleFacadeInterface;
import br.com.wgengenharia.manager.factory.ManagerPaymentFactory;
import br.com.wgengenharia.manager.model.CallStudent;
import br.com.wgengenharia.manager.model.ClassModule;
import br.com.wgengenharia.manager.model.ClassStudent;
import br.com.wgengenharia.manager.model.Contract;
import br.com.wgengenharia.manager.model.FollowUp;
import br.com.wgengenharia.manager.model.Student;
import br.com.wgengenharia.manager.model.StudentInfo;
import br.com.wgengenharia.manager.model.StudentPayments;
import br.com.wgengenharia.manager.report.ManagerReport;
import br.com.wgengenharia.manager.report.factory.ManagerReportFactory;
import br.com.wgengenharia.manager.seguranca.bean.AuthenticationBean;
import br.com.wgengenharia.manager.utils.AuthenticationUtil;
import br.com.wgengenharia.manager.utils.CompanyUtil;
import br.com.wgengenharia.manager.utils.DateUtil;
import br.com.wgengenharia.manager.utils.StudentPaymentUtil;

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
	private List<String> modulesList;
	private List<ClassModule> filteredModules;
	private String globalFilterModule;
	
	//CLASS STUDENT
	private ClassStudentBO classStudentBO;
	private CallStudentBO callStudentBO;
	private StudentInfoBO studentInfoBO;
	private ClassStudent newClassStudent;
	private ClassStudent selectedClassStudent;
	private List<ClassStudent> classStudents;
	private List<ClassStudent> filteredClassStudents;
	private String globalFilterClassStudent;
	private Integer idClassStudentFilter;
	private CallStudent selectedCall;
	private CallStudent selectedCallDetails;
	private StudentInfo selectedStudentInfo;
	
	//STUDENT INFO
	private ClassStudent studentInfoClass;
	private Integer	idClassStudent;
	private List<FollowUp> followups;
	private FollowUpBO followUpBO;
	private FollowUp newFollowUp;
	private FollowUp selectedFollowUp;
	
	// CONTRATO
	private ContractBO contractBO;
	private StreamedContent studentContract;
	
	//STUDENT PAYMENTS
	private StudentPaymentsBO studentPaymentsBO; 
	private List<StudentPayments> student_payments;
	private StudentPayments selectedStudentPayments;
	
	private int quantity_parcel;
	private Date expiry_date;
	private double price;

	private Date new_expiry_date;
	private double new_price;
	
	//PAYMENTS
	private String barcode;
	private StudentPayments studentPayment;
	
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
		callStudentBO = new CallStudentBO(em);
		studentInfoBO = new StudentInfoBO(em);
		contractBO = new ContractBO(em);
		
//		students = studentBO.listStudentByCompany(userInfo.getEmployee().getCompany());
		
		loadListsInfo();
	}
	
	
	// Carrega todas as listas deste Bean
	
	public void loadListsInfo(){
		students = studentBO.listByBranch(userInfo.currentBranch);
		modules = classModuleBO.listByBranch(userInfo.currentBranch);
		classStudents = classStudentBO.listByBranch(userInfo.currentBranch);
	}
	
	

	//METODOS PARA CADASTRA ALUNO
	public void newStudent(){
		this.newStudent = new Student();
	}
	
	public void addStudent(){
		try {
			newStudent.setCompany(userInfo.getEmployee().getCompany());
			newStudent.setBranch(userInfo.currentBranch);
			studentBO.insert(newStudent);
			students = studentBO.listByBranch(userInfo.currentBranch);
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
				students = studentBO.listByBranch(userInfo.currentBranch);
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
				students = studentBO.listByBranch(userInfo.currentBranch);
				selectedStudent = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudent", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Aluno Atualizada com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudent", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar um Aluno"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudent", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void filterStudentByClass(){
		try {
			if(idClassStudentFilter !=null){
				if(idClassStudentFilter == -1){
					students = studentBO.listStudentWithoutClass(userInfo.currentBranch);
				}else{
					students = classStudentBO.findById(idClassStudentFilter).getStudents();
				}
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudent", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar a turma"));
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
			newModule.setBranch(userInfo.currentBranch);
			classModuleBO.insert(newModule);
			newModule.setSequence(newModule.getId_class_module());
			classModuleBO.update(newModule);
			modules = classModuleBO.listByBranch(userInfo.currentBranch);
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
				modules = classModuleBO.listByBranch(userInfo.currentBranch);
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
				modules = classModuleBO.listByBranch(userInfo.currentBranch);
				selectedModule = null;
				FacesContext.getCurrentInstance().addMessage("formManager:msgClassModule", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Modulo Atualizada com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgClassModule", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário selecionar um modulo"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgClassModule", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void updateModuleSeq(){
		try {
			for (int seq = 0; seq < modulesList.size(); seq++) {
				for (ClassModule module : modules) {
					if (module.getName().equals(modulesList.get(seq))) {
						module.setSequence(seq);
						classModuleBO.update(module);
					}
				}
			}
			modules = classModuleBO.listByBranch(userInfo.currentBranch);
			FacesContext.getCurrentInstance().addMessage("formManager:msgClassModule", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Sequencia atualizada com sucesso"));
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
			newClassStudent.setBranch(userInfo.currentBranch);
			classStudentBO.insert(newClassStudent);
			classStudents = classStudentBO.listByBranch(userInfo.currentBranch);
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
				classStudents = classStudentBO.listByBranch(userInfo.currentBranch);
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
				classStudents = classStudentBO.listByBranch(userInfo.currentBranch);
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
			
			idClassStudent = classStudentBO.findClassIdByStudent(selectedStudent);
			studentInfoClass = classStudentBO.findById(idClassStudent);
			
			student_payments = studentPaymentsBO.listStudentPayments(this.selectedStudent);
			resetStudentPaymentInfo();
			return "student_info?faces-redirect=true";
		}else{
			return "";
		}
	}
	
	public void addFollowUp(){
		try {
			if(newFollowUp.getFollowup() == null){
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "Nescessário preencher o FollowUp!"));
			}else{
				newFollowUp.setStudent(this.selectedStudent);
				newFollowUp.setEmployee(userInfo.getEmployee());
				newFollowUp.setDate_followup(new Date());
				newFollowUp.setBranch(userInfo.currentBranch);
				followUpBO.insert(newFollowUp);
				followups = followUpBO.listFollowUpByStudent(selectedStudent);// FAZER PERQUISA POR NOME DA EMPRESA E PELO BRANCH  ???
				newFollowUp = new FollowUp();
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "FollowUp inserido com sucesso"));
			}
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
			
			if(idClassStudent == -1 ){
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Nescessário selecionar uma turma, para lançar o financeiro!"));
			}else if (expiry_date == null || quantity_parcel <= 0 || price <= 0.0 ) {
				if(expiry_date == null) FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Nescessário selecionar data de vencimento."));
				if(quantity_parcel <= 0) FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Quantidade de parcelas inválido."));
				if(price <= 0) FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Valor para pagamento inválido."));
			}else {
				for (int i = 1; i <= quantity_parcel; i++) {
					StudentPayments sp = new StudentPayments();
					
					if(i>1){
						expiry_date = DateUtil.updateDate(expiry_date);
					}
					sp.setExpiry_date(expiry_date);
					sp.setNumber_parcel(i);
					sp.setPrice(price);
					sp.setStudent(this.selectedStudent);
					sp.setBranch(userInfo.currentBranch);
					sp.setCompany(userInfo.getEmployee().getCompany());
					
					studentPaymentsBO.insert(sp);
					
					String barcode = StudentPaymentUtil.generateStudentPaymentBarcode(sp);
					sp.setBarcode(barcode);
					studentPaymentsBO.update(sp);
				}
				
				student_payments = studentPaymentsBO.listStudentPayments(this.selectedStudent);
				resetStudentPaymentInfo();
				
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Financeiro atualizado com sucesso"));
				
				
				CompanyBean companyInfo = CompanyUtil.getCompanyInfo();
				
				if (companyInfo != null) companyInfo.loadlistsInfo();
								
				RequestContext.getCurrentInstance().update("formManager");
			}
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
				
				if((selectedStudentPayments.getPaid() <= 0.0) && (new_expiry_date == null || new_price <= 0.0 )){
					FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Falha ao efetuar o pagamento."));
				}
				
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
			alterStudentClass();
			if(studentInfoClass != null)
				classStudentBO.update(studentInfoClass);
			studentBO.update(selectedStudent);
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Atualizações efetuada com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
		}
	}
	
	public void generateContract(){
		try {
			if(selectedStudent != null){
				if(idClassStudent == -1 ){
					FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Nescessário selecionar uma turma, para selecionar o contrato!"));
				}else{
					Contract contract = new Contract();
					contractBO.insert(contract);
					selectedStudent.setContract(contract);
					studentBO.update(selectedStudent);
				}
			}else{
				
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falha ao gerar o contrato." + e.getMessage()));
		}
	}
	

	public void updateStudentContract(){
		if(selectedStudent != null){
			try {
				// atualiza as informacoes do aluno com o contrato selecionado. 
				studentBO.update(selectedStudent);
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Contrato gerado com sucesso"));	
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falha ao gerar o contrato." + e.getMessage()));
			}
		}
	}
	// Metodo para trocar de turma.
	
	public void alterStudentClass(){
		if(studentInfoClass != null && 
				studentInfoClass.getId_class_student() == idClassStudent){
			return;
		}else{
			if(studentInfoClass != null){
				studentInfoClass.removeStudent(selectedStudent);
				classStudentBO.update(studentInfoClass);
			}
			if(idClassStudent != -1){
				studentInfoClass = classStudentBO.findById(idClassStudent);
				studentInfoClass.addStudent(selectedStudent);
				selectedStudent.setClass_registered(true);
			}
		}
	}
	
	public void fileUpload(FileUploadEvent event) {
  		try {
//  			UploadedFile arq = event.getFile();
        selectedStudent.setImage(event.getFile().getContents());
        studentBO.update(selectedStudent);
        
        
  			FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Atualização efetuada com sucesso"));
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
	
	public String createCall(){
		if(selectedClassStudent!=null){
			selectedCall = new CallStudent();
			selectedCall.setCall_date(new Date());
			selectedCall.setTeacher(userInfo.getEmployee());
			selectedCall.setBranch(userInfo.currentBranch);
			
			selectedCall.addStudentsInfo(selectedClassStudent.getStudents());
			try {
//				for (StudentInfo studentInfo : selectedCall.getStudents_info()) {
//					studentInfoBO.insert(studentInfo);
//				}
				
				callStudentBO.insert(selectedCall);
				selectedClassStudent.addCall(selectedCall);
				
				classStudentBO.update(selectedClassStudent);
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage("formManager:msgStudentCall", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falha ao criar Chamada"));
				return "";
			}
			return "call?faces-redirect=true";
		}else{
			return "";
		}
	}
	
	public String completeCall(){
		try {
			callStudentBO.update(selectedCall);
//			for (StudentInfo student_info : selectedCall.getStudents_info()) {
//				studentInfoBO.update(student_info);
//			}
			selectedClassStudent.addCall(selectedCall);
			selectedClassStudent.updateClassControl();
			classStudentBO.update(selectedClassStudent);	
			
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudentCall", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Chamada concluida."));
			
			return "student_call?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgCall", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Falha ao Finalizar Chamada"));
			return "";
		}
	}
	
	public String openCallDetails(){
		if(selectedCallDetails!=null){
			
			
//		
//		followups = followUpBO.listFollowUpByStudent(this.selectedStudent);
//		newFollowUp = new FollowUp();
		
//		student_payments = studentPaymentsBO.listStudentPayments(this.selectedStudent);
//		resetStudentPaymentInfo();

		return "call_details?faces-redirect=true";
	}else{
		return "";
	}
	}
	
	public void updateStudentInfo(){
		try {
			studentInfoBO.update(selectedStudentInfo);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgCallDetail", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Erro ao atulizar informacoes! " + e.getMessage()));
		}
	}
	//METODOS PARA PAGAMENTO DE BOLETO 
	
	public void findStudentPayment(){
		try {
			studentPayment = studentPaymentsBO.findByBarcode(barcode);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgPayment", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Boleto nao encontrado!"));
		}
	}
	
	public void cancelStudentPayment(){
		studentPayment = null;
		barcode = "";
	}
	
	public void payStudentPayment(){
		try {
			if(studentPayment!=null){
				studentPayment.setPayment_date(new Date());
				studentPayment.setPaid(studentPayment.getPrice());
				
				studentPaymentsBO.update(studentPayment);
				
				ManagerSaleFacadeInterface manager = ManagerPaymentFactory.newInstance(studentPayment);
				manager.persistSale();
				
				studentPayment = null;
				barcode = "";
				FacesContext.getCurrentInstance().addMessage("formManager:msgPayment", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Pagamento efetuado com sucesso"));
			}else{
				FacesContext.getCurrentInstance().addMessage("formManager:msgPayment", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta!", "Nescessário pesquisar um Boleto!"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgPayment", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage() + " " + e.getCause()));
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
	public Integer getIdClassStudentFilter() {
		return idClassStudentFilter;
	}
	public void setIdClassStudentFilter(Integer idClassStudentFilter) {
		this.idClassStudentFilter = idClassStudentFilter;
	}
	public CallStudent getSelectedCall() {
		return selectedCall;
	}
	public void setSelectedCall(CallStudent selectedCall) {
		this.selectedCall = selectedCall;
	}
	public CallStudent getSelectedCallDetails() {
		return selectedCallDetails;
	}
	public void setSelectedCallDetails(CallStudent selectedCallDetails) {
		this.selectedCallDetails = selectedCallDetails;
	}
	public StudentInfo getSelectedStudentInfo() {
		return selectedStudentInfo;
	}
	public void setSelectedStudentInfo(StudentInfo selectedStudentInfo) {
		this.selectedStudentInfo = selectedStudentInfo;
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
	public List<String> getModulesList() {
		Map<Integer, String> orderList = new TreeMap<Integer, String>();
		for (ClassModule module : modules) {
			orderList.put(module.getSequence(), module.getName());
		}
		return  new ArrayList<String>(orderList.values());
	}
	public void setModulesList(List<String> modulesList) {
		this.modulesList = modulesList;
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
	public Integer getIdClassStudent() {
		return idClassStudent;
	}
	public void setIdClassStudent(Integer idClassStudent) {
		this.idClassStudent = idClassStudent;
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
	
	public StreamedContent getStudentContract() {
		try {
			ManagerReport manager =  ManagerReportFactory.newInstanceContract(selectedStudent);
			studentContract = new DefaultStreamedContent(manager.generateReport(), "", "contrato_"+selectedStudent.getStudent_name().trim()+".pdf");
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("formManager:msgStudentInfo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Falha ao efetuar o download do contrato."));
		}
		return studentContract;
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
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public StudentPayments getStudentPayment() {
		return studentPayment;
	}
	public void setStudentPayment(StudentPayments studentPayment) {
		this.studentPayment = studentPayment;
	}
}
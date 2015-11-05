package br.com.wgengenharia.manager.report.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.wgengenharia.manager.model.Student;
import br.com.wgengenharia.manager.report.AbstractManagerReport;
import br.com.wgengenharia.manager.report.model.entity.StudentContract;

public class ContractReporter extends AbstractManagerReport {

	private Student student;
	
	private static final String CONTRACT = "studentContract";
	
	
	public ContractReporter(Student student) {
		this.student = student;
	}
	
	@Override
	public InputStream generateReport() throws JRException {
		StudentContract studentContract = parserStudent();
		List<StudentContract> students = new ArrayList<StudentContract>();
		students.add(studentContract);
		InputStream is = null;
		
		try {
			JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + CONTRACT + student.getContract().getType() +".jrxml");
			JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(students));
//			JasperExportManager.exportReportToPdfFile(print, "C:/Users/william.galindo/Desktop/firstReportaa.pdf");
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(print, out);
			is = new ByteArrayInputStream(out.toByteArray());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return is;
	}
	
	
	private StudentContract parserStudent(){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		StudentContract studentContract = new StudentContract();
		
		studentContract.setAluno(student.getStudent_name());
		if(student.getStudent_birth_date()!= null) studentContract.setAluno_nascimento((df.format(student.getStudent_birth_date())));
		studentContract.setBairro(student.getResp_address().getDistrict());
		studentContract.setCep(student.getResp_address().getZip_code());
		studentContract.setCidade(student.getResp_address().getCity());
		studentContract.setContratante(student.getResp_Name());
		studentContract.setCpf(student.getResp_cpf());
		studentContract.setEndereco(student.getResp_address().getStreet());
		if(student.getResp_birth_date()!= null) studentContract.setNascimento((df.format(student.getResp_birth_date())));
		studentContract.setRg(student.getResp_rg());
		studentContract.setTelefone_com(student.getResp_office_phone());
		studentContract.setTelefone_res(student.getResp_home_phone());
		
		return studentContract; 
		
	}
	

}

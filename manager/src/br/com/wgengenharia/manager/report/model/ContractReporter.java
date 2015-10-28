package br.com.wgengenharia.manager.report.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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

public class ContractReporter extends AbstractManagerReport {

	private Student student;
	
	public ContractReporter(Student student) {
		this.student = student;
	}
	
	@Override
	public InputStream generateReport() throws JRException {
		
		List<Student> students = new ArrayList<Student>();
		students.add(student);
		JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "StudentContract.jrxml");
		
		
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(students));
 
		JasperExportManager.exportReportToPdfFile(print, "C:/Users/william.galindo/Desktop/firstReport.pdf");
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		JasperExportManager.exportReportToPdfStream(print, out);
		
		InputStream is = new ByteArrayInputStream(out.toByteArray());

		
		return is;
	}

}

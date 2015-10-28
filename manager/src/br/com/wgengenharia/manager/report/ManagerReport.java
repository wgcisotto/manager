package br.com.wgengenharia.manager.report;

import java.io.InputStream;

import net.sf.jasperreports.engine.JRException;

public interface ManagerReport {

	abstract InputStream generateReport() throws JRException;
	
	public String getPathToReportPackage();

	public String getPath();
	
}

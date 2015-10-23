package br.com.wgengenharia.manager.report.model;

import java.io.InputStream;

import net.sf.jasperreports.engine.JRException;


public abstract class AbstractManagerReport implements ManagerReport {

	private String path; //Caminho base
	
	private String pathToReportPackage; // Caminho para o package onde estão armazenados os relatorios Jarper
	
	public AbstractManagerReport() {
		StringBuilder sb = new StringBuilder();
		sb.append("/").append(System.getProperty("jboss.server.base.dir")).append("/deployments/manager.war/WEB-INF/classes/br/com/wgengenharia/manager/jasper/");
		pathToReportPackage = sb.toString().replaceAll("\\\\","/");
	}
	
	
	public String getPathToReportPackage(){
		return pathToReportPackage;
	}

	public String getPath(){
		return path;
	}
	
	
	public abstract InputStream generateReport() throws JRException;
	
}

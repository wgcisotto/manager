package br.com.wgengenharia.manager.report;

import java.io.InputStream;

import br.com.wgengenharia.manager.facade.Config;

import net.sf.jasperreports.engine.JRException;


public abstract class AbstractManagerReport implements ManagerReport {

	private String path; //Caminho base
	
	
	public AbstractManagerReport() {
		this.path = Config.get("manager-jasper");
	}
	
	
	public String getPath(){
		return path;
	}
	
	
	public abstract InputStream generateReport() throws JRException;
	
}

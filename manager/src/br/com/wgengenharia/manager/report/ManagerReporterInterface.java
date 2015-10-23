package br.com.wgengenharia.manager.report;

import java.io.InputStream;
import java.util.List;

import br.com.wgengenharia.manager.model.Student;

public interface ManagerReporterInterface {

	public abstract InputStream generate(Student student) throws Exception;

	public abstract String getPathToReportPackage();

	public abstract String getPath();

}
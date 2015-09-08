package br.com.wgengenharia.manager.utils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.wgengenharia.manager.view.bean.StudentBean;


public class StudentUtil {
	


	public static StudentBean getStudentInfo() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);

		StudentBean studentInfo = (StudentBean) session.getAttribute("student");
		
		return studentInfo;
	}

}

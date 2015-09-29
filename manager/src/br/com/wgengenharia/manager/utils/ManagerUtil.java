package br.com.wgengenharia.manager.utils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.wgengenharia.manager.view.bean.ManagerBean;


public class ManagerUtil {
	


	public static ManagerBean getManagerInfo() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);

		ManagerBean managerInfo = (ManagerBean) session.getAttribute("manager");
		
		return managerInfo;
	}

}

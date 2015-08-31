package br.com.wgengenharia.manager.utils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.wgengenharia.manager.view.bean.CompanyBean;


public class CompanyUtil {
	


	public static CompanyBean getCompanyInfo() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);

		CompanyBean companyInfo = (CompanyBean) session.getAttribute("company");
		
		return companyInfo;
	}

}

package br.com.wgengenharia.manager.utils;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.wgengenharia.manager.seguranca.bean.AuthenticationBean;


public class AuthenticationUtil {
	


	public static AuthenticationBean getUserInfo() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);

		AuthenticationBean userInfo = (AuthenticationBean) session.getAttribute("auth");
		
		return userInfo;
	}

}

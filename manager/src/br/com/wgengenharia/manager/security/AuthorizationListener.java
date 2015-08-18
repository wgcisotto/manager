package br.com.wgengenharia.manager.security;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class AuthorizationListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
	  String currentPage = facesContext.getViewRoot().getViewId();
 
    boolean isLoginPage = (currentPage.lastIndexOf("login.xhtml") > -1);
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    Object currentUser = session.getAttribute("user");
	 
	  if (!isLoginPage && currentUser == null) {
	  	NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
	  	nh.handleNavigation(facesContext, null, "loginPage");
	  }
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}

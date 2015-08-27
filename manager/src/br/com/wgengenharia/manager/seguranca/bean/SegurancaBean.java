package br.com.wgengenharia.manager.seguranca.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.wgengenharia.manager.model.Module;
import br.com.wgengenharia.manager.utils.AuthenticationUtil;

@ManagedBean(name="sec")
@SessionScoped
public class SegurancaBean {

	
	private AuthenticationBean userInfo;
	
	public SegurancaBean() {
		userInfo = AuthenticationUtil.getUserInfo();
	}
	
	public boolean hasModule(String mod){
		for (Module module: userInfo.getEmployee().getCompany().getModules()) {
			if(module.getType().equals(mod)){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasProfile(String prof){
		if(userInfo.getEmployee().getProfile().getName().equals(prof)){
			return true;
		}
		return false;
	}
	
	
	
	
}

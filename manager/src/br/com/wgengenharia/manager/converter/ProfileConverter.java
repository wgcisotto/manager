package br.com.wgengenharia.manager.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.wgengenharia.manager.model.Profile;
import br.com.wgengenharia.manager.utils.CompanyUtil;
import br.com.wgengenharia.manager.view.bean.CompanyBean;

/**
 * @author William.Galindo
 *
 */

public class ProfileConverter implements Converter {
	
  
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		CompanyBean companyBean = CompanyUtil.getCompanyInfo();
		for (Profile p : companyBean.getProfiles()) {
			if(p.getName().equals(arg2)){
				return p;
			}
		}
		return new Profile();
	}
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Profile p = (Profile) arg2;
		return p.getName();
	}
	




}

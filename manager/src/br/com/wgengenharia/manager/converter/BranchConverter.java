package br.com.wgengenharia.manager.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.wgengenharia.manager.model.Branch;
import br.com.wgengenharia.manager.model.Profile;
import br.com.wgengenharia.manager.utils.CompanyUtil;
import br.com.wgengenharia.manager.view.bean.CompanyBean;

/**
 * @author William.Galindo
 *
 */

public class BranchConverter implements Converter {
	
  
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		CompanyBean companyBean = CompanyUtil.getCompanyInfo();
		for (Branch b: companyBean.getBranchs()) {
			if(b.getName().equals(arg2)){
				return b;
			}
		}
		return new Profile();
	}
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Branch b = (Branch) arg2;
		return b.getName();
	}
	




}

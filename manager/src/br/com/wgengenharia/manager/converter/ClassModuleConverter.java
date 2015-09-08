package br.com.wgengenharia.manager.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.wgengenharia.manager.model.ClassModule;
import br.com.wgengenharia.manager.model.Profile;
import br.com.wgengenharia.manager.utils.StudentUtil;
import br.com.wgengenharia.manager.view.bean.StudentBean;

/**
 * @author William.Galindo
 *
 */

public class ClassModuleConverter implements Converter {
	
  
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		StudentBean studentInfo = StudentUtil.getStudentInfo();
		for (ClassModule m : studentInfo.getModules()) {
			if(m.getName().equals(arg2)){
				return m;
			}
		}
		return new Profile();
	}
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		ClassModule m = (ClassModule) arg2;
		return m.getName();
	}
	




}

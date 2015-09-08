package br.com.wgengenharia.manager.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.wgengenharia.manager.model.ClassStudent;
import br.com.wgengenharia.manager.model.Profile;
import br.com.wgengenharia.manager.utils.StudentUtil;
import br.com.wgengenharia.manager.view.bean.StudentBean;

/**
 * @author William.Galindo
 *
 */

public class ClassStudentConverter implements Converter {
	
  
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		StudentBean studentInfo = StudentUtil.getStudentInfo();
		for (ClassStudent cs : studentInfo.getClassStudents()) {
			if(cs.getClass_name().equals(arg2)){
				return cs;
			}
		}
		return new Profile();
	}
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		ClassStudent cs = (ClassStudent) arg2;
		return cs.getClass_name();
	}
	




}

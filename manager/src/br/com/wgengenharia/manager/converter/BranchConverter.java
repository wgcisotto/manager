package br.com.wgengenharia.manager.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.wgengenharia.manager.model.Branch;
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
		if(!"".equals(arg2)){
			for (Branch b: companyBean.getBranchs()) {
				if(b.getName().equals(arg2)){
					return b.getId_branch();
				}
			}
		}
		return new Branch();
	}
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		try {
			CompanyBean companyBean = CompanyUtil.getCompanyInfo();
			if(arg2 != null){
				for (Branch b: companyBean.getBranchs()) {
					if(b.getId_branch() == Integer.parseInt(arg2.toString())){
						return b.getName();
					}
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO no converter: " + e.getMessage());
		}
		return "0";
	}
	




}

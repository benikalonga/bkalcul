package beni.momentum.bkalcul.models;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AdminBean {

	
	public String showBasicCalc() {
		return "/basic.xhtml?faces-redirect=true";
	}
	public String showAdvancedCalc() {
		return "/advanced.xhtml?faces-redirect=true";
	}
}

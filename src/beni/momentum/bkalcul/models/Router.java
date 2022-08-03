package beni.momentum.bkalcul.models;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import beni.momentum.bkalcul.plus.Session;

@ManagedBean
@SessionScoped
public class Router {

	String currentUrl;
	String previousUrl;
	
	public String showBasicCalc() {
		return "/basic.xhtml?faces-redirect=true";
	}
	public String showAdvancedCalc() {
		return "/advanced.xhtml?faces-redirect=true";
	}
	public String showAdmin() {
		return "/admin.xhtml?faces-redirect=true";
	}
	public String showIndex() {
		return "/index.xhtml?faces-redirect=true";
	}
	public String showAbout() {
		return "/about.xhtml?faces-redirect=true";
	}
	public String showHome() {
		if(Session.get().getUser()!=null && Session.get().getUser().isAdmin) {
			return showAdmin();
		}
		else
			return showBasicCalc();
	}
	public String goPrevious() {
		return previousUrl;
	}
}

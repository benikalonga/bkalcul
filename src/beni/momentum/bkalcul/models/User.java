package beni.momentum.bkalcul.models;


import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import beni.momentum.bkalcul.controlers.UserDAO;
import beni.momentum.bkalcul.plus.Session;
import beni.momentum.bkalcul.plus.Utils;

@ManagedBean
@ViewScoped
public class User implements Serializable {

	//Operations
	private static final long serialVersionUID = -628939007563015113L;
	
	public String username;
	public String password;
	public boolean isAdmin;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}	
	
	public User() {
		
		
		if(Session.get().getUser() != null ) {
    		User user = Session.get().getUser();
    		
    		System.out.println("User exist" +user);
    		
    		setUsername(user.username);
    		setPassword(password);
    		setAdmin(user.isAdmin);
    		
    		if(Utils.getRequest().getServletPath().contains("index.xhtml")) {
    			if(user.isAdmin) {
					Utils.redirectTo("admin.xhtml");
        		}else {
					Utils.redirectTo("basic.xhtml");
        		}
    		}		
    	}else {
    		
    	}
	}
	public List<User> usersList(){
		return new UserDAO().readAll();
	}

    @Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", isAdmin=" + isAdmin + " ]";
	}

	public String login() {
		User user = new UserDAO().read(username, password);
		if(user == null) {
			Utils.addMessage(FacesMessage.SEVERITY_ERROR, "Loggin Error","Check your credentials and retry" );
			Utils.addMessage(FacesMessage.SEVERITY_ERROR, "Loggin Error","Make sure you have an internet connexion" );
			return "";
		}
		else {
			try {
				Session.get().putUser(user);
				System.out.println("Save User "+user.toString());			
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			if(user.isAdmin) {
				return "/admin.xhtml?faces-redirect=true";
			}
			else {
				return "/basic.xhtml?faces-redirect=true";
			}
		}
		
	}
	public void logout() {
		try {
			Session.get().delelteUser();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("User LogOut");
		Utils.redirectTo("index.xhtml");
	}	
}


package beni.momentum.bkalcul.plus;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import beni.momentum.bkalcul.models.User;


/**
 * This class manage the session to retrieve data, user login, ....
 * @author Beni Kalonga
 *
 */
public class Session {
	

	private HttpSession httpSession;
	
	public static Session session;
	public static Session get() {
		if(session == null) {
			session  = new Session();
		}
		return session;
	}
	
	private Session() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		httpSession = (HttpSession) facesContext.getExternalContext().getSession(true);
		
	}
	public User getUser() {
		if(httpSession.getAttribute("userSession") != null) {
			return (User) httpSession.getAttribute("userSession");
		}
		return null ;
	}
	public void putUser(User user) {
		httpSession.setAttribute("userSession", user);
	}
	public void delelteUser() {
		httpSession.removeAttribute("userSession");
	}
	public  HttpSession httpSession() {
		return httpSession;
	}
	
}

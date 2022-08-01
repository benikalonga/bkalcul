package beni.momentum.bkalcul.plus;

import java.util.Map;

import javax.faces.context.FacesContext;

import beni.momentum.bkalcul.models.User;


/**
 * This class manage the session to retrieve data, user login, ....
 * @author Beni Kalonga
 *
 */
public class Session {
	

	private Map<String,Object> sessionMap;
	
	public static Session session;
	public static Session get() {
		if(session == null) {
			session  = new Session();
		}
		return session;
	}
	
	private Session() {
		sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}
	public User getUser() {
		if(sessionMap.get("userSession") != null) {
			return (User) sessionMap.get("userSession");
		}
		return null ;
	}
	public void putUser(User user) {
		sessionMap.put("userSession", user);
	}
	public void delelteUser() {
		sessionMap.remove("userSession");
	}
	public  Map<String,Object> sessionMap() {
		return sessionMap;
	}
	
}

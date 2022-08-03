package beni.momentum.bkalcul.plus;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class Utils {
	
	public static boolean isTxtEmpty(String text) {
		return text == null || text.length() == 0;
	}

	public static void addMessage(FacesMessage.Severity severity, String title, String msg) {
		FacesMessage message = new FacesMessage(severity, title, msg);
        getFacesContext().addMessage(null, message);
	}


	public static void addMessage(String msgID, FacesMessage.Severity severity, String title, String msg) {
		FacesMessage message = new FacesMessage(severity, title, msg);
		getFacesContext().addMessage(null, message);
	}
	
	public static void redirectTo(String path) {
		try {
			getFacesContext().getExternalContext().redirect(getRequest().getContextPath() +"/"+path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HttpServletRequest getRequest() {
	    return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
	}
	public static FacesContext getFacesContext() {
	    return FacesContext.getCurrentInstance();
	}

}

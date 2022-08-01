package beni.momentum.bkalcul.plus;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Utils {
	
	public static boolean isTxtEmpty(String text) {
		return text == null || text.length() == 0;
	}

	public static void addMessage(FacesMessage.Severity severity, String title, String msg) {
		FacesMessage message = new FacesMessage(severity, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}

}

package beni.momentum.bkalcul.models;

import java.sql.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import beni.momentum.bkalcul.plus.Bkalculator;
import beni.momentum.bkalcul.plus.Session;
import beni.momentum.bkalcul.plus.Utils;

@ManagedBean
public class AdvancedCalculator {
	
	public String result;
	public String request;
	private long timeElapsed;

	public String getResult() {
		return result;
	}

	public long getTimeElapsed() {
		return timeElapsed;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setTimeElapsed(long timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public void performEvaluation() {

		String curReq = request;
		 
		curReq = curReq.replaceAll("π", "pi");
		curReq = curReq.replaceAll("√", "r");
		curReq = curReq.replaceAll("−", "-");
		curReq = curReq.replaceAll("÷", "/");
		curReq = curReq.replaceAll("x", "*");
		
		System.out.println("CurR "+curReq);
		
		//Calls the Calculator class and get the result
		Bkalculator.getCalculator().evaluate(curReq, (req, res, time) -> {

			System.out.println(req + " = " + res);
			System.out.println("Time executed " + time);
			
			setRequest(request);
			setResult(res);
			setTimeElapsed(time);
			
			recordRequest();

			Utils.addMessage(FacesMessage.SEVERITY_INFO, request+"="+res, "\nTime taken : "+time+" nanosecs");

		});

	}
	
	public void recordRequest() {

User user = Session.get().getUser();
		
		try {
			System.out.println("User "+user.getUsername());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Record r = new Record();
		r.setUsername(user == null || Utils.isTxtEmpty(user.username)? "---" : user.username);
		r.setCalcRequest(request);
		r.setAnswer(result);
		r.setTimeCalc(timeElapsed);
		r.setDateInserted(new Date(System.currentTimeMillis()));

		r.save();

	}
}

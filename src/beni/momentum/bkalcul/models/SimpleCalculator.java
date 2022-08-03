package beni.momentum.bkalcul.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import beni.momentum.bkalcul.plus.Bkalculator;
import beni.momentum.bkalcul.plus.Session;
import beni.momentum.bkalcul.plus.Utils;

@ManagedBean
@SessionScoped
public class SimpleCalculator {

	public String firstOp;
	public String secondOp;
	public String operande;

	public String result;
	public String request;
	private long timeElapsed;

	//List Record
	List<Record> recordsList= new ArrayList<Record>();

	public String getFirstOp() {
		return firstOp;
	}

	public void setFirstOp(String firstOp) {
		this.firstOp = firstOp;
	}

	public String getSecondOp() {
		return secondOp;
	}

	public void setSecondOp(String secondOp) {
		this.secondOp = secondOp;
	}

	public String getOperande() {
		return operande;
	}

	public void setOperande(String operande) {
		this.operande = operande;
	}

	//

	public String getResult() {
		return result;
	}

	public long getTimeElapsed() {
		return timeElapsed;
	}

	public String getRequest() {
		return request;
	}
	
	// Operations

	public void setResult(String result) {
		this.result = result;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public void setTimeElapsed(long timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public void performEvaluation() {

		String curOp = operande;
		
		if(curOp.equals("−")) curOp = "-";
		else if(curOp.equals("x")) curOp = "*";
		else if(curOp.equals("÷")) curOp = "/";
		
		//Calls the Calculator class and get the result
		String reqst = new StringBuilder().append(firstOp).append(curOp).append(secondOp).toString();
		
		Bkalculator.getCalculator().evaluate(reqst, (req, res, time) -> {

			System.out.println(req + " = " + res);
			System.out.println("Time executed " + time);
			
			setRequest(firstOp+operande+secondOp);
			setResult(res);
			setTimeElapsed(time);
			
			recordRequest();
			

			Utils.addMessage("messages",FacesMessage.SEVERITY_INFO, request+"="+res, "\nTime taken : "+time+" nanosecs");
			
	
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
		
		String currentUser = user == null || Utils.isTxtEmpty(user.username)? "---" : user.username;
		
		Record r = new Record();
		r.setUsername(currentUser);
		r.setCalcRequest(request);
		r.setAnswer(result);
		r.setTimeCalc(timeElapsed);
		r.setDateInserted(new Date(System.currentTimeMillis()));

		r.save();
		
		recordsList.add(r);
	}
	
	public boolean renderMessage() {
		return !Utils.isTxtEmpty(result);
	}
	public String getMessage() {
		return "";
	}
	

	public List<Record> getRecordsList() {
		return recordsList;
	}

	public void setRecordsList(List<Record> recordsList) {
		this.recordsList = recordsList;
	}

	
}

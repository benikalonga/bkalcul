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

		//Calls the Calculator class and get the result
		String reqst = new StringBuilder().append(firstOp).append(operande).append(secondOp).toString();
		Bkalculator.getCalculator().evaluate(reqst, (req, res, time) -> {

			System.out.println(req + " = " + res);
			System.out.println("Time executed " + time);
			
			setRequest(req);
			setResult(res);
			setTimeElapsed(time);
			
			recordRequest();
			
			Utils.addMessage(FacesMessage.SEVERITY_INFO, req+"="+res, "\nTime taken : "+time+" nanosecs");
			
		});

	}
	
	public void recordRequest() {

		User user = Session.get().getUser();
		
		System.out.println("User "+user.getUsername());
		
		Record r = new Record();
		r.setUsername(user.username);
		r.setCalcRequest(request);
		r.setAnswer(result);
		r.setTimeCalc(timeElapsed);
		r.setDateInserted(new Date(System.currentTimeMillis()));

		r.save();
		
		recordsList.add(r);
	}

	public List<Record> getRecordsList() {
		return recordsList;
	}

	public void setRecordsList(List<Record> recordsList) {
		this.recordsList = recordsList;
	}

	
}

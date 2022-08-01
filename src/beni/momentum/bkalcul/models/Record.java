package beni.momentum.bkalcul.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import beni.momentum.bkalcul.controlers.RecordDAO;
import beni.momentum.bkalcul.plus.Session;
import beni.momentum.bkalcul.plus.Utils;

@ManagedBean
@SessionScoped
public class Record {

	private static final long serialVersionUID = -1215681812021214208L;

	public int id;
	public String calcRequest;
	public String answer;
	public long timeCalc;
	public Date dateInserted;
	public String username;

	// filtre
	public String usernameFilter;
	private String dateFrom;
	private String dateTo;
	public String txtFilter;
	List<Record> recordsList;
	
    private List<Date> dateRange;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
			return username;
	}
	public String getUsername2() {
		if(!Utils.isTxtEmpty(username) && username.equals(Session.get().getUser().username)) {
			return "Me";
		}
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCalcRequest() {
		return calcRequest;
	}

	public void setCalcRequest(String calcRequest) {
		this.calcRequest = calcRequest;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public long getTimeCalc() {
		return timeCalc;
	}

	public void setTimeCalc(long timeCalc) {
		this.timeCalc = timeCalc;
	}

	public Date getDateInserted() {
		return dateInserted;
	}

	public void setDateInserted(Date dateInserted) {
		this.dateInserted = dateInserted;
	}
	
	public String getTxtFilter() {
		return txtFilter;
	}

	public void setTxtFilter(String txtFilter) {
		this.txtFilter = txtFilter;
	}
	
	

	public String getUsernameFilter() {
		return usernameFilter;
	}

	public void setUsernameFilter(String usernameFilter) {
		this.usernameFilter = usernameFilter;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	

	public List<Date> getDateRange() {
		return dateRange;
	}

	public void setDateRange(List<Date> dateRange) {
		this.dateRange = dateRange;
	}

	public boolean contains(String text) {
		text = text.toLowerCase();
		return username.toLowerCase().contains(text) ||
			   calcRequest.toLowerCase().contains(text) ||
			   answer.toLowerCase().contains(text) ||
			   String.valueOf(timeCalc).toLowerCase().contains(text) ||
			   dateInserted.toString().toLowerCase().contains(text);
	}

	// Operations
	public boolean save() {
		return RecordDAO.get().create(this);
	}
	public void deleteRecord() {
		
	}

	public void onUserNameChange() {
		System.out.println("User "+usernameFilter);
		//updateFilter();
	}
	
	public void updateFilter() {
		recordsList();
	}
	
	public void cancelFilter() {
		if(dateRange!=null) dateRange.clear();
		dateFrom = "";
		dateTo = "";
		usernameFilter = "";
	}
	
	// List Records
	public List<Record> recordsList() {
		// Filter
		// if by username
		
		if(dateRange!=null) {
			java.util.Date d0 = dateRange.get(0);
			java.util.Date d1 = dateRange.get(1);
			
			dateFrom = d0.getYear()+"-"+d0.getMonth()+"-"+d0.getDay();
			dateTo = d1.getYear()+"-"+d1.getMonth()+"-"+d0.getDay();
			
		}
		
		if (!Utils.isTxtEmpty(usernameFilter) && Utils.isTxtEmpty(dateFrom) && Utils.isTxtEmpty(dateTo)) {
			recordsList = RecordDAO.get().readAllByUsername(usernameFilter);
		}
		// if by dateFrom to dateTo
		else if (Utils.isTxtEmpty(usernameFilter) && !Utils.isTxtEmpty(dateFrom) && !Utils.isTxtEmpty(dateTo)) {
			recordsList = RecordDAO.get().readAllByDate(dateFrom, dateTo);
		}
		// if by username, dateFrom and dateTo
		else if (Utils.isTxtEmpty(usernameFilter) && !Utils.isTxtEmpty(dateFrom) && !Utils.isTxtEmpty(dateTo)) {
			recordsList = RecordDAO.get().readAllByUsernameDate(usernameFilter, dateFrom, dateTo);
		}
		// No filter
		else recordsList = RecordDAO.get().readAll();
		
		System.out.println("List All "+recordsList);
		
		return recordsList;

	}
	
	public String detail(Record d) {
		Session.get().sessionMap().put("recordDetail", d);
		return "/detail.xhtml?faces-redirect=true";
	}

	public void delete(int i) {
		boolean isDeleted = RecordDAO.get().delete(id);
		System.out.println("Delete "+isDeleted);
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", calcRequest=" + calcRequest + ", answer=" + answer + ", timeCalc=" + timeCalc
				+ ", dateInserted=" + dateInserted + "]";
	}

}

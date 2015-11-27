package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.Date;


public class EmployResume  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1202655331032949090L;
	
	public Long employID;
	
	public Long candinfo_id;
	
	public String compName;
	
	public String title;
	
	public String projectRole;
	
	public String projDescpt; 
	
	public Date fromMonth;
	
	/*public String fromYear;
	
	public String toMonth;*/
	
	public Date toYear;
	
    public Date projfromMonth;
	
	/*public String projfromYear;
	
	public String projtoMonth;*/
	
	public Date projtoYear;
	
	

	public EmployResume(){
		
	}
	
	public EmployResume(Long employID, String  compName, String title, String projectRole, String projDescpt, Date fromMonth, String fromYear,
			String toMonth, Date toYear, Date projfromMonth, String projfromYear, String projtoMonth, Date projtoYear,Long candinfo_id){
		this.employID = employID;
		this.compName = compName;
		this.title = title;
		this.projectRole = projectRole;
		this.projDescpt = projDescpt;
		this.projfromMonth = projfromMonth;
		/*this.projfromYear = projfromYear;
		this.projtoMonth = projtoMonth;*/
		this.projtoYear = projtoYear;
		this.fromMonth = fromMonth;
		/*this.fromYear = fromYear;
		this.toMonth = toMonth;*/
		this.toYear = toYear;
		this.candinfo_id = candinfo_id;
		
		
	}

	public Long getEmployID() {
		return employID;
	}

	public void setEmployID(Long employID) {
		this.employID = employID;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProjectRole() {
		return projectRole;
	}

	public void setProjectRole(String projectRole) {
		this.projectRole = projectRole;
	}

	public String getProjDescpt() {
		return projDescpt;
	}

	public void setProjDescpt(String projDescpt) {
		this.projDescpt = projDescpt;
	}

	
	public Date getFromMonth() {
		return fromMonth;
	}

	public void setFromMonth(Date fromMonth) {
		this.fromMonth = fromMonth;
	}

	public Date getToYear() {
		return toYear;
	}

	public void setToYear(Date toYear) {
		this.toYear = toYear;
	}

	public Date getProjfromMonth() {
		return projfromMonth;
	}

	public void setProjfromMonth(Date projfromMonth) {
		this.projfromMonth = projfromMonth;
	}

	public Date getProjtoYear() {
		return projtoYear;
	}

	public void setProjtoYear(Date projtoYear) {
		this.projtoYear = projtoYear;
	}

	public Long getCandinfo_id() {
		return candinfo_id;
	}

	public void setCandinfo_id(Long candinfo_id) {
		this.candinfo_id = candinfo_id;
	}

	
	
}

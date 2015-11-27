package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.Date;


public class EducationResume implements Serializable {
	


/**
	 * 
	 */
	private static final long serialVersionUID = 4908149954125221305L;

	public Long edID;
	
	public String degree;
	
	public String schoolName;
	
	public String major;
	
	public Date compYear;
	
	public Long candinfo_id;

	
		
	public EducationResume(){
		
	}

	
	public EducationResume(Long edID, String  degree, String schoolName, String major, Date compYear, Long candinfo_id){
		this.edID = edID;
		this.degree = degree;
		this.schoolName = schoolName;
		this.major = major;
		this.compYear = compYear;
		this.candinfo_id = candinfo_id;
		
		
	}

	public Long getEdID() {
		return edID;
	}

	public void setEdID(Long edID) {
		this.edID = edID;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	
	public Date getCompYear() {
		return compYear;
	}


	public void setCompYear(Date compYear) {
		this.compYear = compYear;
	}


	public Long getCandinfo_id() {
		return candinfo_id;
	}


	public void setCandinfo_id(Long candinfo_id) {
		this.candinfo_id = candinfo_id;
	}

	
}

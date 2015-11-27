package com.acnc.mm.web.messenger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.acnc.mm.domain.messenger.EducationResume;

public class TestData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6529599075487541799L;
	private static Logger log = Logger.getLogger(ViewMessengerData.class.getName());
	private String degree;
	private String schoolName;
	private String major;
	public Date compYear;
	
	private List<EducationResume> empEdu;
	
	private EducationResume educationResume;
	
	
	public TestData() {
		init();
		
	}
	
	
	private void init() {
		
				
		empEdu = new ArrayList<EducationResume>();
		educationResume = new EducationResume();
	}
	
	
	public String saveEducationResume() {
		
		educationResume.setDegree(degree);
		educationResume.setSchoolName(schoolName);
		educationResume.setMajor(major);
		educationResume.setCompYear(compYear);
		
		empEdu.add(educationResume);
		for(EducationResume er: empEdu){
			
			System.out.println("Inside Education Loop: " + er.getDegree() + " " + er.getSchoolName() + " " + er.getMajor() + " " + er.getCompYear());
		}
		
		clearValues();		
		return null;
		
	}
	
	public void resetValues() {
		
		
		educationResume = new EducationResume();
		log.info ("Successfully reset Records object.");
	}

	public String clearValues() {
		resetValues();
		return "next";
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


	public List<EducationResume> getEmpEdu() {
		return empEdu;
	}


	public void setEmpEdu(List<EducationResume> empEdu) {
		this.empEdu = empEdu;
	}


	public EducationResume getEducationResume() {
		return educationResume;
	}


	public void setEducationResume(EducationResume educationResume) {
		this.educationResume = educationResume;
	}
	
	

}

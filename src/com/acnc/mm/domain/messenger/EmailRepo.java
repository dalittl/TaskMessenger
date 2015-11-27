package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.Date;



public class EmailRepo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4792598295772959113L;

	/**
	 * 
	 */

	
	private Long jobID;
		
	private Long candidateID;
		
	private Date dtCreated;
		
	private Long emailRepoID;
	
	private String candidateFirstName;
	
	private String candidateLastName;
	
	private String candidateEmail;
	
	private String jobTitle;
	
	private String jobDescription;
	
	private String nameuser;
	
	
	public EmailRepo(){
		
	}

	public EmailRepo(Long emailRepoID, Long jobID, Long candidateID, Date dtCreated, String candidateFirstName, 
			String candidateLastName, String candidateEmail, String jobTitle, String jobDescription, String nameuser) {
		this.jobID = jobID;
		this.candidateID = candidateID;
		this.dtCreated = dtCreated;
		this.emailRepoID = emailRepoID;
		this.candidateFirstName = candidateFirstName;
		this.candidateLastName =candidateLastName;
		this.candidateEmail = candidateEmail;
		this.jobTitle = jobTitle;
		this.jobDescription = jobDescription;
		this.nameuser = nameuser;
		
		
	}

	public Long getJobID() {
		return jobID;
	}

	public void setJobID(Long jobID) {
		this.jobID = jobID;
	}

	public Long getCandidateID() {
		return candidateID;
	}

	public void setCandidateID(Long candidateID) {
		this.candidateID = candidateID;
	}

	public Date getDtCreated() {
		return dtCreated;
	}

	public void setDtCreated(Date dtCreated) {
		this.dtCreated = dtCreated;
	}

	public Long getEmailRepoID() {
		return emailRepoID;
	}

	public void setEmailRepoID(Long emailRepoID) {
		this.emailRepoID = emailRepoID;
	}

	public String getCandidateFirstName() {
		return candidateFirstName;
	}

	public void setCandidateFirstName(String candidateFirstName) {
		this.candidateFirstName = candidateFirstName;
	}

	public String getCandidateLastName() {
		return candidateLastName;
	}

	public void setCandidateLastName(String candidateLastName) {
		this.candidateLastName = candidateLastName;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getNameuser() {
		return nameuser;
	}

	public void setNameuser(String nameuser) {
		this.nameuser = nameuser;
	}

	
	
	
	
}
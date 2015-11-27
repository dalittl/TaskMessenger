package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.Date;

public class DeleteCandidate implements Serializable {

private static final long serialVersionUID = 5005794811776514852L;

	
	private Long candidateID;
	
	private Date candidateCreated;
				
	private String candidateFirstName;
	
	private String candidateLastName;
	
	private String candidateEmail;
	
	private String candidateExperience;
	
	protected boolean  selected;
	
	public DeleteCandidate(){
		
	}

	
	public DeleteCandidate(Long candidateID, String candidateFirstName, String candidateLastName, Date candidateCreated, String candidateEmail, String candidateExperience) {
		this.candidateID = candidateID;
		this.candidateFirstName = candidateFirstName;
		this.candidateLastName = candidateLastName;
		this.candidateCreated = candidateCreated;
		this.candidateEmail = candidateEmail;
		this.candidateExperience = candidateExperience;
		
	}

	public Long getCandidateID() {
		return candidateID;
	}

	public void setCandidateID(Long candidateID) {
		this.candidateID = candidateID;
	}

	public Date getCandidateCreated() {
		return candidateCreated;
	}

	public void setCandidateCreated(Date candidateCreated) {
		this.candidateCreated = candidateCreated;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getCandidateExperience() {
		return candidateExperience;
	}

	public void setCandidateExperience(String candidateExperience) {
		this.candidateExperience = candidateExperience;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
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

	
	
}

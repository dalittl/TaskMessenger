package com.acnc.mm.domain.messenger;

import java.io.Serializable;


public class EmailRepo2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 743927877019560601L;
	
	
	private Long businessID;
	
	private Long contractID;
		
	private Long emailRepoID;
	
	private String businessFirstName;
	
	private String businessLastName;
	
	private String businessEmail;
	
	private String contractTitle;
	
	private String contractAgency;
	
	private String contractClass;
	
	private String nameuser;
	
	public EmailRepo2(){
		
	}
	
	
	public EmailRepo2(Long emailRepoID, Long businessID, Long contractID, String businessFirstName, 
			String businessLastName, String businessEmail, String contractTitle, String contractAgency, String contractClass, String nameuser) {
		this.businessID = businessID;
		this.contractID = contractID;
		this.emailRepoID = emailRepoID;
		this.businessFirstName = businessFirstName;
		this.businessLastName =businessLastName;
		this.businessEmail = businessEmail;
		this.contractTitle = contractTitle;
		this.contractAgency = contractAgency;
		this.contractClass = contractClass;
		this.nameuser = nameuser;
		
	}


	public Long getBusinessID() {
		return businessID;
	}


	public void setBusinessID(Long businessID) {
		this.businessID = businessID;
	}


	public Long getContractID() {
		return contractID;
	}


	public void setContractID(Long contractID) {
		this.contractID = contractID;
	}


	public Long getEmailRepoID() {
		return emailRepoID;
	}


	public void setEmailRepoID(Long emailRepoID) {
		this.emailRepoID = emailRepoID;
	}


	public String getBusinessFirstName() {
		return businessFirstName;
	}


	public void setBusinessFirstName(String businessFirstName) {
		this.businessFirstName = businessFirstName;
	}


	public String getBusinessLastName() {
		return businessLastName;
	}


	public void setBusinessLastName(String businessLastName) {
		this.businessLastName = businessLastName;
	}


	public String getBusinessEmail() {
		return businessEmail;
	}


	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}


	public String getContractTitle() {
		return contractTitle;
	}


	public void setContractTitle(String contractTitle) {
		this.contractTitle = contractTitle;
	}


	public String getContractAgency() {
		return contractAgency;
	}


	public void setContractAgency(String contractAgency) {
		this.contractAgency = contractAgency;
	}


	public String getContractClass() {
		return contractClass;
	}


	public void setContractClass(String contractClass) {
		this.contractClass = contractClass;
	}


	public String getNameuser() {
		return nameuser;
	}


	public void setNameuser(String nameuser) {
		this.nameuser = nameuser;
	}
	
	

}

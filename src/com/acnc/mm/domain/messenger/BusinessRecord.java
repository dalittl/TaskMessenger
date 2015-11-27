package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.Date;


public class BusinessRecord implements Serializable{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Long businessID;
	
	public String businessFirstName;
	
	public String businessLastName;
	
	public String businessPhoneNumber;
	
	public String businessEmail;
	
	public String nameuser;
	
	public Long contractID;
	
    public String contractTitle;
	
	public String contractAgency;
	
	public String contractClass;
	
	private Date contractDate;
	
	public String contractStatus;
	
	
	
	public BusinessRecord(){
		
	}


	public BusinessRecord(Long businessID, String businessFirstName, String businessLastName, String businessPhoneNumber, String businessEmail, String nameuser,
			 Long contractID, String contractTitle, String contractAgency, String contractClass, Date contractDate, String contractStatus) {
		this.businessID = businessID;
		this.businessFirstName = businessFirstName;
		this.businessLastName = businessLastName;
		this.businessPhoneNumber = businessPhoneNumber;
		this.businessEmail = businessEmail;
		this.nameuser = nameuser;
		this.contractID = contractID;
		this.contractTitle = contractTitle;
		this.contractAgency = contractAgency;
		this.contractClass = contractClass;
		this.contractDate = contractDate;
		this.contractStatus = contractStatus;
		
		
	}


	public Long getBusinessID() {
		return businessID;
	}


	public void setBusinessID(Long businessID) {
		this.businessID = businessID;
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


	public String getBusinessPhoneNumber() {
		return businessPhoneNumber;
	}


	public void setBusinessPhoneNumber(String businessPhoneNumber) {
		this.businessPhoneNumber = businessPhoneNumber;
	}


	public String getNameuser() {
		return nameuser;
	}


	public void setNameuser(String nameuser) {
		this.nameuser = nameuser;
	}


	public Long getContractID() {
		return contractID;
	}


	public void setContractID(Long contractID) {
		this.contractID = contractID;
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


	public Date getContractDate() {
		return contractDate;
	}


	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}


	public String getContractStatus() {
		return contractStatus;
	}


	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	
	

	
}

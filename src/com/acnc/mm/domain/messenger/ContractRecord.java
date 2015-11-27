package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.Date;




public class ContractRecord implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7970935320054612950L;

	public Long contractID;
	
	public String contractTitle;
	
	public String contractAgency;
	
	public String contractClass;
	
	public String contractStatusUp;
	
	private Date contractDate;
	
	private Date contractRecompete;
	
	public Long buisnessdevID;
	
	public String buisnessdevFirstname;
	
	public String buisnessdevLastname;
	
	public String buisnessdevPhonenumber;
	
	public String buisnessdevEmail;
	
	public Long jobID;
	
	private String Nameuser;
	
	public ContractRecord(){
		
	}
	


	public ContractRecord(Long contractID, String contractTitle, String contractAgency, String contractClass, Date contractDate, String contractStatusUp,
			 String buisnessdevFirstname, String buisnessdevLastname, String buisnessdevEmail,Date contractRecompete, Long buisnessdevID, Long jobID, String Nameuser,String buisnessdevPhonenumber) {

		this.contractID = contractID;
		this.contractTitle = contractTitle;
		this.contractAgency = contractAgency;
		this.contractClass = contractClass;
		this.contractDate = contractDate;
		this.contractStatusUp = contractStatusUp;
		this.contractRecompete = contractRecompete;
		this.buisnessdevID = buisnessdevID;
		this.jobID = jobID;
		this.Nameuser = Nameuser;
		this.buisnessdevEmail = buisnessdevEmail;
		this.buisnessdevFirstname = buisnessdevFirstname;
		this.buisnessdevLastname = buisnessdevLastname;
		this.buisnessdevPhonenumber = buisnessdevPhonenumber;

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
		return contractStatusUp;
	}


	public void setContractStatus(String contractStatusUp) {
		this.contractStatusUp = contractStatusUp;
	}


	public String getNameuser() {
		return Nameuser;
	}


	public void setNameuser(String nameuser) {
		this.Nameuser = nameuser;
	}


	public Date getContractRecompete() {
		return contractRecompete;
	}


	public void setContractRecompete(Date contractRecompete) {
		this.contractRecompete = contractRecompete;
	}


	public Long getBuisnessdevID() {
		return buisnessdevID;
	}


	public void setBuisnessdevID(Long buisnessdevID) {
		this.buisnessdevID = buisnessdevID;
	}


	public Long getJobID() {
		return jobID;
	}


	public void setJobID(Long jobID) {
		this.jobID = jobID;
	}



	public String getBuisnessdevFirstname() {
		return buisnessdevFirstname;
	}



	public void setBuisnessdevFirstname(String buisnessdevFirstname) {
		this.buisnessdevFirstname = buisnessdevFirstname;
	}



	public String getBuisnessdevLastname() {
		return buisnessdevLastname;
	}



	public void setBuisnessdevLastname(String buisnessdevLastname) {
		this.buisnessdevLastname = buisnessdevLastname;
	}

	
	

	public String getBuisnessdevPhonenumber() {
		return buisnessdevPhonenumber;
	}



	public void setBuisnessdevPhonenumber(String buisnessdevPhonenumber) {
		this.buisnessdevPhonenumber = buisnessdevPhonenumber;
	}



	public String getBuisnessdevEmail() {
		return buisnessdevEmail;
	}



	public void setBuisnessdevEmail(String buisnessdevEmail) {
		this.buisnessdevEmail = buisnessdevEmail;
	}

	
	
	
}

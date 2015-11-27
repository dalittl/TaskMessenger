package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.Date;

public class DeleteContract implements Serializable {
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1527093445306535592L;
	
public Long contractID;
	
	public String contractTitle;
	
	public String contractAgency;
	
	public Date contractDate;
	


	public DeleteContract() {
		
	}

	
public DeleteContract(Long contractID, String contractTitle, String contractAgency, Date contractDate ) {
	
	this.contractID = contractID;
	this.contractTitle = contractTitle;
	this.contractAgency = contractAgency;
	this.contractDate = contractDate;
		
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


public Date getContractDate() {
	return contractDate;
}


public void setContractDate(Date contractDate) {
	this.contractDate = contractDate;
}
 
    

}

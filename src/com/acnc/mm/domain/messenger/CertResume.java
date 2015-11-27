package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.Date;

public class CertResume implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2572597420822621934L;
	
	public Long cerID;
	
	public String certName;
	
	public Date certYear;
	
	public Long candinfo_id;
	
	public CertResume(){
		
	}
	
	public CertResume(Long cerID, String certName, Date certYear, Long candinfo_id){
		this.cerID = cerID;
		this.certName = certName;
		this.certYear = certYear;
		this.candinfo_id = candinfo_id;
	}
	
	

	

	public Long getCerID() {
		return cerID;
	}

	public void setCerID(Long cerID) {
		this.cerID = cerID;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public Date getCertYear() {
		return certYear;
	}

	public void setCertYear(Date certYear) {
		this.certYear = certYear;
	}

	public Long getCandinfo_id() {
		return candinfo_id;
	}

	public void setCandinfo_id(Long candinfo_id) {
		this.candinfo_id = candinfo_id;
	}

	

	
	
	

}

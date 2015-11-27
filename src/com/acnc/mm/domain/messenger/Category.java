package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.Date;

public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3880543351212916186L;
	private Long  catID;
	private String catName;
	private String catDescrp;
	private Date dateCreated;
	
	
	public Category(){
		
	}
	
	public Category(Long id, String cN, String cD, Date dc){
		this.catID = id;
		this.catName = cN;
		this.catDescrp = cD;
		this.dateCreated = dc;
	}
	
	

	public Long getCatID() {
		return catID;
	}

	public void setCatID(Long catID) {
		this.catID = catID;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatDescrp() {
		return catDescrp;
	}

	public void setCatDescrp(String catDescrp) {
		this.catDescrp = catDescrp;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	

}

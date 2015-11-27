package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.sql.Date;

public class SaveComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2775214307973496729L;
	
	public Long idComments;
	
	public String commentString;
	
	public Long task_ID;
	
	public java.util.Date createUpdated;
	
	public String idnameuser;
	
	
	
	public SaveComment(Long idComments, String commentString, Long task_ID, java.util.Date createUpdated, String idnameuser){
		
		this.idComments = idComments;
		this.commentString = commentString;
		this.task_ID = task_ID;
		this.createUpdated = createUpdated;
		this.idnameuser = idnameuser;
		
	}


	

	public SaveComment() {
		// TODO Auto-generated constructor stub
	}

 
	
	
	

	public Long getTask_ID() {
		return task_ID;
	}




	public void setTask_ID(Long task_ID) {
		this.task_ID = task_ID;
	}




	public Long getIdComments() {
		return idComments;
	}



	public void setIdComments(Long idComments) {
		this.idComments = idComments;
	}



	public String getCommentString() {
		return commentString;
	}



	public void setCommentString(String commentString) {
		this.commentString = commentString;
	}


	public java.util.Date getCreateUpdated() {
		return createUpdated;
	}



	public void setCreateUpdated(java.util.Date createUpdated2) {
		this.createUpdated = createUpdated2;
	}



	public String getIdnameuser() {
		return idnameuser;
	}



	public void setIdnameuser(String idnameuser) {
		this.idnameuser = idnameuser;
	}
	
	
}

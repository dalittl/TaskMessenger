package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.mysql.jdbc.Blob;

public class TaskRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9218881055464009599L;
	
	public Long task_ID;
	
	public Long idCompleted_Tasks;
	
	public Long jobID;
	
	public Long candidateID;
		
	public Date dtCreated;
		
	public Long emailRepoID;
	
	public String candidateFirstName;
	
	public String candidateLastName;
	
	public String candidateEmail;
	
	public String adminComments;
	
	public String jobTitle;
	
	public String jobDescription;
	
	public String nameuser;
	
	public java.sql.Blob candinfoResume;
	
	public java.sql.Blob jobTemplate;
	
	public Date dateCompleted;
	
	public int enabled;
	
	public Date dueDate;
	
	public Integer urgent;
	
	public String cancelComments;
	
	public Date dateCanceled;
	
	public java.sql.Blob completedTemplate;
	
	private byte[] resFile;
	
	public Long idComments;
	
	public String commentString;
	
	public java.util.Date createUpdated;
	
	public String idnameuser;
	
	
	
	
	
	
	
	
	
	public TaskRecord(){
		
	}

	public TaskRecord(Long task_ID, Long idCompleted_Tasks ,Long emailRepoID, Long jobID, Long candidateID, Date dtCreated, String candidateFirstName, 
			String candidateLastName, String candidateEmail, String adminComments,String jobTitle, String jobDescription, String nameuser, Blob candinfoResume, Blob jobTemplate, Date dateCompleted, int enabled, Date dueDate, Integer urgent, String cancelComments, Date dateCanceled, Blob completedTemplate, byte[] resFile,
			Long idComments, String commentString, java.util.Date createUpdated, String idnameuser) {
		
		this.task_ID = task_ID;
		this.idCompleted_Tasks = idCompleted_Tasks;
		this.jobID = jobID;
		this.candidateID = candidateID;
		this.dtCreated = dtCreated;
		this.emailRepoID = emailRepoID;
		this.candidateFirstName = candidateFirstName;
		this.candidateLastName =candidateLastName;
		this.candidateEmail = candidateEmail;
		this.adminComments = adminComments;
		this.jobTitle = jobTitle;
		this.jobDescription = jobDescription;
		this.nameuser = nameuser;
		this.candinfoResume = candinfoResume;
		this.jobTemplate = jobTemplate;
		this.dateCompleted = dateCompleted;
		this.enabled = enabled;
		this.dueDate = dueDate;
		this.urgent = urgent;
		this.cancelComments = cancelComments;
		this.dateCanceled = dateCanceled;
		this.completedTemplate = completedTemplate;
		this.resFile = resFile;
		this.idComments = idComments;
		this.commentString = commentString;
		this.createUpdated = createUpdated;
		this.idnameuser = idnameuser;
		
	}

	
	
	
	
	public Long gettask_ID() {
		return task_ID;
	}

	public void settask_ID(Long task_ID) {
		this.task_ID = task_ID;
	}

	
	
	public Long getIdCompleted_Tasks() {
		return idCompleted_Tasks;
	}

	public void setIdCompleted_Tasks(Long idCompleted_Tasks) {
		this.idCompleted_Tasks = idCompleted_Tasks;
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

	
	public String getAdminComments() {
		return adminComments;
	}

	public void setAdminComments(String adminComments) {
		this.adminComments = adminComments;
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

	public java.sql.Blob getcandinfoResume() {
		return candinfoResume;
	}

	public void setcandinfoResume(java.sql.Blob blob) {
		this.candinfoResume = blob;
	}

	public java.sql.Blob getJobTemplate() {
		return jobTemplate;
	}

	public void setJobTemplate(java.sql.Blob jobTemplate) {
		this.jobTemplate = jobTemplate;
	}

	public Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date date) {
		this.dueDate = date;
	}

	public Integer getUrgent() {
		return urgent;
	}

	public void setUrgent(Integer urgent) {
		this.urgent = urgent;
	}

	public String getCancelComments() {
		return cancelComments;
	}

	public void setCancelComments(String cancelComments) {
		this.cancelComments = cancelComments;
	}

	public Date getDateCanceled() {
		return dateCanceled;
	}

	public void setDateCanceled(Date dateCanceled) {
		this.dateCanceled = dateCanceled;
	}

	public java.sql.Blob getCompletedTemplate() {
		return completedTemplate;
	}

	public void setCompletedJobTemplate(java.sql.Blob completedTemplate) {
		this.completedTemplate = completedTemplate;
	}

	public byte[] getResFile() {
		return resFile;
	}

	public void setResFile(byte[] resFile) {
		this.resFile = resFile;
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



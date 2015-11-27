package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.Date;



public class CandinfoRepo implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3527028323013704419L;

	public Long candinfoID;

	public Long candinfo_id;	
				
	public String candinfoFirstName;
	
	public String candinfoLastName;
	
	public String candinfoMiddle;
	
	public String candinfoEmail;
	
	public String candinfoNumber;
	
	public String candinfoQuestone;
	
	public String candinfoQuesttwo;
	
	public String candinfoQuesthree; 
	
	public String candinfoQuestfour;
	
	public String candinfoQuestfive;
	
	public String candinfoSkills;
	
	private Date candinfoCreated;
	
	public String candinfoStatus;
	
	public String candinfoClearance;
	
	private Date dtCreated;

	private Long jobID;
	
	private Long candidateID;
			
	private Long emailRepoID;
	
	private String candidateFirstName;
	
	private String candidateLastName;
	
	private String candidateEmail;
	
	private String jobTitle;
	
	private String jobDescription;
	
	private byte[] resFile;

	public CandinfoRepo(){
		
	}

	
	public CandinfoRepo(Long candinfoID,Long candinfo_id, String candinfoFirstName, String candinfoLastName, String candinfoMiddle, String candinfoEmail,String candinfoNumber, String candinfoQuestone,
			String candinfoQuesttwo, String candinfoQuesthree, String candinfoQuestfour, String candinfoQuestfive, String candinfoSkills, Date candinfoCreated, String candinfoStatus, String candinfoClearance,Long emailRepoID, Long jobID, Long candidateID, Date dtCreated, String candidateFirstName, 
			String candidateLastName, String candidateEmail, String jobTitle, String jobDescription, Byte[] resfile, byte[] resFile ) {
		
		this.candinfoID = candinfoID;
		this.candinfo_id = candinfo_id;
		this.candinfoFirstName = candinfoFirstName;
		this.candinfoLastName = candinfoLastName;
		this.candinfoMiddle = candinfoMiddle;
		this.candinfoEmail = candinfoEmail;
		this.candinfoNumber = candinfoNumber;
		this.candinfoQuestone = candinfoQuestone;
		this.candinfoQuesttwo = candinfoQuesttwo;
		this.candinfoQuesthree = candinfoQuesthree;
		this.candinfoQuestfour = candinfoQuestfour;
		this.candinfoQuestfive = candinfoQuestfive;
		this.candinfoSkills = candinfoSkills;
		this.candinfoCreated = candinfoCreated;
		this.candinfoStatus = candinfoStatus;
		this.candinfoClearance = candinfoClearance;
		this.jobID = jobID;
		this.candidateID = candidateID;
		this.dtCreated = dtCreated;
		this.emailRepoID = emailRepoID;
		this.candidateFirstName = candidateFirstName;
		this.candidateLastName =candidateLastName;
		this.candidateEmail = candidateEmail;
		this.jobTitle = jobTitle;
		this.jobDescription = jobDescription;
		this.resFile = resFile;
			
	}
    
	

	public Long getCandinfoID() {
		return candinfoID;
	}


	public void setCandinfoID(Long candinfoID) {
		this.candinfoID = candinfoID;
	}


	public Long getCandinfo_id() {
		return candinfo_id;
	}


	public void setCandinfo_id(Long candinfo_id) {
		this.candinfo_id = candinfo_id;
	}


	public String getCandinfoFirstName() {
		return candinfoFirstName;
	}


	public void setCandinfoFirstName(String candinfoFirstName) {
		this.candinfoFirstName = candinfoFirstName;
	}


	public String getCandinfoLastName() {
		return candinfoLastName;
	}


	public void setCandinfoLastName(String candinfoLastName) {
		this.candinfoLastName = candinfoLastName;
	}


	public String getCandinfoMiddle() {
		return candinfoMiddle;
	}


	public void setCandinfoMiddle(String candinfoMiddle) {
		this.candinfoMiddle = candinfoMiddle;
	}


	public String getCandinfoEmail() {
		return candinfoEmail;
	}


	public void setCandinfoEmail(String candinfoEmail) {
		this.candinfoEmail = candinfoEmail;
	}

	
	
	
	public String getCandinfoNumber() {
		return candinfoNumber;
	}


	public void setCandinfoNumber(String candinfoNumber) {
		this.candinfoNumber = candinfoNumber;
	}


	public String getCandinfoQuestone() {
		return candinfoQuestone;
	}


	public void setCandinfoQuestone(String candinfoQuestone) {
		this.candinfoQuestone = candinfoQuestone;
	}


	public String getCandinfoQuesttwo() {
		return candinfoQuesttwo;
	}


	public void setCandinfoQuesttwo(String candinfoQuesttwo) {
		this.candinfoQuesttwo = candinfoQuesttwo;
	}


	public String getCandinfoQuesthree() {
		return candinfoQuesthree;
	}


	public void setCandinfoQuesthree(String candinfoQuesthree) {
		this.candinfoQuesthree = candinfoQuesthree;
	}


	public String getCandinfoQuestfour() {
		return candinfoQuestfour;
	}


	public void setCandinfoQuestfour(String candinfoQuestfour) {
		this.candinfoQuestfour = candinfoQuestfour;
	}


	public String getCandinfoQuestfive() {
		return candinfoQuestfive;
	}


	public void setCandinfoQuestfive(String candinfoQuestfive) {
		this.candinfoQuestfive = candinfoQuestfive;
	}


	public String getCandinfoSkills() {
		return candinfoSkills;
	}


	public void setCandinfoSkills(String candinfoSkills) {
		this.candinfoSkills = candinfoSkills;
	}


	public Date getCandinfoCreated() {
		return candinfoCreated;
	}


	public void setCandinfoCreated(Date candinfoCreated) {
		this.candinfoCreated = candinfoCreated;
	}

	public String getCandinfoStatus() {
		return candinfoStatus;
	}


	public void setCandinfoStatus(String candinfoStatus) {
		this.candinfoStatus = candinfoStatus;
	}


	public String getCandinfoClearance() {
		return candinfoClearance;
	}


	public void setCandinfoClearance(String candinfoClearance) {
		this.candinfoClearance = candinfoClearance;
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


	public byte[] getResFile() {
		return resFile;
	}


	public void setResFile(byte[] resFile) {
		this.resFile = resFile;
	}

   
	
	
	
	


}

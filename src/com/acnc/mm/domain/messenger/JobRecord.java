package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class JobRecord extends DateChanger implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8293214337324639611L;











	

	/**
	 * 
	 */

	public Long jobID;
	
	private Date jobDate;
	
	private String jobTitle;
	
	private String jobDescription;
	
	private String jobRequest;
	
	private String jobRequesttype;
	
	private String jobSkill;
	
	private String jobPosition;
	
	private String jobClear;
	
	private String jobLocation;
	
	private String jobSkillsMandatory;
	
	private String jobSkillsDesired;
	
	private String jobCertRequired;
	
	private String jobConus;
	
	private String jobOconus;
	
	private long jobbHoursYear;
	
	private String jobSchedComment;
	
	private String jobNonPub;
	
	private String jobMissionCritical;
	
	private String jobWorkNight;
	
	private String jobLocalTravel;
	
	private String jobPager;
	
	private String jobPagerComment;
	
	private String jobWorkHoliday;
	
	private String jobWorkWeekend;
	
	private String jobShiftWork;
	
	private String jobWarZone;
	
	private String jobCoop;
	
	private String jobStatusChange;
	
	private String jobUpdateDate;
	
	private String jobCloseDate;

    public Long contractID;
	
	public String contractTitle;
	
	public String contractAgency;
	
	public String contractClass;
	
	private Date contractDate;
	
	private String jobPriority;
	
    public String nameuser;
    
    private byte[] resFile;
    
    public java.sql.Blob jobTemplate;
	
	
	
	
	
	public JobRecord(){
		
	}

	public JobRecord(Long jobID, Date jobDate, String jobTitle, String jobDescription, String jobRequest, String jobRequesttype,String jobSkill,String jobPosition,	
	String jobClear, String jobLocation,String jobSkillsMandatory, String jobSkillsDesired, String jobCertRequired, String jobConus, String jobOconus,	
	long jobbHoursYear, String jobSchedComment, String jobNonPub ,String jobMissionCritical, String jobWorkNight, String jobLocalTravel, String jobPager, String jobPagerComment,	
	String jobWorkHoliday, String jobWorkWeekend, String jobShiftWork, String jobWarZone, String jobCoop,String jobStatusChange, String jobUpdateDate, String jobCloseDate,Long contractID, String contractTitle, String contractAgency, String contractClass, Date contractDate,String jobPriority, String nameuser,byte[] resFile, java.sql.Blob jobTemplate) {
		this.jobID = jobID;
		this.jobDate = jobDate;
		this.jobTitle = jobTitle;
		this.jobDescription = jobDescription;
		this.jobRequest = jobRequest;
		this.jobRequesttype = jobRequesttype;
		this.jobSkill = jobSkill;
		this.jobPosition = jobPosition;
		this.jobClear = jobClear;
		this.jobLocation = jobLocation;
		this.jobSkillsMandatory = jobSkillsMandatory;
		this.jobSkillsDesired = jobSkillsDesired;
		this.jobCertRequired = jobCertRequired;
		this.jobConus = jobConus;
		this.jobOconus = jobOconus;
		this.jobbHoursYear = jobbHoursYear;
		this.jobSchedComment = jobSchedComment;
		this.jobNonPub = jobNonPub;
		this.jobMissionCritical = jobMissionCritical;
		this.jobWorkNight = jobWorkNight;
		this.jobLocalTravel = jobLocalTravel;
		this.jobPager = jobPager;
		this.jobPagerComment = jobPagerComment;
		this.jobWorkHoliday = jobWorkHoliday;
		this.jobWorkWeekend = jobWorkWeekend;
		this.jobShiftWork = jobShiftWork;
		this.jobWarZone = jobWarZone;
		this.jobCoop = jobCoop;
		this.jobStatusChange = jobStatusChange;
		this.jobUpdateDate = jobUpdateDate;
		this.jobCloseDate = jobCloseDate;
		this.contractID = contractID;
		this.contractTitle = contractTitle;
		this.contractAgency = contractAgency;
		this.contractClass = contractClass;
		this.contractDate = contractDate;
		this.jobPriority = jobPriority;
		this.nameuser = nameuser;
		this.resFile = resFile;
		this.jobTemplate = jobTemplate;
		
	}
	
	

	
	public String getJobNonPub() {
		return jobNonPub;
	}

	public void setJobNonPub(String jobNonPub) {
		this.jobNonPub = jobNonPub;
	}

	public String getJobRequesttype() {
		return jobRequesttype;
	}

	public void setJobRequesttype(String jobRequesttype) {
		this.jobRequesttype = jobRequesttype;
	}

	public Long getJobID() {
		return jobID;
	}

	public void setJobID(Long jobID) {
		this.jobID = jobID;
	}

	public Date getJobDate() {
		return jobDate;
	}

	public void setJobDate(Date jobDate) {
		this.jobDate = jobDate;
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

	public String getJobRequest() {
		return jobRequest;
	}

	public void setJobRequest(String jobRequest) {
		this.jobRequest = jobRequest ;
	}

	public String getJobSkill() {
		return jobSkill;
	}

	public void setJobSkill(String jobSkill) {
		this.jobSkill = jobSkill;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public String getJobClear() {
		return jobClear;
	}

	public void setJobClear(String jobClear) {
		this.jobClear = jobClear;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public String getJobSkillsMandatory() {
		return jobSkillsMandatory;
	}

	public void setJobSkillsMandatory(String jobSkillsMandatory) {
		this.jobSkillsMandatory = jobSkillsMandatory;
	}

	public String getJobSkillsDesired() {
		return jobSkillsDesired;
	}

	public void setJobSkillsDesired(String jobSkillsDesired) {
		this.jobSkillsDesired = jobSkillsDesired;
	}

	public String getJobCertRequired() {
		return jobCertRequired;
	}

	public void setJobCertRequired(String jobCertRequired) {
		this.jobCertRequired = jobCertRequired;
	}

	public String getJobConus() {
		return jobConus;
	}

	public void setJobConus(String jobConus) {
		this.jobConus = jobConus;
	}

	public String getJobOconus() {
		return jobOconus;
	}

	public void setJobOconus(String jobOconus) {
		this.jobOconus = jobOconus;
	}

	

	public long getJobbHoursYear() {
		return jobbHoursYear;
	}

	public void setJobbHoursYear(long jobbHourPerYear) {
		this.jobbHoursYear = jobbHourPerYear;
	}

	public String getJobSchedComment() {
		return jobSchedComment;
	}

	public void setJobSchedComment(String jobSchedComment) {
		this.jobSchedComment = jobSchedComment;
	}

	public String getJobMissionCritical() {
		return jobMissionCritical;
	}

	public void setJobMissionCritical(String jobMissionCritical) {
		this.jobMissionCritical = jobMissionCritical;
	}

	public String getJobWorkNight() {
		return jobWorkNight;
	}

	public void setJobWorkNight(String jobWorkNight) {
		this.jobWorkNight = jobWorkNight;
	}

	public String getJobLocalTravel() {
		return jobLocalTravel;
	}

	public void setJobLocalTravel(String jobLocalTravel) {
		this.jobLocalTravel = jobLocalTravel;
	}

	public String getJobPager() {
		return jobPager;
	}

	public void setJobPager(String jobPager) {
		this.jobPager = jobPager;
	}

	public String getJobPagerComment() {
		return jobPagerComment;
	}

	public void setJobPagerComment(String jobPagerComment) {
		this.jobPagerComment = jobPagerComment;
	}

	public String getJobWorkHoliday() {
		return jobWorkHoliday;
	}

	public void setJobWorkHoliday(String jobWorkHoliday) {
		this.jobWorkHoliday = jobWorkHoliday;
	}

	public String getJobWorkWeekend() {
		return jobWorkWeekend;
	}

	public void setJobWorkWeekend(String jobWorkWeekend) {
		this.jobWorkWeekend = jobWorkWeekend;
	}

	public String getJobShiftWork() {
		return jobShiftWork;
	}

	public void setJobShiftWork(String jobShiftWork) {
		this.jobShiftWork = jobShiftWork;
	}

	public String getJobWarZone() {
		return jobWarZone;
	}

	public void setJobWarZone(String jobWarZone) {
		this.jobWarZone = jobWarZone;
	}

	public String getJobCoop() {
		return jobCoop;
	}

	public void setJobCoop(String jobCoop) {
		this.jobCoop = jobCoop;
	}

	public String getJobStatusChange() {
		return jobStatusChange;
	}

	public void setJobStatusChange(String jobStatusChange) {
		this.jobStatusChange = jobStatusChange;
	}

	public String getJobUpdateDate() {
		return jobUpdateDate;
	}

	public void setJobUpdateDate(String jobUpdateDate) {
		this.jobUpdateDate = jobUpdateDate;
	}

	public String getJobCloseDate() {
		return jobCloseDate;
	}

	public void setJobCloseDate(String jobCloseDate) {
		this.jobCloseDate = jobCloseDate;
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

	public String getJobPriority() {
		return jobPriority;
	}

	public void setJobPriority(String jobPriority) {
		this.jobPriority = jobPriority;
	}

	public String getNameuser() {
		return nameuser;
	}

	public void setNameuser(String nameuser) {
		this.nameuser = nameuser;
	}

	public byte[] getResFile() {
		return resFile;
	}

	public void setResFile(byte[] resFile) {
		this.resFile = resFile;
	}

	public java.sql.Blob getJobTemplate() {
		return jobTemplate;
	}

	public void setJobTemplate(java.sql.Blob jobTemplate) {
		this.jobTemplate = jobTemplate;
	}

	
}

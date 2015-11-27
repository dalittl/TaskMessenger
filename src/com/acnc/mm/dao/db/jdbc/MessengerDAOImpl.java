/**
 * 
 */
package com.acnc.mm.dao.db.jdbc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.acnc.mm.dao.db.MessengerDAO;
import com.acnc.mm.domain.messenger.BusinessRecord;
import com.acnc.mm.domain.messenger.CandidateInfo;
import com.acnc.mm.domain.messenger.Category;
import com.acnc.mm.domain.messenger.CertResume;
import com.acnc.mm.domain.messenger.ContractRecord;
import com.acnc.mm.domain.messenger.ContractsJob;
import com.acnc.mm.domain.messenger.EducationResume;
import com.acnc.mm.domain.messenger.EmailRepo;
import com.acnc.mm.domain.messenger.EmailRepo2;
import com.acnc.mm.domain.messenger.EmployResume;
import com.acnc.mm.domain.messenger.FlatFile;
import com.acnc.mm.domain.messenger.JobRecord;
import com.acnc.mm.domain.messenger.RegisterCandidate;
import com.acnc.mm.domain.messenger.SaveComment;
import com.acnc.mm.domain.messenger.TaskRecord;
import com.acnc.mm.util.MessengerConstants;

/**
 * @author kalittl
 *
 */
public class MessengerDAOImpl extends BaseSpringJdbcDAO implements MessengerDAO, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5616029932808855232L;

	final static private Logger log = Logger.getLogger(MessengerDAOImpl.class.getName());
	
	
	private long candinfoID;

	private long sqlCERTID ;
	
	private long categoryID;
	
	private long flatfileID;
	
	private long userid;

	
	protected Date jDateChooserStart;
	
	
	
	private class JOBParamaterizedRowMapper<T> implements ParameterizedRowMapper<JobRecord>{

		
		public JobRecord mapRow(ResultSet resultSet, int row) throws SQLException {
			JobRecord jobRecord = new JobRecord();
			jobRecord.setJobID(resultSet.getLong("JOB_ID"));
			jobRecord.setJobRequest(resultSet.getString("JOB_REQ"));
			jobRecord.setJobTitle(resultSet.getString("JOB_TITLE"));
			jobRecord.setJobSkill(resultSet.getString("JOB_SKILL"));
			jobRecord.setJobPosition(resultSet.getString("JOB_POSITION"));
			jobRecord.setJobClear(resultSet.getString("JOB_CLEAR"));
			jobRecord.setJobLocation(resultSet.getString("JOB_LOCATE"));
			jobRecord.setJobDescription(resultSet.getString("JOB_DESCRIPTION"));
			jobRecord.setJobSkillsMandatory(resultSet.getString("JOB_SKILLS_MAN"));
			jobRecord.setJobSkillsDesired(resultSet.getString("JOB_SKILLS_DESIRED"));
			jobRecord.setJobCertRequired(resultSet.getString("JOB_CERT_REQUIRE"));
			jobRecord.setJobConus(resultSet.getString("JOB_CONUS"));
			jobRecord.setJobOconus(resultSet.getString("JOB_OCONUS"));
		    jobRecord.setJobbHoursYear(resultSet.getLong("JOB_HOURSPERYEAR"));
			jobRecord.setJobSchedComment(resultSet.getString("JOB_SCHEDULE_COMMENT"));
			jobRecord.setJobMissionCritical(resultSet.getString("JOB_MISSION_CRITICAL"));
			jobRecord.setJobWorkNight(resultSet.getString("JOB_NIGHT_WORK"));
			jobRecord.setJobLocalTravel(resultSet.getString("JOB_LOCAL_TRAVEL"));
			jobRecord.setJobPager(resultSet.getString("JOB_PAGER"));
			jobRecord.setJobPagerComment(resultSet.getString("JOB_PAGER_DUTY_COMMENTS"));
			jobRecord.setJobWorkHoliday(resultSet.getString("JOB_WORK_HOLIDAY"));
			jobRecord.setJobWorkWeekend(resultSet.getString("JOB_WORK_WEEKEND"));
			jobRecord.setJobShiftWork(resultSet.getString("JOB_SHIFTWORK"));
			jobRecord.setJobWarZone(resultSet.getString("JOB_WARZONE"));
			jobRecord.setJobCoop(resultSet.getString("JOB_COOP"));
			jobRecord.setJobStatusChange(resultSet.getString("JOB_STATUS_CHANGE"));
			jobRecord.setJobUpdateDate(resultSet.getString("JOB_UPDATE_DATE"));
			jobRecord.setJobCloseDate(resultSet.getString("JOB_CLOSE_DATE"));
			jobRecord.setJobDate(resultSet.getDate("JOB_DATE"));
			jobRecord.setContractTitle(resultSet.getString("CONTRACT_TITLE"));
			jobRecord.setContractID(resultSet.getLong("Contract_ID"));
			jobRecord.setJobPriority(resultSet.getString("JOB_Priority"));
			jobRecord.setNameuser(resultSet.getString("nameuser"));
			jobRecord.setJobTemplate(resultSet.getBlob("jobTemplate"));
			/*jobRecord.setContractTitle(resultSet.getString("Contract_TITLE"));
			jobRecord.setContractAgency(resultSet.getString("Contract_Agency"));
			jobRecord.setContractClass(resultSet.getString("Contract_Class"));
			jobRecord.setContractDate(resultSet.getDate("Contract_DATE"));*/
			
			return jobRecord;
		}
		
	}
	
	private class ContractParameterizedRowMapper<T> implements ParameterizedRowMapper<ContractRecord> {

		public ContractRecord mapRow(ResultSet resultSet, int row) throws SQLException {
			ContractRecord contractRecord = new ContractRecord();			
			contractRecord.setContractID(resultSet.getLong("CONTRACT_ID"));					
			contractRecord.setContractTitle(resultSet.getString("CONTRACT_TITLE"));
			contractRecord.setContractAgency(resultSet.getString("CONTRACT_AGENCY"));
			contractRecord.setContractClass(resultSet.getString("CONTRACT_CLASS"));
			contractRecord.setContractDate(resultSet.getDate("CONTRACT_DATE"));
			contractRecord.setContractStatus(resultSet.getString("CONTRACT_STATUS"));
			contractRecord.setNameuser(resultSet.getString("NAMEUSER"));
			
		
						
			return contractRecord;

		}
	}	
	
		
	private class CandInfoParameterizedRowMapper<T> implements ParameterizedRowMapper<CandidateInfo> {

		public CandidateInfo mapRow(ResultSet resultSet, int row) throws SQLException {
			CandidateInfo candidateInfo = new CandidateInfo();			
			candidateInfo.setCandinfo_id(resultSet.getLong("candinfo_ID"));	
			candidateInfo.setCandinfoFirstName(resultSet.getString("FIRSTNAME"));			
			candidateInfo.setCandinfoLastName(resultSet.getString("LASTNAME"));			
			candidateInfo.setCandinfoMiddle(resultSet.getString("Middle"));			
			candidateInfo.setCandinfoEmail(resultSet.getString("Email"));
			candidateInfo.setCandinfoNumber(resultSet.getString("phoneNumber"));
			candidateInfo.setCandinfoSkills(resultSet.getString("Skills"));
			candidateInfo.setCandinfoStatus(resultSet.getString("status"));
			candidateInfo.setCandinfoClearance(resultSet.getString("clearance"));
			candidateInfo.setCandinfoResume(resultSet.getBlob("resume"));
		    candidateInfo.setCandinfoCreated(resultSet.getDate("created"));
		    candidateInfo.setNameuser(resultSet.getString("nameuser"));
		    /*candidateInfo.setDtCreated(resultSet.getDate("CREATE_DT"));*/
		  
		    /* candidateInfo.setCandidateID(resultSet.getLong("CANDIDATE_ID"));
		    candidateInfo.setEmailRepoID(resultSet.getLong("EMAILREPO_ID"));
		    candidateInfo.setJobID(resultSet.getLong("JOB_ID"))*/;
		
			return candidateInfo;

		}
	}	
	
	private class EmployInfoParameterizedRowMapper<T> implements ParameterizedRowMapper<EmployResume> {

		public EmployResume mapRow(ResultSet resultSet, int row) throws SQLException {
			EmployResume employResume = new EmployResume();
			
			employResume.setEmployID(resultSet.getLong("empID"));
			employResume.setCompName(resultSet.getString("compName"));
			employResume.setTitle(resultSet.getString("title"));
			employResume.setFromMonth(resultSet.getDate("frommon"));
			employResume.setToYear(resultSet.getDate("tomon"));
			employResume.setProjectRole(resultSet.getString("project"));
			employResume.setProjDescpt(resultSet.getString("projectdescrpt"));
			employResume.setProjfromMonth(resultSet.getDate("projfrom"));
			employResume.setProjtoYear(resultSet.getDate("projto"));
		
			return employResume;

		}
	}	
	
	private class CertInfoParameterizedRowMapper<T> implements ParameterizedRowMapper<CertResume>{
		
		public CertResume mapRow(ResultSet resultSet, int row) throws SQLException
		{
			CertResume certResume = new CertResume();
		
			certResume.setCandinfo_id(resultSet.getLong("candinfo_id"));
			certResume.setCerID(resultSet.getLong("certsid"));
			certResume.setCertName(resultSet.getString("certName"));
			certResume.setCertYear(resultSet.getDate("certYear"));
			
			return certResume;
			}
		}
	
	private class EducateInfoParametrizedRowMapper<T> implements ParameterizedRowMapper<EducationResume>{
		
		public EducationResume mapRow(ResultSet resultSet, int row) throws SQLException{
			EducationResume educationResume = new EducationResume();
			educationResume.setEdID(resultSet.getLong("edID"));
			educationResume.setDegree(resultSet.getString("degree"));
			educationResume.setSchoolName(resultSet.getString("schoolname"));
			educationResume.setMajor(resultSet.getString("major"));
			educationResume.setCompYear(resultSet.getDate("compYear"));
			
			return educationResume;
		}
	}
	
		
	private class EmailParameterizedRowMapper<T> implements ParameterizedRowMapper<EmailRepo> {

		public EmailRepo mapRow(ResultSet resultSet, int row) throws SQLException {
			EmailRepo emailRecord = new EmailRepo();
			emailRecord.setEmailRepoID(resultSet.getLong("EMAILREPO_ID"));
			
			emailRecord.setCandidateID(resultSet.getLong("CANDIDATE_ID"));
			emailRecord.setCandidateFirstName(resultSet.getString("FIRSTNAME"));
			emailRecord.setCandidateLastName(resultSet.getString("LASTNAME"));
			emailRecord.setCandidateEmail(resultSet.getString("EMAIL"));
			
			emailRecord.setJobID(resultSet.getLong("JOB_ID"));
			emailRecord.setJobTitle(resultSet.getString("JOB_TITLE"));
			emailRecord.setJobDescription(resultSet.getString("JOB_DESCRIPTION"));
			
			emailRecord.setDtCreated(resultSet.getDate("CREATE_DT"));			
			emailRecord.setNameuser(resultSet.getString("nameuser"));
			
			return emailRecord;

		}
	}	
	

	private class Email2ParameterizedRowMapper<T> implements ParameterizedRowMapper<EmailRepo2> {

		public EmailRepo2 mapRow(ResultSet resultSet, int row) throws SQLException {
			EmailRepo2 emailRecord2 = new EmailRepo2();
			emailRecord2.setEmailRepoID(resultSet.getLong("EMAILREPO_ID"));
			
			emailRecord2.setBusinessID(resultSet.getLong("BUSINESSDEV_ID"));
			emailRecord2.setBusinessFirstName(resultSet.getString("BUSINESSDEV_FIRSTNAME"));
			emailRecord2.setBusinessLastName(resultSet.getString("BUSINESSDEV_LASTNAME"));
			emailRecord2.setBusinessEmail(resultSet.getString("BUSINESSDEV_EMAIL"));
			
			emailRecord2.setContractID(resultSet.getLong("Contract_ID"));
			emailRecord2.setContractTitle(resultSet.getString("Contract_TITLE"));
			emailRecord2.setContractAgency(resultSet.getString("CONTRACT_AGENCY"));
			emailRecord2.setContractClass(resultSet.getString("Contract_Class"));
			emailRecord2.setNameuser(resultSet.getString("nameuser"));
			
			
			return emailRecord2;

		}
	}	
	
	   	    
	    private class BusinessParameterizedRowMapper<T> implements ParameterizedRowMapper<BusinessRecord> {  
	    
	    public BusinessRecord mapRow(ResultSet resultSet, int row) throws SQLException{
	    	BusinessRecord businessRecord = new BusinessRecord();
	    	businessRecord.setBusinessID(resultSet.getLong("BUSINESSDEV_ID"));					
	    	businessRecord.setBusinessFirstName(resultSet.getString("BUSINESSDEV_FIRSTNAME"));
	    	businessRecord.setBusinessLastName(resultSet.getString("BUSINESSDEV_LASTNAME"));
	    	businessRecord.setBusinessPhoneNumber(resultSet.getString("BUSINESSDEV_PHONENUMBER"));
	    	businessRecord.setBusinessEmail(resultSet.getString("BUSINESSDEV_EMAIL"));
	    	businessRecord.setNameuser(resultSet.getString("nameuser"));
			

			return businessRecord;

	    }
 
	 } 
	    
	    
	   private class TaskParameterizedRowMapper<T>  implements ParameterizedRowMapper<TaskRecord>{
		   
		   public TaskRecord mapRow(ResultSet resultSet, int row) throws SQLException{
			TaskRecord taskRecord = new TaskRecord();
			taskRecord.settask_ID(resultSet.getLong("task_ID"));
			taskRecord.setCandidateFirstName(resultSet.getString("firstname"));
			taskRecord.setCandidateLastName(resultSet.getString("lastname"));
			taskRecord.setJobTitle(resultSet.getString("jobTitle"));
			taskRecord.setJobDescription(resultSet.getString("jobDescription"));
			taskRecord.setAdminComments(resultSet.getString("adminComments"));
			taskRecord.setNameuser(resultSet.getString("nameuser"));
			taskRecord.setcandinfoResume(resultSet.getBlob("candinfoResume"));
			taskRecord.setDateCompleted(resultSet.getDate("dateCompleted"));
			taskRecord.setDueDate(resultSet.getDate("dueDate"));
			taskRecord.setUrgent(resultSet.getInt("urgent"));
			taskRecord.setCancelComments(resultSet.getString("cancelComments"));
			taskRecord.setDateCanceled(resultSet.getDate("dateCanceled"));
			   
			   
			   
		    return taskRecord;
			   
		   }
		   
	   }
	    
	   private class CommentParameterizedRowMapper<T> implements ParameterizedRowMapper<SaveComment>{

		
		public SaveComment mapRow(ResultSet resultSet, int row) throws SQLException {
			SaveComment saveComment = new SaveComment();
			saveComment.setIdComments(resultSet.getLong("idComments"));
			saveComment.setCommentString(resultSet.getString("commentString"));
			saveComment.setTask_ID(resultSet.getLong("task_ID"));
			saveComment.setCreateUpdated(resultSet.getDate("createUpdated"));
			saveComment.setIdnameuser(resultSet.getString("idnameuser"));
			
			return saveComment;
		}
		   
	   
		   
		   
	  }
	    
	
	    private class ContractsJobsParameterizedRowMapper<T> implements ParameterizedRowMapper<ContractsJob>{
			
			public ContractsJob mapRow(ResultSet resultSet, int row) throws SQLException {
				
				ContractsJob contractsJob = new ContractsJob();
				contractsJob.setJobID(resultSet.getLong("JOB_ID"));
				contractsJob.setJobRequest(resultSet.getString("JOB_REQ"));
				contractsJob.setJobTitle(resultSet.getString("JOB_TITLE"));
				contractsJob.setJobSkill(resultSet.getString("JOB_SKILL"));
				contractsJob.setJobPosition(resultSet.getString("JOB_POSITION"));
				contractsJob.setJobClear(resultSet.getString("JOB_CLEAR"));
				contractsJob.setJobLocation(resultSet.getString("JOB_LOCATE"));
				contractsJob.setJobDescription(resultSet.getString("JOB_DESCRIPTION"));
				contractsJob.setJobSkillsMandatory(resultSet.getString("JOB_SKILLS_MAN"));
				contractsJob.setJobSkillsDesired(resultSet.getString("JOB_SKILLS_DESIRED"));
				contractsJob.setJobCertRequired(resultSet.getString("JOB_CERT_REQUIRE"));
				contractsJob.setJobConus(resultSet.getString("JOB_CONUS"));
				contractsJob.setJobOconus(resultSet.getString("JOB_OCONUS"));
				contractsJob.setJobbHoursYear(resultSet.getLong("JOB_HOURSPERYEAR"));
				contractsJob.setJobSchedComment(resultSet.getString("JOB_SCHEDULE_COMMENT"));
				contractsJob.setJobMissionCritical(resultSet.getString("JOB_MISSION_CRITICAL"));
				contractsJob.setJobWorkNight(resultSet.getString("JOB_NIGHT_WORK"));
				contractsJob.setJobLocalTravel(resultSet.getString("JOB_LOCAL_TRAVEL"));
				contractsJob.setJobPager(resultSet.getString("JOB_PAGER"));
				contractsJob.setJobPagerComment(resultSet.getString("JOB_PAGER_DUTY_COMMENTS"));
				contractsJob.setJobWorkHoliday(resultSet.getString("JOB_WORK_HOLIDAY"));
				contractsJob.setJobWorkWeekend(resultSet.getString("JOB_WORK_WEEKEND"));
				contractsJob.setJobShiftWork(resultSet.getString("JOB_SHIFTWORK"));
				contractsJob.setJobWarZone(resultSet.getString("JOB_WARZONE"));
				contractsJob.setJobCoop(resultSet.getString("JOB_COOP"));
				contractsJob.setJobStatusChange(resultSet.getString("JOB_STATUS_CHANGE"));
				contractsJob.setJobUpdateDate(resultSet.getString("JOB_UPDATE_DATE"));
				contractsJob.setJobCloseDate(resultSet.getString("JOB_CLOSE_DATE"));
				contractsJob.setJobDate(resultSet.getDate("JOB_DATE"));
				
				contractsJob.setContractID(resultSet.getLong("Contract_ID"));
				contractsJob.setContractTitle(resultSet.getString("Contract_TITLE"));
				contractsJob.setContractAgency(resultSet.getString("CONTRACT_AGENCY"));
				contractsJob.setContractClass(resultSet.getString("Contract_Class"));
				contractsJob.setContractDate(resultSet.getDate("contract_Date"));
				/*contractsJob.setBusinessID(resultSet.getLong("BUISNESSDEV_ID"));
				contractsJob.setBusinessFirstName(resultSet.getString("businessdev_email"));
				contractsJob.setBusinessLastName(resultSet.getString("businessLastName"));
	*/			
				contractsJob.setBusinessID(resultSet.getLong("BUSINESSDEV_ID"));					
				contractsJob.setBusinessFirstName(resultSet.getString("BUSINESSDEV_FIRSTNAME"));
				contractsJob.setBusinessLastName(resultSet.getString("BUSINESSDEV_LASTNAME"));
				contractsJob.setBusinessPhoneNumber(resultSet.getString("BUSINESSDEV_PHONENUMBER"));
				contractsJob.setBusinessEmail(resultSet.getString("BUSINESSDEV_EMAIL"));
				return contractsJob;
			}
			  
		  }
	    
	    private class ContractJobsParameterizedRowMapper<T> implements ParameterizedRowMapper<ContractsJob>{
			
	 			public ContractsJob mapRow(ResultSet resultSet, int row) throws SQLException {
	 				
	 				ContractsJob contractsJob = new ContractsJob();
//	 				contractsJob.setJobID(resultSet.getLong("JOB_ID"));
//	 				contractsJob.setJobRequest(resultSet.getString("JOB_REQ"));
//	 				contractsJob.setJobTitle(resultSet.getString("JOB_TITLE"));
//	 				contractsJob.setJobSkill(resultSet.getString("JOB_SKILL"));
//	 				contractsJob.setJobPosition(resultSet.getString("JOB_POSITION"));
//	 				contractsJob.setJobClear(resultSet.getString("JOB_CLEAR"));
//	 				contractsJob.setJobLocation(resultSet.getString("JOB_LOCATE"));
//	 				contractsJob.setJobDescription(resultSet.getString("JOB_DESCRIPTION"));
//	 				contractsJob.setJobSkillsMandatory(resultSet.getString("JOB_SKILLS_MAN"));
//	 				contractsJob.setJobSkillsDesired(resultSet.getString("JOB_SKILLS_DESIRED"));
//	 				contractsJob.setJobCertRequired(resultSet.getString("JOB_CERT_REQUIRE"));
//	 				contractsJob.setJobConus(resultSet.getString("JOB_CONUS"));
//	 				contractsJob.setJobOconus(resultSet.getString("JOB_OCONUS"));
//	 				contractsJob.setJobbHoursYear(resultSet.getLong("JOB_HOURSPERYEAR"));
//	 				contractsJob.setJobSchedComment(resultSet.getString("JOB_SCHEDULE_COMMENT"));
//	 				contractsJob.setJobMissionCritical(resultSet.getString("JOB_MISSION_CRITICAL"));
//	 				contractsJob.setJobWorkNight(resultSet.getString("JOB_NIGHT_WORK"));
//	 				contractsJob.setJobLocalTravel(resultSet.getString("JOB_LOCAL_TRAVEL"));
//	 				contractsJob.setJobPager(resultSet.getString("JOB_PAGER"));
//	 				contractsJob.setJobPagerComment(resultSet.getString("JOB_PAGER_DUTY_COMMENTS"));
//	 				contractsJob.setJobWorkHoliday(resultSet.getString("JOB_WORK_HOLIDAY"));
//	 				contractsJob.setJobWorkWeekend(resultSet.getString("JOB_WORK_WEEKEND"));
//	 				contractsJob.setJobShiftWork(resultSet.getString("JOB_SHIFTWORK"));
//	 				contractsJob.setJobWarZone(resultSet.getString("JOB_WARZONE"));
//	 				contractsJob.setJobCoop(resultSet.getString("JOB_COOP"));
//	 				contractsJob.setJobStatusChange(resultSet.getString("JOB_STATUS_CHANGE"));
//	 				contractsJob.setJobUpdateDate(resultSet.getString("JOB_UPDATE_DATE"));
//	 				contractsJob.setJobCloseDate(resultSet.getString("JOB_CLOSE_DATE"));
//	 				contractsJob.setJobDate(resultSet.getDate("JOB_DATE"));
	 				
	 				contractsJob.setContractID(resultSet.getLong("Contract_ID"));
	 				contractsJob.setContractTitle(resultSet.getString("Contract_TITLE"));
	 				contractsJob.setContractAgency(resultSet.getString("CONTRACT_AGENCY"));
	 				contractsJob.setContractClass(resultSet.getString("Contract_Class"));
	 				contractsJob.setContractDate(resultSet.getDate("contract_Date"));
	 				/*contractsJob.setBusinessID(resultSet.getLong("BUISNESSDEV_ID"));
	 				contractsJob.setBusinessFirstName(resultSet.getString("businessdev_email"));
	 				contractsJob.setBusinessLastName(resultSet.getString("businessLastName"));
	 	*/			
//	 				contractsJob.setBusinessID(resultSet.getLong("BUSINESSDEV_ID"));					
//	 				contractsJob.setBusinessFirstName(resultSet.getString("BUSINESSDEV_FIRSTNAME"));
//	 				contractsJob.setBusinessLastName(resultSet.getString("BUSINESSDEV_LASTNAME"));
//	 				contractsJob.setBusinessPhoneNumber(resultSet.getString("BUSINESSDEV_PHONENUMBER"));
//	 				contractsJob.setBusinessEmail(resultSet.getString("BUSINESSDEV_EMAIL"));
	 				return contractsJob;
	 			}
	 			  
	 		  }
	    
	    private class ContractssJobsParameterizedRowMapper<T> implements ParameterizedRowMapper<ContractsJob>{
			
 			public ContractsJob mapRow(ResultSet resultSet, int row) throws SQLException {
 				
 				ContractsJob contractsJob = new ContractsJob();
 				contractsJob.setJobID(resultSet.getLong("JOB_ID"));
 				contractsJob.setJobRequest(resultSet.getString("JOB_REQ"));
 				contractsJob.setJobTitle(resultSet.getString("JOB_TITLE"));
 				contractsJob.setJobSkill(resultSet.getString("JOB_SKILL"));
 				contractsJob.setJobPosition(resultSet.getString("JOB_POSITION"));
 				contractsJob.setJobClear(resultSet.getString("JOB_CLEAR"));
 				contractsJob.setJobLocation(resultSet.getString("JOB_LOCATE"));
 				contractsJob.setJobDescription(resultSet.getString("JOB_DESCRIPTION"));
 				contractsJob.setJobSkillsMandatory(resultSet.getString("JOB_SKILLS_MAN"));
				contractsJob.setJobSkillsDesired(resultSet.getString("JOB_SKILLS_DESIRED"));
 				contractsJob.setJobCertRequired(resultSet.getString("JOB_CERT_REQUIRE"));
 				contractsJob.setJobConus(resultSet.getString("JOB_CONUS"));
 				contractsJob.setJobOconus(resultSet.getString("JOB_OCONUS"));
 				contractsJob.setJobbHoursYear(resultSet.getLong("JOB_HOURSPERYEAR"));
 				contractsJob.setJobSchedComment(resultSet.getString("JOB_SCHEDULE_COMMENT"));
 				contractsJob.setJobMissionCritical(resultSet.getString("JOB_MISSION_CRITICAL"));
 				contractsJob.setJobWorkNight(resultSet.getString("JOB_NIGHT_WORK"));
 				contractsJob.setJobLocalTravel(resultSet.getString("JOB_LOCAL_TRAVEL"));
 				contractsJob.setJobPager(resultSet.getString("JOB_PAGER"));
 				contractsJob.setJobPagerComment(resultSet.getString("JOB_PAGER_DUTY_COMMENTS"));
 				contractsJob.setJobWorkHoliday(resultSet.getString("JOB_WORK_HOLIDAY"));
 				contractsJob.setJobWorkWeekend(resultSet.getString("JOB_WORK_WEEKEND"));
 				contractsJob.setJobShiftWork(resultSet.getString("JOB_SHIFTWORK"));
 				contractsJob.setJobWarZone(resultSet.getString("JOB_WARZONE"));
 				contractsJob.setJobCoop(resultSet.getString("JOB_COOP"));
 				contractsJob.setJobStatusChange(resultSet.getString("JOB_STATUS_CHANGE"));
 				contractsJob.setJobUpdateDate(resultSet.getString("JOB_UPDATE_DATE"));
 				contractsJob.setJobCloseDate(resultSet.getString("JOB_CLOSE_DATE"));
 				contractsJob.setJobDate(resultSet.getDate("JOB_DATE"));
 				
 				contractsJob.setContractID(resultSet.getLong("Contract_ID"));
 				contractsJob.setContractTitle(resultSet.getString("Contract_TITLE"));
 				contractsJob.setContractAgency(resultSet.getString("CONTRACT_AGENCY"));
 				contractsJob.setContractClass(resultSet.getString("Contract_Class"));
 				contractsJob.setContractDate(resultSet.getDate("contract_Date"));
 				/*contractsJob.setBusinessID(resultSet.getLong("BUISNESSDEV_ID"));
 				contractsJob.setBusinessFirstName(resultSet.getString("businessdev_email"));
 				contractsJob.setBusinessLastName(resultSet.getString("businessLastName"));
 	*/			
// 				contractsJob.setBusinessID(resultSet.getLong("BUSINESSDEV_ID"));					
// 				contractsJob.setBusinessFirstName(resultSet.getString("BUSINESSDEV_FIRSTNAME"));
// 				contractsJob.setBusinessLastName(resultSet.getString("BUSINESSDEV_LASTNAME"));
// 				contractsJob.setBusinessPhoneNumber(resultSet.getString("BUSINESSDEV_PHONENUMBER"));
// 				contractsJob.setBusinessEmail(resultSet.getString("BUSINESSDEV_EMAIL"));
 				return contractsJob;
 			}
 			  
 		  }
	    
		    
	    
	    
	    
	    
	//Change From JOBS to job
	final static private String SQL_GET_ALL_EMAIL_REPO =
			" SELECT e.EMAILREPO_ID, e.CANDIDATE_ID, e.JOB_ID, e.CREATE_DT, e.nameuser, " +
			" c.FIRSTNAME, c.LASTNAME, c.EMAIL, " +
			" j.JOB_TITLE, j.JOB_DESCRIPTION" +
			" FROM " +
			MessengerConstants.MM_SCHEMA_NAME + ".emailRepo e " +
					" JOIN " + MessengerConstants.MM_SCHEMA_NAME + ".candinfo c " +
					" ON e.CANDIDATE_ID = c.CANDINFO_ID " + 
					" JOIN " + MessengerConstants.MM_SCHEMA_NAME + ".jobs j " +
					" ON e.JOB_ID = j.JOB_ID";
	
	final static private String SQL_GET_CONTRACTS_JOBS = 
			" Select c.CONTRACT_ID, c.CONTRACT_TITLE, c.CONTRACT_AGENCY, c.CONTRACT_CLASS, c.CONTRACT_DATE, c.nameuser " +
	        "j.JOB_ID, j.JOB_REQ, j.JOB_TITLE, j.JOB_SKILL, j.JOB_POSITION, j.JOB_CLEAR, j.JOB_LOCATE, j.JOB_DESCRIPTION, j.JOB_SKILLS_MAN, j.JOB_SKILLS_DESIRED, j.JOB_CERT_REQUIRE, j.JOB_CONUS, j.JOB_OCONUS, j.JOB_HOURSPERYEAR, j.JOB_SCHEDULE_COMMENT" +
	        "j.JOB_MISSION_CRITICAL, j.JOB_NIGHT_WORK, j.JOB_PAGER, j.JOB_PAGER_DUTY_COMMENTS, j.JOB_WORK_HOLIDAY, j.JOB_WORK_WEEKEND, j.JOB_SHIFTWORK, j.JOB_WARZONE, j.JOB_COOP, j.JOB_STATUS_CHANGE, j.JOB_UPDATE_DATE, j.JOB_CLOSE_DATE, j.JOB_DATE, j.CONTRACT_TITLE, j.nameuser"  +
	        "from" +
	        MessengerConstants.MM_SCHEMA_NAME + ".contractsJob cj" +
	        "JOIN" + MessengerConstants.MM_SCHEMA_NAME + ".contracts c" +
	        "ON cj.CONTRACT_ID = c.CONTRACT_ID" +
	        "JOIN" + MessengerConstants.MM_SCHEMA_NAME + ".jobs j" +
	        "ON cj.JOB_ID = j.JOB_ID";
	        
	        
					
	final static private String SQL_GET_ALL_EMAIL_REPO2 =
			" SELECT e.EMAILREPO_ID, e.CONTRACT_ID, e.BUSINESSDEV_ID, " +
			" b.BUSINESSDEV_FIRSTNAME, b.BUSINESSDEV_LASTNAME, b.BUSINESSDEV_EMAIL, b.nameuser, " +
			" c.CONTRACT_TITLE, c.CONTRACT_AGENCY, c.CONTRACT_CLASS, c.nameuser " +
			" FROM " +
			MessengerConstants.MM_SCHEMA_NAME + ".emailRepo2 e " +
					" JOIN " + MessengerConstants.MM_SCHEMA_NAME + ".contracts c " +
					" ON e.CONTRACT_ID = c.CONTRACT_ID " + 
					" JOIN " + MessengerConstants.MM_SCHEMA_NAME + ".businessdev b " +
					" ON e.BUSINESSDEV_ID = b.BUSINESSDEV_ID";
	
	final static private String SQL_GET_ALL_JOBS_PRIORITY =
			
			"Select distinct c.contract_Title, c.contract_agency, c.contract_id, c.contract_class, c.contract_date from " + MessengerConstants.MM_SCHEMA_NAME  + ".contracts c ," + MessengerConstants.MM_SCHEMA_NAME  + ".jobs j where c.contract_id = j.contract_id and j.job_priority =1";
			
//			" SELECT j.JOB_ID, j.JOB_REQ, j.JOB_TITLE, j.JOB_SKILL, j.JOB_POSITION, j.JOB_CLEAR, j.JOB_LOCATE, j.JOB_DESCRIPTION, j.JOB_SKILLS_MAN, j.JOB_SKILLS_DESIRED, j.JOB_CERT_REQUIRE, j.JOB_CONUS, j.JOB_OCONUS,j.JOB_HOURSPERYEAR,j.JOB_SCHEDULE_COMMENT, j.JOB_MISSION_CRITICAL," +
//			" j.JOB_NIGHT_WORK, j.contract_id, j.JOB_LOCAL_TRAVEL, j.JOB_PAGER, j.JOB_PAGER_DUTY_COMMENTS, j.JOB_WORK_HOLIDAY,j.JOB_WORK_WEEKEND,j.JOB_SHIFTWORK, j.JOB_WARZONE, j.JOB_COOP,j.JOB_STATUS_CHANGE, j.JOB_UPDATE_DATE,j.JOB_CLOSE_DATE, j.JOB_DATE, j.nameuser, c.CONTRACT_TITLE, c.CONTRACT_ID, " +
//			" c.CONTRACT_AGENCY,c.contract_DATE, c.CONTRACT_CLASS, c.nameuser, j.JOB_PRIORITY FROM " +
//			MessengerConstants.MM_SCHEMA_NAME + ".jobs j " +
//			" Join " + MessengerConstants.MM_SCHEMA_NAME + " .contracts c " +
//     		" on j.contract_ID = c.contract_ID WHERE j.job_priority=1 " ;
//			" Join " + MessengerConstants.MM_SCHEMA_NAME + " .businessdev b " +
//			" on j.contract_id = b.contract_id WHERE j.job_priority=1 order by c.contract_id";
//	, b.businessdev_id, b.businessdev_firstname, b.businessdev_lastname,b.BUSINESSDEV_PHONENUMBER, b.BUSINESSDEV_EMAIL, b.nameuser
	
    final static private String SQL_GET_ALL_JOBS = 
			 " Select distinct c.CONTRACT_ID, c.CONTRACT_TITLE, c.CONTRACT_AGENCY, c.CONTRACT_CLASS, c.CONTRACT_DATE, c.nameuser, " +
				        " j.JOB_ID, j.JOB_REQ, j.JOB_TITLE, j.JOB_SKILL, j.JOB_POSITION, j.JOB_CLEAR, j.JOB_LOCATE, j.JOB_DESCRIPTION, j.JOB_SKILLS_MAN, j.JOB_SKILLS_DESIRED, j.JOB_CERT_REQUIRE, j.JOB_CONUS, j.JOB_OCONUS, j.JOB_HOURSPERYEAR, j.JOB_SCHEDULE_COMMENT," +
				        " j.JOB_MISSION_CRITICAL, j.JOB_NIGHT_WORK,j.JOB_LOCAL_TRAVEL, j.JOB_PAGER, j.JOB_PAGER_DUTY_COMMENTS, j.JOB_WORK_HOLIDAY, j.JOB_WORK_WEEKEND, j.JOB_SHIFTWORK, j.JOB_WARZONE, j.JOB_COOP, j.JOB_STATUS_CHANGE, j.JOB_UPDATE_DATE, j.JOB_CLOSE_DATE, j.JOB_DATE, j.CONTRACT_TITLE, j.CONTRACT_ID, j.JOB_PRIORITY, j.nameuser, j.jobTemplate " +
				        " from " +
				        MessengerConstants.MM_SCHEMA_NAME + " .contracts c " +
				        " JOIN " + MessengerConstants.MM_SCHEMA_NAME + " .jobs j GROUP BY j.JOB_ID ";
    
    
    final static private String SQL_GET_ALL_JOBS_NEW = 
			 " Select c.CONTRACT_ID, c.CONTRACT_TITLE, c.CONTRACT_AGENCY, c.CONTRACT_CLASS, c.CONTRACT_DATE, c.nameuser, " +
				        " j.JOB_ID, j.JOB_REQ, j.JOB_TITLE, j.JOB_SKILL, j.JOB_POSITION, j.JOB_CLEAR, j.JOB_LOCATE, j.JOB_DESCRIPTION, j.JOB_SKILLS_MAN, j.JOB_SKILLS_DESIRED, j.JOB_CERT_REQUIRE, j.JOB_CONUS, j.JOB_OCONUS, j.JOB_HOURSPERYEAR, j.JOB_SCHEDULE_COMMENT," +
				        " j.JOB_MISSION_CRITICAL, j.JOB_NIGHT_WORK,j.JOB_LOCAL_TRAVEL, j.JOB_PAGER, j.JOB_PAGER_DUTY_COMMENTS, j.JOB_WORK_HOLIDAY, j.JOB_WORK_WEEKEND, j.JOB_SHIFTWORK, j.JOB_WARZONE, j.JOB_COOP, j.JOB_STATUS_CHANGE, j.JOB_UPDATE_DATE, j.JOB_CLOSE_DATE, j.JOB_DATE, j.CONTRACT_TITLE, j.CONTRACT_ID, j.JOB_PRIORITY, j.nameuser, j.jobTemplate " +
				        " from " +
				        MessengerConstants.MM_SCHEMA_NAME + " .contracts c " +
				        " JOIN " + MessengerConstants.MM_SCHEMA_NAME + " .jobs j " +
				        " ON c.CONTRACT_ID = j.CONTRACT_ID WHERE JOB_SKILL = 'TELL US'";
	 
				      
	
	 final static private String SQL_GET_ALL_BUISNESS_CONTRACT =
			 "SELECT b.CONTRACT_ID, b.BUSINESSDEV_ID, b.BUSINESSDEV_FIRSTNAME, b.BUSINESSDEV_LASTNAME, "
						+ "b.BUSINESSDEV_EMAIL, b.BUSINESSDEV_PHONENUMBER, c.CONTRACT_ID, c.CONTRACT_AGENCY, c.CONTRACT_DATE, c.CONTRACT_TITLE, c.CONTRACT_CLASS, c.CONTRACT_STATUS, c.NAMEUSER FROM " + 
				        MessengerConstants.MM_SCHEMA_NAME + ".businessdev b " +
				        " join " + MessengerConstants.MM_SCHEMA_NAME + ".contracts c " +
				        " where b.CONTRACT_ID = c.CONTRACT_ID and c.contract_status = 'Potential Contract'";
			 
			 
			 
//			 " Select  c.CONTRACT_ID, c.CONTRACT_TITLE, c.CONTRACT_AGENCY, c.CONTRACT_CLASS, c.CONTRACT_DATE, c.Contract_Status, c.nameuser " +
//				        " from " +
//				        MessengerConstants.MM_SCHEMA_NAME + " .contracts c WHERE c.contract_status = 'Potential Contract'"; 
	
/*	 Select   b.businessdev_id,b.businessdev_firstname, b.businessdev_lastname, b.businessdev_phonenumber, b.businessdev_email, +
     c.CONTRACT_ID, c.CONTRACT_TITLE, c.CONTRACT_AGENCY, c.CONTRACT_CLASS, c.CONTRACT_DATE, c.BUISNESSDEV_ID +
     j.JOB_ID, j.JOB_REQ, j.JOB_TITLE, j.JOB_SKILL
	 from  massEmail.businessdev b 
     JOIN massEmail.contracts c ON b.contract_ID = c.contract_id 
     join massEmail.jobs j on c.job_id = j.job_id and j.job_priority = 1;*/
	 
		
	final static private String SQL_GET_ALL_CONTRACTS =
			" SELECT CONTRACT_ID, CONTRACT_TITLE, CONTRACT_AGENCY,CONTRACT_CLASS, CONTRACT_DATE, CONTRACT_STATUS, nameuser FROM " +
			MessengerConstants.MM_SCHEMA_NAME + ".contracts ";
	
	final static private String SQL_GET_ALL_CONTRACTS_BUSINESS =
			"SELECT b.CONTRACT_ID, b.BUSINESSDEV_ID, b.BUSINESSDEV_FIRSTNAME, b.BUSINESSDEV_LASTNAME, "
					+ " b.BUSINESSDEV_EMAIL,b.BUSINESSDEV_PHONENUMBER, c.CONTRACT_ID, c.CONTRACT_AGENCY, c.CONTRACT_DATE, c.CONTRACT_TITLE, c.CONTRACT_CLASS, c.CONTRACT_STATUS, c.NAMEUSER FROM " + 
			        MessengerConstants.MM_SCHEMA_NAME + ".businessdev b " +
			        " join " + MessengerConstants.MM_SCHEMA_NAME + ".contracts c " +
			        " where b.CONTRACT_ID = c.CONTRACT_ID ";
	
	
	
	
	
	final static private String SQL_GET_MAX_ID_CANDINFO =
			"SELECT candinfo_id, firstname, lastname, middle, email, phoneNumber, skills, status, clearance, resume, created, nameuser FROM " +
	        MessengerConstants.MM_SCHEMA_NAME + " .candinfo where enabled = 0 and status != 'Have Not Contacted'";
	
	final static private String SQL_GET_MAX_ID_CANDINFO_QUERY =
			"SELECT candinfo_id, firstname, lastname, middle, email, phoneNumber, skills, status, clearance, resume,created, nameuser FROM " +
	        MessengerConstants.MM_SCHEMA_NAME + " .candinfo where enabled = 0 ";

	
	/*final static private String SQL_GET_MAX_ID_CANDINFO_EMAIL =
			"SELECT c.candinfo_id, c.firstname, c.lastname, c.middle, c.phoneNumber, c.email, c.skills, c.created, c.status, c.resume, c.clearance, c.clearDate, c.nameuser, "
			+ " e.EMAILREPO_ID, e.JOB_ID, e.CANDIDATE_ID, e.CREATE_DT, e.nameuser "+ " FROM " +
	        MessengerConstants.MM_SCHEMA_NAME + ".candinfo c" +
					" JOIN " + MessengerConstants.MM_SCHEMA_NAME + ".EMAILREPO e " +
					" ON c.candinfo_id = e.candidate_id GROUP BY e.CANDIDATE_ID";*/
	
	
	final static private String SQL_GET_MAX_ID_CANDINFO_EMAIL_NEW =
			"SELECT candinfo_id, firstname, lastname, middle, email, phoneNumber, skills, status, clearance, resume, created, nameuser FROM " +
			        MessengerConstants.MM_SCHEMA_NAME + " .candinfo WHERE enabled = 0 and status = 'Have Not Contacted'";
	
	
	final static private String SQL_GET_ALL_EDUCATION = "Select CANDINFO_ID, EDID, DEGREE, SCHOOLNAME, MAJOR, COMPYEAR FROM "  +
			MessengerConstants.MM_SCHEMA_NAME + ".education ";
	               
	
	final static private String SQL_GET_ALL_EMPLOY = "Select CANDINFO_ID, EMPID, COMPNAME, TITLE, FROMMON, TOMON, PROJECT, PROJECTDESCRPT, PROJFROM, PROJTO FROM " 
	     + MessengerConstants.MM_SCHEMA_NAME + ".employer ";
	 
	
	final static private String SQL_GET_ALL_CERT = "Select CANDINFO_ID, CERTSID, CERTYEAR, CERTNAME from " +
		 MessengerConstants.MM_SCHEMA_NAME + ".certs ";
	 
			
	final static private String SQL_GET_ALL_BUSINESS =
			" SELECT BUSINESSDEV_ID, BUSINESSDEV_FIRSTNAME, BUSINESSDEV_LASTNAME, BUSINESSDEV_PHONENUMBER,BUSINESSDEV_EMAIL, nameuser FROM " +
			MessengerConstants.MM_SCHEMA_NAME + ".businessdev ";
	
	
	final static private String SQL_GET_ALL_TASKINFO =
			" SELECT task_ID, firstname, lastname, jobTitle, jobDescription, adminComments, Nameuser, candinfoResume, jobTemplate, dateCompleted, dueDate, urgent, cancelComments, dateCanceled FROM " +
			MessengerConstants.MM_SCHEMA_NAME + ".tasks WHERE enabled = 1";
	
	final static private String SQL_GET_ALL_COMPLETEDTASKINFO =
			" SELECT task_ID, firstname, lastname, jobTitle, jobDescription, adminComments, Nameuser, candinfoResume, jobTemplate, dateCompleted, dueDate, urgent, cancelComments, dateCanceled FROM " +
			MessengerConstants.MM_SCHEMA_NAME + ".tasks WHERE enabled = 0 and enabled != 'Canceled'";

	final static private String SQL_GET_ALL_CANCLEDTASKS =
			" SELECT task_ID, firstname, lastname, jobTitle, jobDescription, adminComments, Nameuser, candinfoResume, jobTemplate, dateCompleted, dueDate, urgent, cancelComments, dateCanceled FROM " +
			MessengerConstants.MM_SCHEMA_NAME + ".tasks WHERE enabled = 'Canceled'";
	
	
	
	final static private String SQL_GET_ALL_TASKS_AND_COMMENTS = 
			" SELECT t.task_ID, t.firstname, t.lastname, t.jobTitle, t.jobDescription, t.adminComments, t.Nameuser, t.candinfoResume, t.jobTemplate, t.dateCompleted, t.dueDate, t.urgent, t.cancelComments, t.dateCanceled, " +
			" c.idComments, c.commentString, c.task_ID, c.createUpdated, c.idnameuser FROM "+
			MessengerConstants.MM_SCHEMA_NAME + " .tasks t " +
			" JOIN " + MessengerConstants.MM_SCHEMA_NAME + ".comments c " +
			" ON t.task_ID = c.task_ID";
					
	final static private String SQL_GET_ALL_SELECTED_COMMENTS = 
			"SELECT idComments, commentString, task_ID, createUpdated, idnameuser FROM " + MessengerConstants.MM_SCHEMA_NAME + ".comments";
	
	
	
	public List<EmailRepo> getEmailRepo() {
		log.info("this is the email repo pull ");
		JdbcTemplate template = getJdbcTemplate();
		List<EmailRepo> newEmailRecord = template.query(SQL_GET_ALL_EMAIL_REPO, 
				new EmailParameterizedRowMapper<EmailRepo>());
		
		return newEmailRecord;
	}
		
	public List<CandidateInfo> getCandInfoID(){
		log.info("This is All of the candidates ");
		JdbcTemplate template = getJdbcTemplate();
		List<CandidateInfo> newCandidateInfo = template.query(SQL_GET_MAX_ID_CANDINFO, 
				new CandInfoParameterizedRowMapper<CandidateInfo>());
		
		return newCandidateInfo;
	}
	

     
    public long MaxID(CandidateInfo allCandidateInfoPosted) {
    	log.info("Max Id from candidate last step");
    	long candinfoID = allCandidateInfoPosted.getCandinfo_id();
    	if(candinfoID == 0){
    		String sqlMaxID = "Select MAX(Candinfo_ID) + 1 from "   +  MessengerConstants.MM_SCHEMA_NAME + ".candinfo " ;
    		candinfoID = getJdbcTemplate().queryForLong(sqlMaxID, new Object[0]);
    		System.out.println(sqlMaxID);
    	}
    	
    	return candinfoID;
	}
 
    @Override
	public List<CandidateInfo> getAllCandidateInfo() {
    	log.info("This is the max ID for ID");
		JdbcTemplate template = getJdbcTemplate();
		List<CandidateInfo> newCandidateInfo = template.query(SQL_GET_MAX_ID_CANDINFO_EMAIL_NEW, 
				new CandInfoParameterizedRowMapper<CandidateInfo>());
		
		return newCandidateInfo;
	}

	public List<JobRecord> getJobs() {
		log.info("this is the job id to query from ");
		JdbcTemplate template = getJdbcTemplate();
		List<JobRecord> newJobRecord = template.query(SQL_GET_ALL_JOBS_NEW, 
				new JOBParamaterizedRowMapper<JobRecord>());
		
		return newJobRecord;
	}
	
	public List<ContractRecord> getContracts() {
		log.info("this is the contract id to query from ");
		JdbcTemplate template = getJdbcTemplate();
		List<ContractRecord> newContractRecord = template.query(SQL_GET_ALL_CONTRACTS, 
				new ContractParameterizedRowMapper<ContractRecord>());
		
		for(ContractRecord rdc : newContractRecord){
			System.out.println("This is the id from the query " + rdc.getBuisnessdevID());
			System.out.println("This is the name from the query " + rdc.getBuisnessdevFirstname());
			System.out.println("This is the name from the query " + rdc.getBuisnessdevLastname());
			
		}
		
		return newContractRecord;
	}

	
	public List<BusinessRecord> getBusiness() {
		log.info("this is the business id to query from ");
		JdbcTemplate template = getJdbcTemplate();
		List<BusinessRecord> newBusinessRecord = template.query(SQL_GET_ALL_BUSINESS, 
				new BusinessParameterizedRowMapper<BusinessRecord>());
		
		return newBusinessRecord;
	}
	
	
	final static private String SQL_INSERT_CANDIDATE =
			" INSERT INTO " + 
			MessengerConstants.MM_SCHEMA_NAME + ".candinfo " +
			" (firstname, lastname, middle, email, phoneNumber, skills, created, nameuser, status, clearance,enabled ) " +
			" VALUES (?,?,?,?,?,?,?,?,?,?,0)";
	// Populate Candidate information table
	public void svCandidate(CandidateInfo candidateInfo){
		log.info("this is the candidate data being saved ");
		candinfoID = Long.MAX_VALUE;
		if(candinfoID != 0){
   		String sqlMaxID = "Select MAX(Candinfo_ID) + 1 from "   +  MessengerConstants.MM_SCHEMA_NAME + ".candinfo " ;
   		candinfoID = getJdbcTemplate().queryForLong(sqlMaxID, new Object[0]);
   		System.out.println("This is what here : " + candidateInfo.getCandinfoFirstName());
		}
		JdbcTemplate template = getJdbcTemplate();
		template.update(SQL_INSERT_CANDIDATE, new Object[] {
				candidateInfo.getCandinfoFirstName(),
				candidateInfo.getCandinfoLastName(),
				candidateInfo.getCandinfoMiddle(),
				candidateInfo.getCandinfoEmail(),
				candidateInfo.getCandinfoNumber(),
				candidateInfo.getCandinfoSkills(),
				new Date(),
				candidateInfo.getNameuser(),
				candidateInfo.getCandinfoStatus(),
				candidateInfo.getCandinfoClearance()});
		// Make a cal to the other table to poplutae it with fake data
	}
	
	
	public void svBusiness(BusinessRecord businessRecord) {
		log.info("this is the Business data being saved ");
		JdbcTemplate template = getJdbcTemplate();
		template.update(SQL_INSERT_BUSINESS, new Object[] {
				businessRecord.getBusinessFirstName(),
				businessRecord.getBusinessLastName(),
				businessRecord.getBusinessPhoneNumber(),
				businessRecord.getBusinessEmail(),
				businessRecord.getNameuser()});
	}
	
	
	final static private String SQL_INSERT_BUSINESS =
			" INSERT INTO " + 
			MessengerConstants.MM_SCHEMA_NAME + ".businessdev " +
					" (BUSINESSDEV_FIRSTNAME,BUSINESSDEV_LASTNAME,BUSINESSDEV_PHONENUMBER, BUSINESSDEV_EMAIL, nameuser ) " +
					" VALUES (?,?,?,?,?)";

	
	final static private String SQL_INSERT_CONTRACT =
			" INSERT INTO " + 
			MessengerConstants.MM_SCHEMA_NAME + ".contracts " +
					" (CONTRACT_TITLE,CONTRACT_AGENCY,CONTRACT_CLASS,CONTRACT_DATE,CONTRACT_STATUS,nameuser) " +
					" VALUES (?,?,?,?,?,?)";
	
	public void svContract(ContractRecord contractRecord){
		log.info("this is the contract data being saved ");
		JdbcTemplate template = getJdbcTemplate();
		template.update(SQL_INSERT_CONTRACT, new Object[] {
				contractRecord.getContractTitle(),
				contractRecord.getContractAgency(),
				contractRecord.getContractClass(),
				new Date(),
				contractRecord.getContractStatus(),
				contractRecord.getNameuser()});
	}
	
  //Update all rows.. to match columns in database
	final static private String SQL_INSERT_JOB =
			" INSERT INTO " + 
			MessengerConstants.MM_SCHEMA_NAME + ".jobs " +
					" (JOB_TITLE,JOB_DESCRIPTION,JOB_SKILL,JOB_POSITION,JOB_CLEAR,JOB_LOCATE,JOB_SKILLS_MAN,JOB_SKILLS_DESIRED,JOB_CERT_REQUIRE,JOB_CONUS,JOB_OCONUS,JOB_HOURSPERYEAR,JOB_SCHEDULE_COMMENT,JOB_MISSION_CRITICAL,JOB_NIGHT_WORK,JOB_LOCAL_TRAVEL,JOB_PAGER,JOB_PAGER_DUTY_COMMENTS,JOB_WORK_HOLIDAY,JOB_WORK_WEEKEND,JOB_SHIFTWORK,JOB_WARZONE,JOB_COOP,JOB_STATUS_CHANGE,JOB_UPDATE_DATE,JOB_CLOSE_DATE,JOB_DATE,CONTRACT_TITLE) " +
					" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public void svJob(JobRecord jobRecord){
		log.info("this is the job data being saved ");
		JdbcTemplate template = getJdbcTemplate();
		template.update(SQL_INSERT_JOB, new Object[] {
				jobRecord.getJobTitle(),
				jobRecord.getJobDescription(),
			    jobRecord.getJobSkill(),
				jobRecord.getJobPosition(),
				jobRecord.getJobClear(),
				jobRecord.getJobLocation(),
				jobRecord.getJobSkillsMandatory(),
				jobRecord.getJobSkillsDesired(),
				jobRecord.getJobCertRequired(),
				jobRecord.getJobConus(),
				jobRecord.getJobOconus(),
				jobRecord.getJobbHoursYear(),
				jobRecord.getJobSchedComment(),
				jobRecord.getJobMissionCritical(),
				jobRecord.getJobWorkNight(),
				jobRecord.getJobLocalTravel(),
				jobRecord.getJobPager(),
				jobRecord.getJobPagerComment(),
				jobRecord.getJobWorkHoliday(),
				jobRecord.getJobWorkWeekend(),
				jobRecord.getJobShiftWork(),
				jobRecord.getJobWarZone(),
				jobRecord.getJobCoop(),
			    jobRecord.getJobStatusChange(),
			    jobRecord.getJobUpdateDate(),
			    jobRecord.getJobCloseDate(),
				new Date(),
				jobRecord.getContractTitle()});
				
	}
	
	final static private String SQL_INSERT_EMAIL_REPO =
			" INSERT INTO " + 
			MessengerConstants.MM_SCHEMA_NAME + ".emailRepo " +
					" (CANDIDATE_ID,JOB_ID,CREATE_DT,nameuser ) " +
					" VALUES (?,?,?,?)";
	
	final static private String SQL_INSERT_EMAIL_REPO2 =
			" INSERT INTO " + 
			MessengerConstants.MM_SCHEMA_NAME + ".emailRepo2 " +
					" (BUSINESSDEV_ID,CONTRACT_ID,CREATE_DT, nameuser ) " +
					" VALUES (?,?,?,?)";
	final static private String SQL_INSERT_CERT = 
			 " INSERT INTO " +
		               MessengerConstants.MM_SCHEMA_NAME + ".certs " +
		    		   "(CERTYEAR,CERTNAME, CANDINFO_CER_ID ) " +
		               "VALUES(?,?,?)";
	
	
	final static private String SQL_INSERT_EMPLOY =
			" INSERT INTO " + 
					MessengerConstants.MM_SCHEMA_NAME + ".employer" +
					" (COMPNAME,TITLE,FROMMON,TOMON,PROJECT,PROJDESCRPT,PROJFROM, PROJTO ) " +
					" VALUES (?,?,?,?,?,?,?,?)";
	
	final static private String SQL_INSERT_EDUCATE =
			" INSERT INTO " + 
					MessengerConstants.MM_SCHEMA_NAME + ".education" +
					" (DEGREE,SCHOOLNAME,MAJOR,COMPYEAR, ) " +
					" VALUES (?,?,?,?)";
	
	public void svEmailQueue(final List<EmailRepo> allAddedEmailsInfo){
		log.info("this is the EMAIL REPO data being saved ");
		
		JdbcTemplate template = getJdbcTemplate();
		template.batchUpdate(SQL_INSERT_EMAIL_REPO, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement arg0, int arg1) throws SQLException {
				EmailRepo emailRepo = allAddedEmailsInfo.get(arg1);
				arg0.setLong(1, emailRepo.getCandidateID());
				arg0.setLong(2, emailRepo.getJobID());
				arg0.setDate(3, new java.sql.Date((new Date(System.currentTimeMillis())).getTime()));
				arg0.setString(4, emailRepo.getNameuser());
				
			}
			
			@Override
			public int getBatchSize() {
				return allAddedEmailsInfo.size();
			}
		});
		
		
	}

	public void updateCandidate(CandidateInfo updateCandidate){
		log.info("THIS IS THE UPDATED INFO");
		JdbcTemplate template = getJdbcTemplate();
		template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".candidates " +
		"SET CANDIDATE_FIRSTNAME = ?, CANDIDATE_LASTNAME = ?, CANDIDATE_EMAIL = ? " +
		"WHERE CANDIDATE_ID =" + updateCandidate.getCandidateID() , new Object[] {
		updateCandidate.getCandidateFirstName(),
		updateCandidate.getCandidateLastName(),
		updateCandidate.getCandidateEmail(),
		});
		}

	
  //Update Later based on what we want to update...
	public void updateJobinfo(JobRecord jobRecord) {
		log.info("this is the Job Info being updated");
		JdbcTemplate template = getJdbcTemplate();
		template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".jobs" +
		    " SET JOB_REQUEST_TYPE = ?, JOB_TITLE = ?, JOB_DESCRIPTION = ?, JOB_SKILL = ?, JOB_POSITION = ?, JOB_CLEAR = ?, JOB_LOCATE = ?, JOB_SKILLS_MAN = ?, JOB_SKILLS_DESIRED = ?, JOB_CERT_REQUIRE = ?, JOB_CONUS = ?, JOB_OCONUS = ?,"
		    + "JOB_HOURSPERYEAR = ?, JOB_SCHEDULE_COMMENT = ?, JOB_NON_PUB = ?, JOB_MISSION_CRITICAL = ?, JOB_NIGHT_WORK = ?, JOB_LOCAL_TRAVEL = ?, JOB_PAGER = ?, JOB_PAGER_DUTY_COMMENTS = ?, JOB_WORK_HOLIDAY = ?, JOB_WORK_WEEKEND = ?, JOB_SHIFTWORK = ?, JOB_WARZONE = ?, JOB_COOP = ?, CONTRACT_TITLE = ?, JOB_PRIORITY = ?, nameuser = ?" + 
		  " WHERE JOB_ID = " + jobRecord.getJobID(), new Object[] {
		jobRecord.getJobRequesttype(),
		jobRecord.getJobTitle(),
		jobRecord.getJobDescription(),
		jobRecord.getJobSkill(),
		jobRecord.getJobPosition(),
		jobRecord.getJobClear(),
		jobRecord.getJobLocation(),
		jobRecord.getJobSkillsMandatory(),
		jobRecord.getJobSkillsDesired(),
		jobRecord.getJobCertRequired(),
		jobRecord.getJobConus(),
		jobRecord.getJobOconus(),
		jobRecord.getJobbHoursYear(),
		jobRecord.getJobSchedComment(),
		jobRecord.getJobNonPub(),
		jobRecord.getJobMissionCritical(),
		jobRecord.getJobWorkNight(),
		jobRecord.getJobLocalTravel(),
		jobRecord.getJobPager(),
		jobRecord.getJobPagerComment(),
		jobRecord.getJobWorkHoliday(),
		jobRecord.getJobWorkWeekend(),
		jobRecord.getJobShiftWork(),
		jobRecord.getJobWarZone(),
		jobRecord.getJobCoop(),
		jobRecord.getContractTitle(),
		jobRecord.getJobPriority(),
		jobRecord.getNameuser()
		});
		
		}

	public void deleteJobinfo(JobRecord jobRecord) {
		log.info("this is the job info being deleted");
		JdbcTemplate template = getJdbcTemplate();
		template.update("Delete FROM " + MessengerConstants.MM_SCHEMA_NAME + ".jobs WHERE JOB_ID = " + jobRecord.getJobID());
		
		}



	public void updateBusinessinfo(BusinessRecord updateBusiness) {
		log.info("THIS IS THE UPDATED BUSINESS INFO");
	
		log.info("id value for bdpoc: " + updateBusiness.getBusinessID());
		JdbcTemplate template = getJdbcTemplate();
		template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".businessdev " +
		" SET BUSINESSDEV_FIRSTNAME = ?, BUSINESSDEV_LASTNAME = ?, BUSINESSDEV_PHONENUMBER = ?, BUSINESSDEV_EMAIL = ?, nameuser = ? " +
		" WHERE BUSINESSDEV_ID =" + updateBusiness.getBusinessID(), new Object[] {
			updateBusiness.getBusinessFirstName(),
			updateBusiness.getBusinessLastName(),
			updateBusiness.getBusinessPhoneNumber(),
			updateBusiness.getBusinessEmail(),
			updateBusiness.getNameuser()
			});
		
	}

	public void deleteBusinessinfo(BusinessRecord businessRecord) {
		log.info("this is the business info being deleted");
		JdbcTemplate template = getJdbcTemplate();
		template.update("Delete FROM " + MessengerConstants.MM_SCHEMA_NAME + ".businessdev WHERE BUSINESSDEV_ID = " + businessRecord.getBusinessID());
		
	}

	public void deleteContractinfo(ContractRecord contractRecord) {
		log.info("this is the Contract info being deleted");
		JdbcTemplate template = getJdbcTemplate();
		template.update("Delete FROM " + MessengerConstants.MM_SCHEMA_NAME + ".contracts WHERE CONTRACT_ID = " + contractRecord.getContractID());
		
	}

	public void updateContractinfo(ContractRecord updateContract) {
		log.info("THIS IS THE UPDATED Contract INFO");
		JdbcTemplate template = getJdbcTemplate();
		template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".contracts " +
		"SET CONTRACT_TITLE = ?, CONTRACT_AGENCY = ?, CONTRACT_CLASS = ?, CONTRACT_STATUS = ?, nameuser = ?, BUSINESSDEV_FIRSTNAME = ?, BUSINESSDEV_LASTNAME = ?, BUSINESSDEV_PHONENUMBER = ?, BUSINESSDEV_EMAIL = ? " +
		"WHERE CONTRACT_ID =" + updateContract.getContractID() , new Object[] {
			updateContract.getContractTitle(),
			updateContract.getContractAgency(),
			updateContract.getContractClass(),
			updateContract.getContractStatus(),
			updateContract.getNameuser(),
			updateContract.getBuisnessdevFirstname(),
			updateContract.getBuisnessdevLastname(),
			updateContract.getBuisnessdevPhonenumber(),
			updateContract.getBuisnessdevEmail()});
		
	}

	public List<EmailRepo2> getEmailRepo2() {
		log.info("this is the email2 repo pull ");
		JdbcTemplate template = getJdbcTemplate();
		List<EmailRepo2> newEmailRecord2 = template.query(SQL_GET_ALL_EMAIL_REPO2, 
				new Email2ParameterizedRowMapper<EmailRepo2>());
		
		return newEmailRecord2;
	}

	public void svEmailQueue2(final List<EmailRepo2> allAddedEmailsInfo2) {
         log.info("this is the EMAIL REPO2 data being saved ");
		
		JdbcTemplate template = getJdbcTemplate();
		template.batchUpdate(SQL_INSERT_EMAIL_REPO2, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement arg0, int arg1) throws SQLException {
				EmailRepo2 emailRepo2 = allAddedEmailsInfo2.get(arg1);				
				arg0.setLong(1, emailRepo2.getBusinessID());
				arg0.setLong(2, emailRepo2.getContractID());
				arg0.setDate(3, new java.sql.Date((new Date(System.currentTimeMillis())).getTime()));
				arg0.setString(4, emailRepo2.getNameuser());
				
			}
			
			
			public int getBatchSize() {
				return allAddedEmailsInfo2.size();
			}
		});
		
	}

	@Override
	public void svCandidateInfo(CandidateInfo candidateInfo) {
		log.info("Resume info data being saved ");
		candinfoID = Long.MAX_VALUE;
		if(candinfoID != 0){
   		String sqlMaxID = "Select MAX(Candinfo_ID) + 1 from "   +  MessengerConstants.MM_SCHEMA_NAME + ".candinfo " ;
   		candinfoID = getJdbcTemplate().queryForLong(sqlMaxID, new Object[0]);
   		System.out.println("This is what here : " + candidateInfo.getCandinfoFirstName());
   		}		
		JdbcTemplate template = getJdbcTemplate();
		template.update(SQL_INSERT_CANDIDATE, new Object[] {
				
				candidateInfo.getCandinfoFirstName(),
				candidateInfo.getCandinfoLastName(),
				candidateInfo.getCandinfoMiddle(),
				candidateInfo.getCandinfoEmail(),
				candidateInfo.getCandinfoNumber(),
				candidateInfo.getCandinfoSkills(),
				candidateInfo.getCandinfoCreated()
		});
	}
	
	@Override
	public void svCandidateInfo(EducationResume educationResume) {
		log.info("Education info data being saved !!!!");	    
		
		final String sqlEDUIN  = "INSERT INTO " +  MessengerConstants.MM_SCHEMA_NAME + ".education (CANDINFO_ID, DEGREE, SCHOOLNAME, MAJOR, COMPYEAR) "
		    		    + "VALUES (?,?,?,?,?)";

		    	   getJdbcTemplate().update(
		    		  sqlEDUIN, candinfoID, educationResume.getDegree(), educationResume.getSchoolName(), educationResume.getMajor(), educationResume.getCompYear());
		
		

}
	@Override
	public void svCandidateInfo(EmployResume employResume) {
		log.info("Employ info data being saved ");
		
		final String sqlEMP = "INSERT INTO " +  MessengerConstants.MM_SCHEMA_NAME + 
    			".employer  (CANDINFO_ID, COMPNAME,TITLE,FROMMON,TOMON,PROJECT,PROJECTDESCRPT,PROJFROM, PROJTO) " +
					" VALUES (?,?,?,?,?,?,?,?,?)";
    	
    	       getJdbcTemplate().update(
    		    sqlEMP, candinfoID, employResume.getCompName(), employResume.getTitle(), employResume.getFromMonth(), employResume.getToYear(), employResume.getProjectRole(), employResume.getProjDescpt(), 
    		    employResume.getProjfromMonth(), employResume.getProjtoYear());
		
			}
    // Trigger when populate the add candidates
	@Override
	public void svCertResume(CertResume certResume) {
		log.info("Certification info data being saved ");
		
		final String sqlCert = "INSERT INTO " +  MessengerConstants.MM_SCHEMA_NAME + ".certs (CANDINFO_ID, CERTYEAR, CERTNAME) "
			    + "VALUES (?,?,?)";

			    getJdbcTemplate().update(
			    sqlCert, candinfoID,certResume.getCertYear(), certResume.getCertName());
		    	
	 }
	
	//Wizard trigger resume save.	
	public void saveCandidateInfo(List<CandidateInfo> cnd) {
		for(CandidateInfo om: cnd){
			System.out.println("Inside cnd loop last stage " + om.getCandinfoFirstName());
		}
		
		long candinfoID = Long.MAX_VALUE;
		if(candinfoID != 0){
   		String sqlMaxID = "Select MAX(Candinfo_ID) + 1 from "   +  MessengerConstants.MM_SCHEMA_NAME + ".candinfo " ;
   		candinfoID = getJdbcTemplate().queryForLong(sqlMaxID, new Object[0]);
   		System.out.println("Max Resume wizard id number is :" + candinfoID);
		 }

	}
	

	@Override
	public void saveEmployInfo(final List<EmployResume> mainER) {
		
		JdbcTemplate template = getJdbcTemplate();
		template.batchUpdate(SQL_INSERT_EMPLOY, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement arg0, int arg1)
					throws SQLException {
				EmployResume employResume = mainER.get(arg1);
				arg0.setNString(1, employResume.getCompName());
				arg0.setNString(2, employResume.getTitle());
				arg0.setDate(3, (java.sql.Date) employResume.getFromMonth());
				arg0.setDate(4, (java.sql.Date) employResume.getToYear());
				arg0.setNString(5, employResume.getProjectRole());
				arg0.setNString(6, employResume.getProjDescpt());
				arg0.setDate(3, (java.sql.Date) employResume.getProjfromMonth());
				arg0.setDate(3, (java.sql.Date) employResume.getProjtoYear());
				
			}
			
			@Override
			public int getBatchSize() {
				return mainER.size();
			}
        });
		
	}


	@Override
	public void saveCertificationInfo(final List<CertResume> cert) {
		for(CertResume om : cert){
			System.out.println("Inside cert loop last stage " + om.getCertName());
		}
		
		JdbcTemplate template = getJdbcTemplate();
		template.batchUpdate(SQL_INSERT_CERT, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement arg0, int arg1)
					throws SQLException {
				CertResume certResume = cert.get(arg1);
				arg0.setNString(1, certResume.getCertName());
				arg0.setDate(2, (java.sql.Date) certResume.getCertYear());
				arg0.setLong(3, certResume.getCandinfo_id());
								
			}
			
			@Override
			public int getBatchSize() {
				return cert.size();
			}
        });

	}

	@Override
	public void saveEducationInfo(final List<EducationResume> empEdu) {
		for(EducationResume on : empEdu){
			System.out.println("Inside empEdu loop laststage " + on.getDegree());
		}

		JdbcTemplate template = getJdbcTemplate();
		template.batchUpdate(SQL_INSERT_EDUCATE, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement arg0, int arg1)
					throws SQLException {
				EducationResume educationResume = empEdu.get(arg1);
				arg0.setNString(1, educationResume.getDegree());
				arg0.setNString(2, educationResume.getSchoolName());
				arg0.setNString(2, educationResume.getMajor());
				arg0.setDate(2, (java.sql.Date) educationResume.getCompYear());
			}
			
			@Override
			public int getBatchSize() {
				return empEdu.size();
			}
        });
		
	}

	@Override
	public List<EducationResume> getAllEducation() {
		log.info("this is the education pull ");
		JdbcTemplate template = getJdbcTemplate();
		List<EducationResume> newEducationResume = template.query(SQL_GET_ALL_EDUCATION, 
				new EducateInfoParametrizedRowMapper<EducationResume>());
		
		return newEducationResume;
	}

	@Override
	public List<EmployResume> getAllEmploy() {
		log.info("this is the employ pull ");
		JdbcTemplate template = getJdbcTemplate();
		List<EmployResume> newEmployResume = template.query(SQL_GET_ALL_EMPLOY, 
				new EmployInfoParameterizedRowMapper<EmployResume>());
		
		return newEmployResume;
	}

	@Override
	public List<CertResume> getAllCert() {
		log.info("this is the cert pull ");
		JdbcTemplate template = getJdbcTemplate();
		List<CertResume> newCertResume = template.query(SQL_GET_ALL_CERT, 
				new CertInfoParameterizedRowMapper<CertResume>());
		
		return newCertResume;
	}

	@Override
	public void getALLCert(CertResume certResume) {
        
	 sqlCERTID = certResume.getCandinfo_id();
	 System.out.println("This is the value I need " + sqlCERTID);
	 
	 findByForeignKey(sqlCERTID);
		
	}
	
	public CertResume findByForeignKey(long custId){
			System.out.println("This is what here " + custId);
		
		return null;
	}


	@Override
	public List<CandidateInfo> getAllQueryRecord() {
		log.info("This is the entire record call");
		
		JdbcTemplate template = getJdbcTemplate();
		List<CandidateInfo> newCandidateInfo = template.query(SQL_GET_MAX_ID_CANDINFO_QUERY, 
				new CandInfoParameterizedRowMapper<CandidateInfo>());
		return newCandidateInfo;
	}
	
	
	
//	SQL_GET_MAX_ID_CANDINFO_EMAIL

	
	@Override
	public void updateCandidateInfo(CandidateInfo candidateInfo) {
		log.info("THIS IS THE UPDATED Candidate INFO");
		JdbcTemplate template = getJdbcTemplate();
		template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".candinfo " +
		"SET firstname = ?, lastname = ?, middle = ?, email = ?, phoneNumber = ?, skills = ?, status = ?, clearance = ?, nameuser = ? " +
		" WHERE candinfo_id = " + candidateInfo.getCandinfo_id(), new Object[] {
			
			candidateInfo.getCandinfoFirstName(),
			candidateInfo.getCandinfoLastName(),
			candidateInfo.getCandinfoMiddle(),
			candidateInfo.getCandinfoEmail(),
			candidateInfo.getCandinfoNumber(),
			candidateInfo.getCandinfoSkills(),
			candidateInfo.getCandinfoStatus(),
			candidateInfo.getCandinfoClearance(),
			candidateInfo.getNameuser()
		});
		System.out.println("This is my value here at DAO IMPL = " + candidateInfo.getCandinfo_id());
		System.out.println("This is my value here at DAO IMPL First Name = " + candidateInfo.getCandinfoFirstName());
	}

	@Override
	public void updateEducationResume(EducationResume educationResume) {
		log.info("THIS IS THE UPDATED Education Resume");
		JdbcTemplate template = getJdbcTemplate();
		template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".education " +
		"SET degree = ?, schoolname = ?, major = ?, compyear = ?" +
		"WHERE  edid = " + educationResume.getEdID(), new Object[] {
			educationResume.getDegree(),
			educationResume.getSchoolName(),
			educationResume.getMajor(),
			educationResume.getCompYear()
			
		});
		System.out.println("This is my value here at DAO IMPL ID = " + educationResume.getEdID());
		System.out.println("This is my value here at DAO IMPL Degree = " + educationResume.getDegree());
	}

	@Override
	public void updateCertResume(CertResume certResume) {
		log.info("THIS IS THE UPDATED CERT Resume");
		JdbcTemplate template = getJdbcTemplate();
		template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".certs " +
		"SET certyear = ?, certname = ?" +
		"WHERE certsid = " + certResume.getCerID(), new Object[] {
			certResume.getCertYear(),
			certResume.getCertName()
		});
		System.out.println("This is my value cert statment" );
		System.out.println("This is my value cert statment = " + certResume.getCerID() );
	}

	@Override
	public void updateEmploynResume(EmployResume employResume) {
		log.info("THIS IS THE UPDATED EMPLOY Resume");
		JdbcTemplate template = getJdbcTemplate();
		template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".employer " +
		"SET compname = ?, title = ?, frommon = ?, tomon = ?, project = ?, projectdescrpt = ?, projfrom = ?," +
		" projto = ?"	+	
		"WHERE empid = " + employResume.getEmployID(), new Object[] {
			employResume.getCompName(),
			employResume.getTitle(),
			employResume.getFromMonth(),
			employResume.getToYear(),
			employResume.getProjectRole(),
			employResume.getProjDescpt(),
			employResume.getProjfromMonth(),
			employResume.getProjtoYear()
		});
		
	}

	@Override
	public void candID(long passID) {
		
	}

	
	

	@Override
	public List<CertResume> getSelectedCertsInfomation(Long passID) {
		
		JdbcTemplate template = getJdbcTemplate();
		List<CertResume> newCertResume = template.query("Select * from " + MessengerConstants.MM_SCHEMA_NAME + ".certs where candinfo_id =" + passID, 
				new CertInfoParameterizedRowMapper<CertResume>());
			return newCertResume;
		
		 
	}


@Override
public List<EducationResume> getSelectedEducationInformation(Long passID) {
	log.info("this is the education pull ");
	JdbcTemplate template = getJdbcTemplate();
	List<EducationResume> newEducationResume = template.query("Select * from " + MessengerConstants.MM_SCHEMA_NAME + ".education where candinfo_id =" + passID, 
			new EducateInfoParametrizedRowMapper<EducationResume>());
	
	return newEducationResume;
}

@Override
public List<EmployResume> getSelectedEmployInfomation(Long passID) {
	JdbcTemplate template = getJdbcTemplate();
	List<EmployResume> newEmployResume = template.query("Select * from " + MessengerConstants.MM_SCHEMA_NAME + ".employer where candinfo_id ="  + passID,
			new EmployInfoParameterizedRowMapper<EmployResume>());
	
	return newEmployResume;
	
}

@Override
public void svUpdatedEmploy(EmployResume employResume) {
	
	JdbcTemplate template = getJdbcTemplate();
	template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".employer " +
	"SET compname = ?, title = ?, frommon = ?, tomon = ?, project = ?, projectdescrpt = ?, projfrom = ?," +
	" projto = ?"	+	
	"WHERE empid = " + employResume.getEmployID(), new Object[] {
		employResume.getCompName(),
		employResume.getTitle(),
		employResume.getFromMonth(),
		employResume.getToYear(),
		employResume.getProjectRole(),
		employResume.getProjDescpt(),
		employResume.getProjfromMonth(),
		employResume.getProjtoYear()
	});
	
	System.out.println("This is my value employ statment = " + employResume.getCompName() + " where ID = " + employResume.getEmployID());
}

@Override
public void svUpdatedEducation(EducationResume educationResume) {
	JdbcTemplate template = getJdbcTemplate();
	template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".education " +
	"SET degree = ?, schoolname = ?, major = ?, compyear = ?" +
	"WHERE  edid = " + educationResume.getEdID(), new Object[] {
		educationResume.getDegree(),
		educationResume.getSchoolName(),
		educationResume.getMajor(),
		educationResume.getCompYear()
		
	});
	System.out.println("This is my value here at DAO IMPL ID = " + educationResume.getEdID());
	System.out.println("This is my value here at DAO IMPL Degree = " + educationResume.getDegree());
	
}

@Override
public void svUpdatedCert(CertResume certResume) {
	JdbcTemplate template = getJdbcTemplate();
	template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".certs " +
	"SET certyear = ?, certname = ?" +
	"WHERE certsid = " + certResume.getCerID(), new Object[] {
		certResume.getCertYear(),
		certResume.getCertName()
	});
	System.out.println("This is my value cert statment = " + certResume.getCertName());
	System.out.println("This is my id cert statment = " + certResume.getCerID() );
	
}

@Override
public void svUpdatedCand(CandidateInfo candidateInfo) {
	
	JdbcTemplate template = getJdbcTemplate();
	template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".candinfo " +
	"SET firstname = ?, lastname = ?, middle = ?, phoneNumber = ?, email = ?, skills = ?, status = ?, clearance = ?, nameuser = ? " +
	"WHERE candinfo_id = " + candidateInfo.getCandinfo_id(), new Object[] {
		
		candidateInfo.getCandinfoFirstName(),
		candidateInfo.getCandinfoLastName(),
		candidateInfo.getCandinfoMiddle(),
		candidateInfo.getCandinfoNumber(),
		candidateInfo.getCandinfoEmail(),
		candidateInfo.getCandinfoSkills(),
		candidateInfo.getCandinfoStatus(),
		candidateInfo.getCandinfoClearance(),
		candidateInfo.getNameuser()
	});
	System.out.println("This is my value here at DAO IMPL = " + candidateInfo.getCandinfo_id());
	System.out.println("This is my value here at DAO IMPL First Name = " + candidateInfo.getCandinfoFirstName());
	System.out.println("This is who editing the candidate" +" "+ candidateInfo.getNameuser());
	
	
}

@Override
public void deleteCandidateinfo(CandidateInfo updateCandidateinfo) {
	log.info("This is the deleted info");
	log.info("This is the id number " + updateCandidateinfo.getCandinfo_id());
	JdbcTemplate template = getJdbcTemplate();
	template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME  +".candinfo SET enabled=1 WHERE CANDINFO_ID = " + updateCandidateinfo.getCandinfo_id());
	
}

/*public void deleteCandidateinfo1( DeleteCandidate) {
	log.info("This is the deleted info");
	JdbcTemplate template = getJdbcTemplate ();
	template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME  +".candinfo SET enabled=1 WHERE CANDINFO_ID = " + updateCandidateinfo.getCandinfo_id());
	
}*/



/*@Override
public void svCategory(Category category) {
	log.info("Save Category");
	categoryID = Long.MAX_VALUE;
	if(categoryID != 0){
		String sqlMaxID1 = "Select MAX(Category_ID) + 1 from "   +  MessengerConstants.MM_SCHEMA_NAME + ".category " ;
		categoryID = getJdbcTemplate().queryForLong(sqlMaxID1, new Object[0]);
		System.out.println("Max id number is :" + categoryID);
	}
	JdbcTemplate template = getJdbcTemplate();
	template.update(SQL_INSERT_CATEGORY, new Object[]{	
	  category.getCatName(),
	  category.getCatDescrp()); 
	
   }
*/





final static private String SQL_INSERT_FLATFILE =
"INSERT INTO " +
  MessengerConstants.MM_SCHEMA_NAME + ".flat_file" +
 "(FLAT_FILE_CLOB, FLAT_FILE_NEW_NAME, FLAT_FILE_OLD_NAME, FLAT_FILE_CREATED, FLAT_FILE_CATID)" +
 "VALUES(?,?,?,?,?)";		

@Override
public void svFlatFile(final List<FlatFile> flat) {
	log.info("Save Flat File");
	for(FlatFile f : flat){
		System.out.println(f.getClobFile().length + " : " + f.getNewName() + " : " + f.getOldName());
	}
	
	
	flatfileID = Long.MAX_VALUE;
	if(flatfileID != 0){
		String sqlMaxID = "Select MAX(flat_file_id) + 1 from "   +  MessengerConstants.MM_SCHEMA_NAME + ".flat_file " ;
		flatfileID = getJdbcTemplate().queryForLong(sqlMaxID, new Object[0]);
		System.out.println("This is what here : " + flatfileID);
		}		
	
	
		
	JdbcTemplate template = getJdbcTemplate();	
	template.batchUpdate(SQL_INSERT_FLATFILE, new BatchPreparedStatementSetter() {

		@Override
		public void setValues(PreparedStatement arg0, int arg1)
				throws SQLException {
			FlatFile ffile =  flat.get(arg1);
			arg0.setBytes(1, ffile.getClobFile());
			arg0.setNString(2, ffile.getNewName());
			arg0.setNString(3, ffile.getOldName());
			arg0.setDate(4, new java.sql.Date((new Date(System.currentTimeMillis())).getTime()));
			arg0.setLong(5, categoryID);
		}
		
		@Override
		public int getBatchSize() {
			return flat.size();
		}
    });
	
}

@Override
public void svPassID(Category category) {
	categoryID = category.getCatID();
	System.out.println("The id is: " + categoryID);	
}

final static private String SQL_INSERT_BUSINESSFILE =
" INSERT INTO " + 
MessengerConstants.MM_SCHEMA_NAME + ".businessdev " +
		" (BUSINESSDEV_FIRSTNAME,BUSINESSDEV_LASTNAME,BUSINESSDEV_PHONENUMBER, BUSINESSDEV_EMAIL, FLAT_ID) " +
		" VALUES (?,?,?,?,?)";

@Override
public void svBusFile(final List<BusinessRecord> br) {
	log.info("Save Buisness Excel");
	JdbcTemplate template = getJdbcTemplate();
	template.batchUpdate(SQL_INSERT_BUSINESSFILE, new BatchPreparedStatementSetter() {

		
		@Override
		public void setValues(PreparedStatement arg0, int arg1)
				throws SQLException {
			BusinessRecord brecord = br.get(arg1);
			arg0.setNString(1, brecord.getBusinessFirstName());
			arg0.setNString(2, brecord.getBusinessLastName());
			arg0.setNString(3, "555-55-5555");
			arg0.setNString(4, brecord.getBusinessEmail());
			arg0.setLong(5, flatfileID);
			
		}
		
		@Override
		public int getBatchSize() {
			return br.size();
		}
		
	});
}

final static private String SQL_INSERT_CONTRACTFILE =
" INSERT INTO " + 
MessengerConstants.MM_SCHEMA_NAME + ".contracts " +
		" (CONTRACT_TITLE,CONTRACT_AGENCY,CONTRACT_CLASS,CONTRACT_DATE,FLATFILE_ID) " +
		" VALUES (?,?,?,?,?)";

@Override
public void svContFIle(final List<ContractRecord> cr) {
	log.info("Save Contracts Excel");
	
	JdbcTemplate template = getJdbcTemplate();
	template.batchUpdate(SQL_INSERT_CONTRACTFILE, new BatchPreparedStatementSetter() {
		
		@Override
		public void setValues(PreparedStatement arg0, int arg1)
				throws SQLException {
			ContractRecord crecord = cr.get(arg1);
			arg0.setNString(1, crecord.getContractTitle());
			arg0.setNString(2, crecord.getContractAgency());
			arg0.setNString(3, crecord.getContractClass());
			arg0.setDate(4, new java.sql.Date((new Date(System.currentTimeMillis())).getTime()));
			arg0.setLong(5, flatfileID);
			
		}
		
		@Override
		public int getBatchSize() {			
			return cr.size();
		}		
	});	
}

final static private String SQL_INSERT_JOBFILE =
" INSERT INTO " + 
MessengerConstants.MM_SCHEMA_NAME + ".jobs " +		 
		" (JOB_REQ,JOB_REQUEST_TYPE,JOB_TITLE, JOB_SKILL, JOB_POSITION, JOB_CLEAR, JOB_LOCATE, JOB_DESCRIPTION, JOB_SKILLS_MAN, JOB_SKILLS_DESIRED, JOB_CERT_REQUIRE, JOB_CONUS,JOB_OCONUS,JOB_HOURSPERYEAR,JOB_SCHEDULE_COMMENT,JOB_NON_PUB,JOB_MISSION_CRITICAL,JOB_NIGHT_WORK,JOB_LOCAL_TRAVEL,JOB_PAGER, JOB_PAGER_DUTY_COMMENTS,JOB_WORK_HOLIDAY,JOB_WORK_WEEKEND,JOB_SHIFTWORK,JOB_WARZONE,JOB_COOP,JOB_STATUS_CHANGE,JOB_UPDATE_DATE,JOB_CLOSE_DATE, CONTRACT_TITLE)" 
         + "Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 



		

@Override
public void svJobFile(final List<JobRecord> jr) {
	 log.info("Saving the new Job files");
	 
	 
	
	JdbcTemplate template = getJdbcTemplate();
	template.batchUpdate(SQL_INSERT_JOBFILE, new BatchPreparedStatementSetter() {		
		
	
		@Override
		public void setValues(PreparedStatement arg0, int arg1) 
				throws SQLException {			
			
			JobRecord jrecord = jr.get(arg1);
			arg0.setNString(1, jrecord.getJobRequest());
			arg0.setNString(2, jrecord.getJobRequesttype());
			arg0.setNString(3, jrecord.getJobTitle());
			arg0.setNString(4, jrecord.getJobSkill());
			arg0.setNString(5, jrecord.getJobPosition());
			arg0.setNString(6, jrecord.getJobClear());
			arg0.setNString(7, jrecord.getJobLocation());
			arg0.setNString(8, jrecord.getJobDescription());
			arg0.setNString(9, jrecord.getJobSkillsMandatory());
			arg0.setNString(10, jrecord.getJobSkillsDesired());
			arg0.setNString(11, jrecord.getJobCertRequired());
			arg0.setNString(12, jrecord.getJobConus());
			arg0.setNString(13, jrecord.getJobOconus());		    
			arg0.setLong(14, jrecord.getJobbHoursYear());
			arg0.setNString(15, jrecord.getJobSchedComment());
			arg0.setNString(16, jrecord.getJobNonPub());
			arg0.setNString(17, jrecord.getJobMissionCritical());
			arg0.setNString(18, jrecord.getJobWorkNight());
			arg0.setNString(19, jrecord.getJobLocalTravel());
			arg0.setNString(20, jrecord.getJobPager());
			arg0.setNString(21, jrecord.getJobPagerComment());
			arg0.setNString(22, jrecord.getJobWorkHoliday());
			arg0.setNString(23, jrecord.getJobWorkWeekend());
			arg0.setNString(24, jrecord.getJobShiftWork());
			arg0.setNString(25, jrecord.getJobWarZone());
			arg0.setNString(26, jrecord.getJobCoop());
			arg0.setNString(27, jrecord.getJobUpdateDate());
			arg0.setNString(28,jrecord.getJobUpdateDate());
		    arg0.setNString(29,jrecord.getJobCloseDate());
//			arg0.setDate(27, (java.sql.Date) new Date());
		    arg0.setNString(30, jrecord.getContractTitle());
			
		}
		
		@Override
		public int getBatchSize() {			
			return jr.size();
		}
	});

}

@Override
public void svNewuser(RegisterCandidate regcan) {
	
	final String sqlNewUser = "INSERT INTO " +  MessengerConstants.MM_SCHEMA_NAME + ".user " +
			" (FIRSTNAME, LASTNAME, EMAIL, USERNAME,PASSWORD, ENABLED, DATECREATED,MODDATE,UPDATEUSER)"
		    + "VALUES (?,?,?,?,?,1,?,?,?)";

		    getJdbcTemplate().update(
		   	sqlNewUser, regcan.getFirstName(),regcan.getLastName(),regcan.getEmail(),regcan.getUsername(),regcan.getPassword(), new Date(),new Date(),"ADMIN");   	
		    userid = Long.MAX_VALUE;
		    if(userid != 0){
		    String sqlMaxID = "Select MAX(userid) from "   +  MessengerConstants.MM_SCHEMA_NAME + ".user " ;
	   		userid = getJdbcTemplate().queryForLong(sqlMaxID, new Object[0]);
		    
		    }
		    
		    
		    final String sqluserroleid ="INSERT INTO " +  MessengerConstants.MM_SCHEMA_NAME + ".user_roles " + "(username, user_role, role_id)" +
		    "VALUES (?,?,?)";
		    
		    getJdbcTemplate().update(
		    		sqluserroleid, regcan.getUsername(),"ROLE_USER", userid);
		    
}

@Override
public void deleteCertResume(CertResume certResume) {
	log.info("this is the Cert Resume info being deleted");
	JdbcTemplate template = getJdbcTemplate();
	template.update("Delete FROM " + MessengerConstants.MM_SCHEMA_NAME + ".certs WHERE Cer_ID = " + certResume.getCerID());
	
}


public List<ContractsJob> getSqlGetContractsJobs() {
	log.info("Getting JOBS and CONTRACTS ID'S");
	JdbcTemplate template = getJdbcTemplate();
	List<ContractsJob> newContractsJob = template.query(SQL_GET_CONTRACTS_JOBS, new ContractsJobsParameterizedRowMapper<ContractsJob>());
	return newContractsJob;
}

@Override
public List<ContractsJob> getAllJobPriority() {
	log.info("Getting all High priority jobs from the database");
	JdbcTemplate template = getJdbcTemplate();
	List<ContractsJob> newJobRecord = template.query(SQL_GET_ALL_JOBS_PRIORITY, new ContractJobsParameterizedRowMapper<ContractsJob>());
	return newJobRecord;
}


@Override
public List<ContractRecord> getAllBuisnessContracts() {
	log.info("Getting all business/contract info");
	JdbcTemplate template = getJdbcTemplate();
	List<ContractRecord> newContractsJob = template.query(SQL_GET_ALL_BUISNESS_CONTRACT, new ContractParameterizedRowMapper<ContractRecord>());
	return newContractsJob;
}

@Override
public List<JobRecord> getAllContractTitles() {
	
	return null;
}

@Override
public List<JobRecord> getJobs2() {
	
	JdbcTemplate template = getJdbcTemplate();
	List<JobRecord> newJobRecord = template.query(SQL_GET_ALL_JOBS, 
			new JOBParamaterizedRowMapper<JobRecord>());
	
	return newJobRecord;
}

@Override
public List<CandidateInfo> getAllCandidateInfo2() {
	
	JdbcTemplate template = getJdbcTemplate();
	List<CandidateInfo> newCandidateInfo = template.query(SQL_GET_MAX_ID_CANDINFO, 
			new CandInfoParameterizedRowMapper<CandidateInfo>());
	return newCandidateInfo;
}
//final  String SQL_GET_ALL_SELECTED_JOBS =
////Select j.job_id from massEmail.jobs j, massEmail.contracts c where j.contract_id = c.contract_id and j.contract_id = 1767;
//
//" SELECT j.JOB_ID, j.JOB_REQ, j.JOB_TITLE, j.JOB_SKILL, j.JOB_POSITION, j.JOB_CLEAR, j.JOB_LOCATE, j.JOB_DESCRIPTION, j.JOB_SKILLS_MAN, j.JOB_SKILLS_DESIRED, j.JOB_CERT_REQUIRE, j.JOB_CONUS, j.JOB_OCONUS,j.JOB_HOURSPERYEAR,j.JOB_SCHEDULE_COMMENT, j.JOB_MISSION_CRITICAL," +
//" j.JOB_NIGHT_WORK, j.JOB_LOCAL_TRAVEL, j.JOB_PAGER, j.JOB_PAGER_DUTY_COMMENTS, j.JOB_WORK_HOLIDAY,j.JOB_WORK_WEEKEND,j.JOB_SHIFTWORK, j.JOB_WARZONE, j.JOB_COOP,j.JOB_STATUS_CHANGE, j.JOB_UPDATE_DATE,j.JOB_CLOSE_DATE, j.JOB_DATE, c.CONTRACT_TITLE, c.CONTRACT_ID, " +
//" c.CONTRACT_AGENCY, c.contract_DATE, c.CONTRACT_CLASS, c.Contract_Status ,c.nameuser, j.JOB_PRIORITY FROM " +
//MessengerConstants.MM_SCHEMA_NAME + ".jobs j, " +
//MessengerConstants.MM_SCHEMA_NAME + " .contracts c " +
//" where j.contract_id = c.contract_id " +
//" and j.contract_id = " + passID ;  


@Override
public List<ContractsJob> getSelectedJobInfo(Long passID) {
	log.info("Getting all jobs based off current selected contract");
	log.info("Seeing it get back here " + passID);
	System.out.println("I get here finally");	
	JdbcTemplate template = getJdbcTemplate();	
	List<ContractsJob> jobContractRecords = template.query("SELECT j.JOB_ID, j.JOB_REQ, j.JOB_TITLE, j.JOB_SKILL, j.JOB_POSITION, j.JOB_CLEAR, j.JOB_LOCATE, j.JOB_DESCRIPTION, j.JOB_SKILLS_MAN, j.JOB_SKILLS_DESIRED, j.JOB_CERT_REQUIRE, j.JOB_CONUS, j.JOB_OCONUS,j.JOB_HOURSPERYEAR,j.JOB_SCHEDULE_COMMENT, j.JOB_MISSION_CRITICAL," +
			" j.JOB_NIGHT_WORK, j.JOB_LOCAL_TRAVEL, j.JOB_PAGER, j.JOB_PAGER_DUTY_COMMENTS, j.JOB_WORK_HOLIDAY,j.JOB_WORK_WEEKEND,j.JOB_SHIFTWORK, j.JOB_WARZONE, j.JOB_COOP,j.JOB_STATUS_CHANGE, j.JOB_UPDATE_DATE,j.JOB_CLOSE_DATE, j.JOB_DATE, c.CONTRACT_TITLE, c.CONTRACT_ID, " +
			" c.CONTRACT_AGENCY, c.contract_DATE, c.CONTRACT_CLASS, c.Contract_Status ,c.nameuser, j.JOB_PRIORITY FROM " +
			MessengerConstants.MM_SCHEMA_NAME + ".jobs j " +
			" join " + MessengerConstants.MM_SCHEMA_NAME + ".contracts c " +
			" where j.contract_id = c.contract_id " +
			" and j.contract_id = " + passID, new ContractssJobsParameterizedRowMapper<ContractsJob>());
	
	System.out.println("I get here finally to see the id " + passID);
		
	return jobContractRecords;
}

@Override
public List<ContractRecord> getSelectedPOCInfo(Long passID) {
	log.info("Getting all POC based off current selected contract");
	log.info("Seeing it get back here " + passID);
	JdbcTemplate template = getJdbcTemplate();
	List<ContractRecord> newContractRecord = template.query("SELECT b.CONTRACT_ID, b.BUSINESSDEV_ID, b.BUSINESSDEV_FIRSTNAME, b.BUSINESSDEV_LASTNAME, "
			+ "b.BUSINESSDEV_EMAIL, c.CONTRACT_ID, c.CONTRACT_AGENCY, c.CONTRACT_DATE, c.CONTRACT_TITLE, c.CONTRACT_CLASS, c.NAMEUSER FROM " + 
	        MessengerConstants.MM_SCHEMA_NAME + ".businessdev b " +
	        " join " + MessengerConstants.MM_SCHEMA_NAME + ".contracts c " +
	        " where b.CONTRACT_ID = c.CONTRACT_ID " +
	  		" and b.CONTRACT_ID = " + passID,
			new BusConParameterizedRowMapper<ContractRecord>());
	
	System.out.print("This is the back side  finish number : " + passID);
	
	for(ContractRecord ct : newContractRecord){
		System.out.println("This is the business id :" + ct.getBuisnessdevID());
		System.out.println("This is the first name : " + ct.getBuisnessdevFirstname());
	}
	return newContractRecord;
}

private class BusConParameterizedRowMapper<T> implements ParameterizedRowMapper<ContractRecord> {

	public ContractRecord mapRow(ResultSet resultSet, int row) throws SQLException {
		ContractRecord contractRecord = new ContractRecord();
		
		contractRecord.setBuisnessdevID(resultSet.getLong("BUSINESSDEV_ID"));
		contractRecord.setBuisnessdevFirstname(resultSet.getString("BUSINESSDEV_FIRSTNAME"));
		contractRecord.setBuisnessdevLastname(resultSet.getString("BUSINESSDEV_LASTNAME"));
		contractRecord.setBuisnessdevEmail(resultSet.getString("BUSINESSDEV_EMAIL"));
		
		contractRecord.setContractID(resultSet.getLong("CONTRACT_ID"));
		contractRecord.setContractDate(resultSet.getDate("CONTRACT_DATE"));
		contractRecord.setContractTitle(resultSet.getString("CONTRACT_TITLE"));
		contractRecord.setContractAgency(resultSet.getString("CONTRACT_AGENCY"));
		contractRecord.setContractClass(resultSet.getString("CONTRACT_CLASS"));
		contractRecord.setNameuser(resultSet.getString("NAMEUSER"));
		
		
		return contractRecord;

	}
}	

@Override
public List<CandidateInfo> getAllCandidateInfoAll() {
	log.info("Getting all CandidateInfo");
	JdbcTemplate template = getJdbcTemplate();
	List<CandidateInfo> newCandidateInfo = template.query(SQL_GET_MAX_ID_CANDINFO_QUERY, 
			new CandInfoParameterizedRowMapper<CandidateInfo>());
	
	return newCandidateInfo;
}

//final static private String SQL_Update_Blob_Candidate = " Update " + MessengerConstants.MM_SCHEMA_NAME + ".candinfo SET resume " +  + " where candinfo_id = ";

@Override
public void svUpdateResumeBlob(final CandidateInfo cnd) {
		log.info("Im getting called to Upload a candidates Resume");	
	
	 CandidateInfo ciff = cnd;
	 System.out.println("This is what id need to see " + ciff.getCandinfo_id());
	 System.out.println("This is what i need to see " + ciff.getResFile());
	 
	JdbcTemplate template = getJdbcTemplate();
	template.update(" Update " + MessengerConstants.MM_SCHEMA_NAME + ".candinfo SET resume = ?  where candinfo_id = " + ciff.getCandinfo_id(), new Object[] {
			
			ciff.getResFile()
});

	System.out.println("Resume has been saved!!!!!!");
}

@Override
public void svJobTemplateBlob(final JobRecord jobR) {
	// TODO Auto-generated method stub
	 JobRecord rec = jobR;
	 System.out.println("This is what id need to see " + rec.getJobID());
	 System.out.println("This is what i need to see " + rec.getResFile());
	 
	JdbcTemplate template = getJdbcTemplate();
	template.update(" Update " + MessengerConstants.MM_SCHEMA_NAME + ".jobs SET jobTemplate = ?  where JOB_ID = " + rec.getJobID(), new Object[] {
			
		rec.getResFile()
});

	System.out.println("Job Template Being Saved");
}










@Override
public List<CandidateInfo> getAllCandidateInfoTask() {
	log.info("Getting all CandidateInfo");
	JdbcTemplate template = getJdbcTemplate();
	List<CandidateInfo> newCandidateInfo = template.query(SQL_GET_MAX_ID_CANDINFO_QUERY, 
			new CandInfoParameterizedRowMapper<CandidateInfo>());
	
	return newCandidateInfo;
}


final static private String SQL_INSERT_TASKS =
" INSERT INTO " + 
MessengerConstants.MM_SCHEMA_NAME + ".tasks " +
		" (firstname, lastname, jobTitle, jobDescription, adminComments ,nameuser, candinfoResume, jobTemplate , enabled, dateCompleted, dueDate, urgent) " +
		" VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";


@Override
public void saveTaskRecord(TaskRecord taskRecord) {
	log.info("this is the Task data being saved ");
	JdbcTemplate template = getJdbcTemplate();
	template.update(SQL_INSERT_TASKS, new Object[] {
			taskRecord.getCandidateFirstName(),
			taskRecord.getCandidateLastName(),
			taskRecord.getJobTitle(),
			taskRecord.getJobDescription(),
			taskRecord.getAdminComments(),
			taskRecord.getNameuser(),
			taskRecord.getcandinfoResume(),
			taskRecord.getJobTemplate(),
			taskRecord.enabled = (1),
			taskRecord.getDateCompleted(),
			taskRecord.getDueDate(),
			taskRecord.getUrgent()});
		}


@Override
public List<TaskRecord> getAllTaskInfoPosted() {
	log.info("This is All of the Task Info Assigned ");
	JdbcTemplate template = getJdbcTemplate();
	List<TaskRecord> newTaskRecord = template.query(SQL_GET_ALL_TASKINFO, 
			new TaskParameterizedRowMapper<TaskRecord>());
	
	return newTaskRecord;
}


@Override
public List<TaskRecord> getAllCompletedTasks() {
	log.info("These are all the tasks that have been completed");
	JdbcTemplate template = getJdbcTemplate();
	List<TaskRecord> newTaskRecord = template.query(SQL_GET_ALL_COMPLETEDTASKINFO, 
			new TaskParameterizedRowMapper<TaskRecord>());
	
	return newTaskRecord;
}


final static private String SQL_INSERT_COMPLETEDTASK =
" INSERT INTO " + 
MessengerConstants.MM_SCHEMA_NAME + ".Completed_Tasks " +
		" (firstname, lastname, jobTitle, nameuser, dateCompleted) " +
		" VALUES (?,?,?,?,?)";


@Override
public void saveCompletedTask(TaskRecord taskRecord) {
	log.info("This is a Task Being Completed ");
	JdbcTemplate template = getJdbcTemplate();
	template.update(SQL_INSERT_COMPLETEDTASK, new Object[] {
		
 			
			taskRecord.getCandidateFirstName(),
			taskRecord.getCandidateLastName(),
			taskRecord.getJobTitle(),
			taskRecord.getNameuser(),
			new Date()});
			
}

@Override
public void setTaskCompleted(TaskRecord selectedTaskRecord) {
	log.info("Mark Task as Complete");
	log.info("This is the id number " + selectedTaskRecord.gettask_ID());
	JdbcTemplate template = getJdbcTemplate();
	
	template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME + ".tasks SET dateCompleted = UTC_TIMESTAMP() WHERE task_ID =" + selectedTaskRecord.gettask_ID());
	
	template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME  +".tasks SET enabled = 0 WHERE task_ID = " + selectedTaskRecord.gettask_ID());
	
}



@Override
public void cancelTask(TaskRecord selectedTaskRecord) {
	log.info("Cancel This Task");
	log.info("This is the id number " + selectedTaskRecord.gettask_ID());
	
	log.info("This is the comments:" + selectedTaskRecord.getCancelComments());
	
	JdbcTemplate template = getJdbcTemplate();
	
  template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME  +".tasks  SET enabled = 'Canceled', cancelComments = ?, dateCanceled = ? WHERE task_ID = " + selectedTaskRecord.gettask_ID(), new Object[] {
 
	  selectedTaskRecord.getCancelComments(),
	  selectedTaskRecord.getDateCanceled()
	  
  });
	
	//template.update("UPDATE " + MessengerConstants.MM_SCHEMA_NAME  +".tasks  SET enabled = 'Canceled' WHERE task_ID = " + selectedTaskRecord.gettask_ID());

}

@Override
public List<TaskRecord> getAllCanceledTasks() {
	log.info("These are all the tasks that have been canceled");
	JdbcTemplate template = getJdbcTemplate();
	List<TaskRecord> newTaskRecord = template.query(SQL_GET_ALL_CANCLEDTASKS, 
			new TaskParameterizedRowMapper<TaskRecord>());
	
	return newTaskRecord;
}

@Override
public void svCompletedTemplate(final TaskRecord comT) {
	// TODO Auto-generated method stub
		TaskRecord tk = comT;
		 System.out.println("This is what id need to see " + tk.gettask_ID());
		 System.out.println("This is what i need to see " + tk.getResFile());
		 
		JdbcTemplate template = getJdbcTemplate();
		template.update(" Update " + MessengerConstants.MM_SCHEMA_NAME + ".tasks SET completedTemplate = ?  where task_ID = " + tk.gettask_ID(), new Object[] {
				
	
			
			
			tk.getResFile()
	});

		System.out.println("Job Template Being Saved");
	}


final static private String SQL_INSERT_INTO_COMMENTS =
" INSERT INTO " + 
MessengerConstants.MM_SCHEMA_NAME + ".comments " +
		" (idComments, commentString, task_ID, createUpdated, idnameuser) " +
		" VALUES (?,?,?,?,?)";


@Override
public void saveComment(TaskRecord selectedTaskRecord) {
	log.info("this is the Comment data being saved ");
	JdbcTemplate template = getJdbcTemplate();
	template.update(SQL_INSERT_INTO_COMMENTS, new Object[] {
			selectedTaskRecord.getIdComments(),
			selectedTaskRecord.getCommentString(),
			selectedTaskRecord.gettask_ID(),
			selectedTaskRecord.getCreateUpdated(),
			selectedTaskRecord.getNameuser()});
}

@Override
public List<SaveComment> getAllSelectedComments() {
	log.info("Getting all comments ");
	JdbcTemplate template = getJdbcTemplate();
	List<SaveComment> newSaveComment = template.query(SQL_GET_ALL_SELECTED_COMMENTS, 
			new CommentParameterizedRowMapper<SaveComment>());
	
	return newSaveComment;
}




}//end class

	
	
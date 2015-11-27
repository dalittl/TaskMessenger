/**
 * 
 */
package com.acnc.mm.dao.db;

import java.util.List;






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


/**
 * @author kalittl
 *
 */
public interface MessengerDAO {
	

	public List<JobRecord> getJobs();
	
	//Existing
	public List<JobRecord> getJobs2();
	
    //Populate Candidate information Table
	public void svCandidate(CandidateInfo candidateInfo);

	public void svJob(JobRecord jobRecord);

	public void svEmailQueue(List<EmailRepo> allAddedEmailsInfo);

	public List<EmailRepo> getEmailRepo();

	public void updateCandidate(CandidateInfo updateCandidate);

	public void deleteCandidateinfo(CandidateInfo updateCandidateinfo);

	public void updateJobinfo(JobRecord jobRecord);

	public void deleteJobinfo(JobRecord jobRecord);

	public void svContract(ContractRecord ContractRecord);

	public List<ContractRecord> getContracts();

	public List<BusinessRecord> getBusiness();

	public void svBusiness(BusinessRecord businessRecord);

	public void updateBusinessinfo(BusinessRecord updateBusiness);

	public void deleteBusinessinfo(BusinessRecord businessRecord);

	public void deleteContractinfo(ContractRecord contractRecord);

	public void updateContractinfo(ContractRecord updateContract);

	public List<EmailRepo2> getEmailRepo2();

	public void svEmailQueue2(List<EmailRepo2> allAddedEmailsInfo2);
	//Resume save wizard
	public void svCandidateInfo(CandidateInfo candidateInfo);

	public void svCandidateInfo(EmployResume employResume);

	public void svCandidateInfo(EducationResume educationResume);

	public void svCertResume(CertResume certResume);

	public List<CandidateInfo> getAllCandidateInfo();
	
	public List<TaskRecord> getAllCompletedTasks();

    public void saveEmployInfo(List<EmployResume> mainER);

	public void saveCertificationInfo(List<CertResume> cert);

	public void saveEducationInfo(List<EducationResume> empEdu);
	//Resume save wizard
	public void saveCandidateInfo(List<CandidateInfo> cnd);

	public List<EducationResume> getAllEducation();

	public List<EmployResume> getAllEmploy();

	public List<CertResume> getAllCert();

	public void getALLCert(CertResume certResume);

	public List<CandidateInfo> getAllQueryRecord();

	public void updateCandidateInfo(CandidateInfo candidateInfo);

	public void updateEducationResume(EducationResume educationResume);

	public void updateCertResume(CertResume certResume);

	public void updateEmploynResume(EmployResume employResume);

	public void candID(long passID);

	public List<CertResume> getSelectedCertsInfomation(Long passID);

	public List<EducationResume> getSelectedEducationInformation(Long passID);

	public List<EmployResume> getSelectedEmployInfomation(Long passID);

	public void svUpdatedEmploy(EmployResume employResume);

	public void svUpdatedEducation(EducationResume educationResume);

	public void svUpdatedCert(CertResume certResume);

	public void svUpdatedCand(CandidateInfo candidateInfo);
   
	public void svFlatFile(List<FlatFile> flat);
	//Pass the ID to the Category
	public void svPassID(Category category);
	// Save the list of the business file
	public void svBusFile(List<BusinessRecord> br);
	// Save the list of the Contract file
	public void svContFIle(List<ContractRecord> cr);
	
	public void svNewuser(RegisterCandidate regcan);
	// Save the job file
	public void svJobFile(List<JobRecord> jr);
	public void deleteCertResume(CertResume certResume);
	//Get all High Priority Jobs
	public List<ContractsJob> getAllJobPriority();
	//Get all buisiness contracts realted to urgent jobs
	public List<ContractRecord> getAllBuisnessContracts();
	
	public List<JobRecord> getAllContractTitles();

	public List<CandidateInfo> getAllCandidateInfo2();
    // Get all Selected jobs
	public List<ContractsJob> getSelectedJobInfo(Long passID);

	public List<CandidateInfo> getAllCandidateInfoAll();
	
	// update blob to resume
	public void svUpdateResumeBlob(CandidateInfo cnd);
    // Get POC info realted to contract 
	public List<ContractRecord> getSelectedPOCInfo(Long passID);
	
	/*public List<CandidateInfo> getAllCreatedDates();*/

	public List<CandidateInfo> getAllCandidateInfoTask();

	public void saveTaskRecord(TaskRecord taskRecord);
	
	public List<TaskRecord> getAllTaskInfoPosted();


	public void saveCompletedTask(TaskRecord taskRecord);

	public void setTaskCompleted(TaskRecord selectedTaskRecord);

	public void svJobTemplateBlob(JobRecord jobR);

	public void cancelTask(TaskRecord selectedTaskRecord);
	
	public List<TaskRecord> getAllCanceledTasks();

	public void svCompletedTemplate(TaskRecord comT);

	public void saveComment(TaskRecord selectedTaskRecord);

	public List<SaveComment> getAllSelectedComments();
	
}

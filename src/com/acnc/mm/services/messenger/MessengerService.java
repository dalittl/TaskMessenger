package com.acnc.mm.services.messenger;


import java.util.List;

import com.acnc.mm.domain.messenger.BusinessRecord;
import com.acnc.mm.domain.messenger.CandidateInfo;
import com.acnc.mm.domain.messenger.Category;
import com.acnc.mm.domain.messenger.CertResume;
import com.acnc.mm.domain.messenger.ContractRecord;
import com.acnc.mm.domain.messenger.ContractsJob;
import com.acnc.mm.domain.messenger.DeleteCandidate;
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
 * @author Kunta L.
 *
 */
public interface MessengerService  {
	
	public List<JobRecord> getAllJobs();
    //Populate candidate table
	public void saveCandidate(CandidateInfo candidateInfo);

	public void saveJob(JobRecord jobRecord);

	public void saveEmailQueue(List<EmailRepo> allAddedEmailsInfo);

	public List<EmailRepo> getAllEmailRepos();

   public void deleteCandidate(CandidateInfo updateCandidateinfo);

	public void updateJob(JobRecord jobRecord);

	public void deleteJob(JobRecord jobRecord);

	public void saveContract(ContractRecord contractRecord);

	public List<ContractRecord> getAllContracts();

	public List<BusinessRecord> getAllBusiness();
	
	public void saveBusiness(BusinessRecord businessRecord);

	public void updateBusiness(BusinessRecord updateBusiness);

	public void deleteBusiness(BusinessRecord businessRecord);

	public void deleteContract(ContractRecord contractRecord);

	public void updateContract(ContractRecord updateContract);

	public List<EmailRepo2> getAllEmailRepos2();

	public void saveEmailQueue2(List<EmailRepo2> allAddedEmailsInfo2);
	
	public void saveCandidateInfo(EmployResume employResume);

	public void saveCandidateInfo(EducationResume educationResume);

	public void saveCertResume(CertResume certResume);

	public List<CandidateInfo> getAllCandidateInfo();

	public void saveEmployInfo(List<EmployResume> mainER);

	public void saveCertificationInfo(List<CertResume> cert);

	public void saveEducationInfo(List<EducationResume> empEdu);
	//Resume save candidate wizard
	public void saveCandidateInfo(List<CandidateInfo> cnd);

	public void svcandidateinfo(CandidateInfo candidateInfo);

	public void sveducationinfo(EducationResume educationResume);

	public void svcertresume(CertResume certResume);

	public void svemployresume(EmployResume employResume);

	public List<EducationResume> getAllEducation();

	public List<EmployResume> getAllEmploy();

	public List<CertResume> getAllCert();

	public void getAllCert(CertResume certResume);

	public List<CandidateInfo> getAllQueryRecord();

	public void updateCandidateInfo(CandidateInfo candidateInfo);

	public void updateEducationResume(EducationResume educationResume);

	public void updateCertResume(CertResume certResume);

	public void updateEmployResume(EmployResume employResume);

	public void candID(long passID);

	public List<CertResume> getSelectedCertsInfo(Long passID);

	public List<EducationResume> getSelectedEducationInfo(Long passID);

	public List<EmployResume> getSelectedEmployInfo(Long passID);

	public void updateEmploy(EmployResume employResume);

	public void updateEducation(EducationResume educationResume);

	public void updateCert(CertResume certResume);

	public void upDateCandidate(CandidateInfo candidateInfo);
  
	public void saveFlatFile(List<FlatFile> flat);
	//Pass the ID to match the Category table
	public void passID(Category category);
	//Save Excel sheet info Business Record
	public void saveBusinessFile(List<BusinessRecord> br);
	//Save Excel sheet info Contract File
	public void saveContractFile(List<ContractRecord> cr);
	//Register candidate being saved
	public void newUser(RegisterCandidate regcan);
	//Save Job List
	public void saveJob(List<JobRecord> jr);
	
	public void deleteCertResume(CertResume certResume);
	
	public void setTask(TaskRecord selectedTaskRecord);
	
	public void CancelTask(TaskRecord selectedTaskRecord);
	
	public void deleteCandidateInfo1(DeleteCandidate deleteCandidateInfo1);
	public void updateStatus();
	//Get All High Priority Jobs
	public List<ContractsJob> getAllJobPriority();
	//Get All Buisness Contracts
	public List<ContractRecord> getAllBuisnessContract();
	
	public List<JobRecord> getAllContractTitles();
	//Get All Created Dates For Candidates
	/*public List<CandidateInfo> getAllCreatedDates();*/
	
	//Existing Jobs
	public List<JobRecord> getAllJobs2();
	
	//Existing Candidates
	public List<CandidateInfo> getAllCandidateInfo2();
	
	//TASK
	public List<CandidateInfo> getAllCandidateInfoTask();
	
	//Get all selected jobs from Selectec contracts
	public List<ContractsJob> getSelectedJobInfo(Long passID);
	public List<CandidateInfo> getAllCandidateInfoAll();
	// Updating CLob to resume object
	public void upDateCandResume(CandidateInfo cnd);
	//Get Poc info
	public List<ContractRecord> getSelectedPOCInfo(Long passID);
	
	public void saveTaskRecord(TaskRecord taskRecord);
	
	public void saveComment(TaskRecord selectedTaskRecord);
	
	public void saveCompletedTask(TaskRecord taskRecord);
	
	public List<TaskRecord> getAllTaskInfoPosted();
	
	
	public List<TaskRecord> getAllCompletedTask();
	
	public void svJobTemplateBlob(JobRecord jobR);
		
	public List<TaskRecord> getAllCanceledTask();
	
	public void UploadCompletedTemplate(TaskRecord comT);
	
	public List<SaveComment> getAllSelectedComments();
}
	
	

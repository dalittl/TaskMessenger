package com.acnc.mm.services.messenger;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.acnc.mm.dao.db.MessengerDAO;
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
 * @author Kunta Little
 *
 */
public class MessengerServiceImpl implements MessengerService, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6872165936647733610L;

	private static Logger log = Logger.getLogger(MessengerServiceImpl.class.getName());

	private MessengerDAO messengerDAO;

	public MessengerServiceImpl() {
		log.fine("Initializing Messenger Service");
	}
	
	
	public List<JobRecord> getAllJobs(){
		log.info("getting all jobs ");
		return messengerDAO.getJobs();
	}

	public MessengerDAO getMessengerDAO() {
		return messengerDAO;
	}


	public void setMessengerDAO(MessengerDAO messengerDAO) {
		this.messengerDAO = messengerDAO;
	}

    //Populate Candidate Table
	public void saveCandidate(CandidateInfo candidateInfo) {
		messengerDAO.svCandidate(candidateInfo);
	}


	public void saveJob(JobRecord jobRecord) {
		messengerDAO.svJob(jobRecord);
		
	}


	public List<EmailRepo> getAllEmailRepos() {
		log.info("getting all email repoed ");
		return messengerDAO.getEmailRepo();
	}


	public void saveEmailQueue(List<EmailRepo> allAddedEmailsInfo) {
		messengerDAO.svEmailQueue(allAddedEmailsInfo);
	}


	@Override
	public void deleteCandidate(CandidateInfo updateCandidateinfo) {
		log.info("Deleting candidate info");
		log.info("DO We see the id here: " + updateCandidateinfo.getCandinfo_id());
		messengerDAO.deleteCandidateinfo(updateCandidateinfo);
		
	}


	
	@Override
	public void updateJob(JobRecord jobRecord) {
		log.info("Updating job selected");
		messengerDAO.updateJobinfo(jobRecord);
		
	}


	@Override
	public void deleteJob(JobRecord jobRecord) {
		log.info("Deleting Job info");
		messengerDAO.deleteJobinfo(jobRecord);
				
	}


	@Override
	public void saveContract(ContractRecord contractRecord) {
		log.info("Save Contract info");
		messengerDAO.svContract(contractRecord);
		
	}


	@Override
	public List<ContractRecord> getAllContracts() {
		log.info("getting all contracts ");
		return messengerDAO.getContracts();
	}


	@Override
	public List<BusinessRecord> getAllBusiness() {
		log.info("getting all Business ");
		return messengerDAO.getBusiness();
	}


	@Override
	public void saveBusiness(BusinessRecord businessRecord) {
		log.info("Save Business info");
		messengerDAO.svBusiness(businessRecord);
		
	}

	@Override
	public void saveTaskRecord(TaskRecord taskRecord){
		log.info("Save Task Data");
		messengerDAO.saveTaskRecord(taskRecord);
	}
	

	public void updateBusiness(BusinessRecord updateBusiness) {
		log.info("Updating Business selected");
		messengerDAO.updateBusinessinfo(updateBusiness);
		
	}


	
	public void deleteBusiness(BusinessRecord businessRecord) {
		log.info("Deleting Business info");
		messengerDAO.deleteBusinessinfo(businessRecord);
		
	}


	
	public void deleteContract(ContractRecord contractRecord) {
		log.info("Deleting Contract info");
		messengerDAO.deleteContractinfo(contractRecord);
		
	}


	@Override
	public void updateContract(ContractRecord updateContract) {
		log.info("Updating Contract selected");
		messengerDAO.updateContractinfo(updateContract);
		
	}


	
	public List<EmailRepo2> getAllEmailRepos2() {
		log.info("getting all email repoed 2 ");
		return messengerDAO.getEmailRepo2();
	}


	
	public void saveEmailQueue2(List<EmailRepo2> allAddedEmailsInfo2) {
		messengerDAO.svEmailQueue2(allAddedEmailsInfo2);
		
	}

/*	//Resume save wizard
	@Override
	public void saveCandidateInfo(CandidateInfo candidateInfo) {
		log.info("Save Resume candidate info");
		messengerDAO.svCandidateInfo(candidateInfo);
		
	}*/


	@Override
	public void saveCandidateInfo(EmployResume employResume) {
		log.info("Save Employ candidate info");
		messengerDAO.svCandidateInfo(employResume);
		
	}


	@Override
	public void saveCandidateInfo(EducationResume educationResume) {
		log.info("Save education candidate info");
		messengerDAO.svCandidateInfo(educationResume);
		
	}


	@Override
	public void saveCertResume(CertResume certResume) {
		log.info("Save Cert info");
		messengerDAO.svCertResume(certResume);
		
	}


	
/*	public void MaxID(CandidateInfo candidateInfo) {
		log.info("Max ID retrieve");
		messengerDAO.MaxID(candidateInfo);
		
		
	}*/


	@Override
	public List<CandidateInfo> getAllCandidateInfo() {
		log.info("get all of candaite info step one");
		return messengerDAO.getAllCandidateInfo();
	}


	
	@Override
	public void saveEmployInfo(List<EmployResume> mainER) {
		messengerDAO.saveEmployInfo(mainER);
	
	}


	@Override
	public void saveCertificationInfo(List<CertResume> cert) {
		messengerDAO.saveCertificationInfo(cert);
		
	}


	@Override
	public void saveEducationInfo(List<EducationResume> empEdu) {
		messengerDAO.saveEducationInfo(empEdu);
		
	}

	//Resume save wizard
	@Override
	public void saveCandidateInfo(List<CandidateInfo> cnd) {
		messengerDAO.saveCandidateInfo(cnd);
	}


	@Override
	public void svcandidateinfo(CandidateInfo candidateInfo) {
		messengerDAO.svCandidateInfo(candidateInfo);
		
	}


	@Override
	public void sveducationinfo(EducationResume educationResume) {
		messengerDAO.svCandidateInfo(educationResume);
		
	}


	@Override
	public void svcertresume(CertResume certResume) {
		messengerDAO.svCertResume(certResume);
		
	}


	@Override
	public void svemployresume(EmployResume employResume) {
		messengerDAO.svCandidateInfo(employResume);
		
	}


	@Override
	public List<EducationResume> getAllEducation() {		
		return messengerDAO.getAllEducation();
	}


	@Override
	public List<EmployResume> getAllEmploy() {		
		return messengerDAO.getAllEmploy();
	}


	@Override
	public List<CertResume> getAllCert() {		
		return messengerDAO.getAllCert();
	}


	@Override
	public void getAllCert(CertResume certResume) {
		  messengerDAO.getALLCert(certResume);
		
	}


	@Override
	public List<CandidateInfo> getAllQueryRecord() {		
		return messengerDAO.getAllQueryRecord();
	}


	@Override
	public void updateCandidateInfo(CandidateInfo candidateInfo) {
		log.info("Updating Candidated selected");
		messengerDAO.updateCandidateInfo(candidateInfo);
		
	}


	@Override
	public void updateEducationResume(EducationResume educationResume) {
		log.info("Updating Education Resume");
		messengerDAO.updateEducationResume(educationResume);
		
	}


	@Override
	public void updateCertResume(CertResume certResume) {
		log.info("Updating Cert Resume");
		messengerDAO.updateCertResume(certResume);
		
	}


	@Override
	public void updateEmployResume(EmployResume employResume) {
		log.info("Updating Employ Resume");
		messengerDAO.updateEmploynResume(employResume);
	}


	@Override
	public void candID(long passID) {
		messengerDAO.candID(passID);
		
		
	}


	@Override
	public List<CertResume> getSelectedCertsInfo(Long passID) {		
		return messengerDAO.getSelectedCertsInfomation(passID);
	}


	@Override
	public List<EducationResume> getSelectedEducationInfo(Long passID) {		
		return messengerDAO.getSelectedEducationInformation(passID);
	}


	@Override
	public List<EmployResume> getSelectedEmployInfo(Long passID) {		
		return messengerDAO.getSelectedEmployInfomation(passID);
	}


	@Override
	public void updateEmploy(EmployResume employResume) {
		messengerDAO.svUpdatedEmploy(employResume);
		
	}


	@Override
	public void updateEducation(EducationResume educationResume) {
		messengerDAO.svUpdatedEducation(educationResume);
		
	}


	@Override
	public void updateCert(CertResume certResume) {
		messengerDAO.svUpdatedCert(certResume);
		
	}


	@Override
	public void upDateCandidate(CandidateInfo candidateInfo) {
		messengerDAO.svUpdatedCand(candidateInfo);
		
	}

   

	@Override
	public void saveFlatFile(List<FlatFile> flat) {
		messengerDAO.svFlatFile(flat);
		
	}


	@Override
	public void passID(Category category) {
		messengerDAO.svPassID(category);
		
	}


	@Override
	public void saveBusinessFile(List<BusinessRecord> br) {
		messengerDAO.svBusFile(br);
		
	}


	@Override
	public void saveContractFile(List<ContractRecord> cr) {
		messengerDAO.svContFIle(cr);
		
	}


	@Override
	public void newUser(RegisterCandidate regcan)
	{
	messengerDAO.svNewuser(regcan);
		
	}


	@Override
	public void saveJob(List<JobRecord> jr) {
		messengerDAO.svJobFile(jr);
		
	}


	@Override
	public void deleteCertResume(CertResume certResume) {
		log.info("Deleting Cert Resume info");
		messengerDAO.deleteCertResume(certResume);
		
	}


	@Override
	public void deleteCandidateInfo1(DeleteCandidate deleteCandidateInfo1) {
		// TODO Auto-generated method stub
		
	}


	public void updateStatus() {
		System.out.println(new Date() + " Update status Service invoked");

	}


	@Override
	public List<ContractsJob> getAllJobPriority() {
	  log.info("Getting High Priority Jobs");
		return messengerDAO.getAllJobPriority();
	}


	@Override
	public List<ContractRecord> getAllBuisnessContract() {
		log.info("Getting Buiness Contact related to urgent jobs");
		return messengerDAO.getAllBuisnessContracts();
	}


	@Override
	public List<JobRecord> getAllContractTitles() {
		// TODO Auto-generated method stub
		return messengerDAO.getAllContractTitles();
	}


	@Override
	public List<JobRecord> getAllJobs2() {
		
		return messengerDAO.getJobs2();
	}


	@Override
	public List<CandidateInfo> getAllCandidateInfo2() {
		
		return messengerDAO.getAllCandidateInfo2();
	}

	@Override
	public List<CandidateInfo> getAllCandidateInfoAll() {
		// TODO Auto-generated method stub
		return messengerDAO.getAllCandidateInfoAll();
	}

  // update resume blob to candidate table
	@Override
	public void upDateCandResume(CandidateInfo cnd) {
		
		 messengerDAO.svUpdateResumeBlob(cnd);
		
	}

 // Get business poc for contract html
	@Override
	public List<ContractRecord> getSelectedPOCInfo(Long passID) {
		// TODO Auto-generated method stub
		log.info("Getting all poc associated with the current contracts");
		return messengerDAO.getSelectedPOCInfo(passID);
	}
	
	@Override
	public List<ContractsJob> getSelectedJobInfo(Long passID) {
		log.info("Getting all jobs associated with the current contracts");
		return messengerDAO.getSelectedJobInfo(passID);
	}


	@Override
	public List<CandidateInfo> getAllCandidateInfoTask() {
		// TODO Auto-generated method stub
		return messengerDAO.getAllCandidateInfoTask();
	}


	@Override
	public List<TaskRecord> getAllTaskInfoPosted() {
		// TODO Auto-generated method stub
		return messengerDAO.getAllTaskInfoPosted();
	}



	@Override
	public List<TaskRecord> getAllCompletedTask() {
		// TODO Auto-generated method stub
		return messengerDAO.getAllCompletedTasks();
	}


	@Override
	public void saveCompletedTask(TaskRecord taskRecord) {
		log.info("Saving Completed Task");
		messengerDAO.saveCompletedTask(taskRecord);
	}


	@Override
	public void setTask(TaskRecord selectedTaskRecord) {
		log.info("Set Task Completed");
		messengerDAO.setTaskCompleted(selectedTaskRecord);
	
	}


	@Override
	public void svJobTemplateBlob(JobRecord jobR) {
		
		messengerDAO.svJobTemplateBlob(jobR);
	}


	@Override
	public void CancelTask(TaskRecord selectedTaskRecord) {
	
		messengerDAO.cancelTask(selectedTaskRecord);
	}


	@Override
	public List<TaskRecord> getAllCanceledTask() {
		// TODO Auto-generated method stub
		return messengerDAO.getAllCanceledTasks();
	}


	@Override
	public void UploadCompletedTemplate(TaskRecord comT) {
		// TODO Auto-generated method stub
		messengerDAO.svCompletedTemplate(comT);
	}


	@Override
	public void saveComment(TaskRecord selectedTaskRecord){
		log.info("Save Task Comments");
		messengerDAO.saveComment(selectedTaskRecord);
	}


	@Override
	public List<SaveComment> getAllSelectedComments() {
		// TODO Auto-generated method stub
		return messengerDAO.getAllSelectedComments();
	}		
	

}
	

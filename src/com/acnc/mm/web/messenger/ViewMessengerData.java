package com.acnc.mm.web.messenger;



import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.sql.rowset.serial.SerialException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.acnc.mm.controller.LoginBean;
import com.acnc.mm.domain.messenger.BusinessRecord;
import com.acnc.mm.domain.messenger.CandidateInfo;
import com.acnc.mm.domain.messenger.Category;
import com.acnc.mm.domain.messenger.CertResume;
import com.acnc.mm.domain.messenger.ContractRecord;
import com.acnc.mm.domain.messenger.ContractsJob;
import com.acnc.mm.domain.messenger.DeleteBusiness;
import com.acnc.mm.domain.messenger.DeleteCandidate;
import com.acnc.mm.domain.messenger.DeleteCertResume;
import com.acnc.mm.domain.messenger.DeleteContract;
import com.acnc.mm.domain.messenger.EducationResume;
import com.acnc.mm.domain.messenger.EmailRepo;
import com.acnc.mm.domain.messenger.EmailRepo2;
import com.acnc.mm.domain.messenger.EmployResume;
import com.acnc.mm.domain.messenger.FlatFile;
import com.acnc.mm.domain.messenger.JobRecord;
import com.acnc.mm.domain.messenger.RegisterCandidate;
import com.acnc.mm.domain.messenger.SaveComment;
import com.acnc.mm.domain.messenger.TaskRecord;
import com.acnc.mm.services.mail.MailService;
import com.acnc.mm.services.messenger.MessengerService;
import com.acnc.mm.util.MessengerConstants;
import com.acnc.mm.web.BaseUIBean;


public class ViewMessengerData<updateCandidate>  extends BaseUIBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1865449671609587246L;

	private static Logger log = Logger.getLogger(ViewMessengerData.class.getName());
	
	private int currentLevel = 1;  

	private boolean skip;  
	
	private int number = 0;	
	
	private String username;
	
	private String nameuser;
	
	private Date dateCompleted;
	
	private Date dateCanceled;
	
	private Date createUpdated;
	
	private StreamedContent file;
	
	private byte[] resFile;
	
	private String candinfoFile;
	
	private String regFirstName;
	
	private String regusername;
	
	private String regpassword;
	
	private String regLastName;
	
	private String regEmail;
	
	private Date regmoddate;	
	
	private Date regstartdate;
	
	private String userId;
	
	private String password;
	
	private String newNameFile;
	
	private String fNameFile;
	
	private String candinfoFirstName;
	
    private String candinfoLastName;
	
    private String candinfoMiddle;
	
    private String candinfoEmail;
    
    private String candinfoNumber;
	
    private String candinfoQuestone;
	
    private String candinfoQuesttwo;
	
    private String candinfoQuesthree; 
	
    private String candinfoQuestfour;
	
	private String candinfoQuestfive;
	
	private String candinfoSkills;
	
	private String candinfoClearance;
	
	private String candinfoStatus;
	
	private Long candinfo_id;
	
	public Long edID;
	
	private String degree;
	
	private String schoolName;
	
	private String major;
	
	private Date compYear;
	
	public Long cerID;
	
	private String certName;
	
	private Date certYear;
	
	public Long employID;
	
    private String compName;
    
	private String title;
	
	private byte[] clobFile;
	
	private String projectRole;
	
	private String projDescpt; 
	
	private Date fromMonth;
	
	private String fromYear;
	
	private String toMonth;
	
	private Date toYear;
	
	private Date projfromMonth;
	
	private String projfromYear;
	
	private String projtoMonth;
	
	private Date projtoYear;
	
	private String catName;
	
	private String catDescrp;
	
	private Date cateDate;
	
	private Blob fileClob; 
	
	private String lilb;
	
	private String newName;
	
	private String oldName;
	
	private Date dateCreated;
	
    private boolean valueOne;
	  
    private boolean valueTwo;	
		
	private MessengerService messengerService;	
	
	private MailService mailService;
	
	//NEW
	private List<JobRecord> allJobPosted; 
	
	//Existing
	private List<JobRecord> allJobPostedExisting;
	
	private List<ContractsJob> allJobPriority;
	
	private List<ContractRecord> allBuisnessContracts;
	
	private List<EducationResume> allEducationPosted;
	
	private List<EducationResume> allSelectedEducation;
	
	private List<EmployResume> allSelectedEmploy;
	
	private List<EmployResume> allEmployPosted;
	
	private List<CertResume> allCertPosted;
	
	private List<CertResume> allSelectedCerts;
	
	private List<CertResume> selectedCerts;
	
	private List<CertResume> allQueryCertPosted;
	
	private List<CandidateInfo> allQueryRecord;
		
	private List<ContractRecord> allContractPosted;
	
	private List<ContractRecord> cr;
	
	private List<BusinessRecord> br;
	
	private List<JobRecord> jr;
	
	private List<EmailRepo> allEmailPosted;
	
	private List<EmailRepo2> allEmailPosted2;
	
	//ALL
	private List<CandidateInfo> allCandidateInfoPostedAll;
	//NEW
	private List<CandidateInfo> allCandidateInfoPosted;
	//Existing
	private List<CandidateInfo> allCandidateInfoPostedExisting;
	
	//TASK
	private List<CandidateInfo> allCandidateInfoPostedTask;
	
	private List<JobRecord> allContractTitles;
	
	private List<EducationResume> empEdu;
	
	private List<EmployResume> mainER;
	
	private List<CandidateInfo> cnd;
	
	private List<TaskRecord>comT;
	
	private List<JobRecord> jobR;
	
	private List<CertResume> cert;
	
	private List<Category> cat;
	
	private List<FlatFile> flat;
	
	private List<TaskRecord> allCompletedTask;
	
	private List<TaskRecord> allCanceledTask;
 	
	private JobRecord jobRecord;
	
	private RegisterCandidate  regcan;
		
	private Category category;
	
	private FlatFile flatfile;
	
	private CertResume certResume;
			
	private CandidateInfo  candidateInfo;
	
	private CandidateInfo updateCandidate;
	
	private CandidateInfo updateCandidateinfo;
	
	private BusinessRecord updateBusiness;
	
	private ContractRecord updateContract;	
	
	private DeleteCandidate deleteCandidate;
	
	
	private ContractRecord contractRecord;
	
	private BusinessRecord businessRecord;
	
	private DeleteContract deleteContract;
	
	private DeleteCertResume deleteCertResume;
	
	private DeleteBusiness deleteBusiness;
	
	private EducationResume educationResume;
	
	private EmployResume employResume;
	
	private java.sql.Blob candinfoResume;
	
	private String candidateFirstName;
	
	private String candidateLastName;

	private String candidateEmail;
	
	private String jobPriority;
	
   private Long jobID;
	
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
	
	private String contractTitle;
	
	private String contractAgency;
	
	private String contractClass;

	private Date contractDate;
	
	private String contractStatus;
	
    public String buisnessdevFirstname;
	
	public String buisnessdevLastname;
	
	public String buisnessdevPhonenumber;
	
	public String buisnessdevEmail;
	
	private String adminComments;
	
	private String cancelComments;
	
	private String commentString;
	
	private String businessFirstName;
	
	private String businessLastName;
	
	private String businessPhoneNumber;
	
	private	String businessEmail;
	
	//private boolean notAdmin = false;
	
	private Boolean showEmailPushOption = Boolean.FALSE;
	
	private Boolean showMainData = Boolean.TRUE;
	
	private Boolean showJobDataInput = Boolean.FALSE;

	private Boolean showCanDataInput = Boolean.FALSE;

	private Boolean showSelectedLst = Boolean.FALSE;
	
	private Boolean showSelectedLstDisable = Boolean.TRUE;
	
    private Boolean showSelectedLst2 = Boolean.FALSE;
	
	private Boolean showSelectedLstDisable2 = Boolean.TRUE;
	
	//private Boolean showSelectedListExisting = Boolean.FALSE;
	
	//private Boolean showSelectedListDisableExisting = Boolean.TRUE;

	private Boolean addingEducation = Boolean.FALSE;
	
	private Boolean showFileUpLoaderDisable = Boolean.FALSE;
	
	private Boolean showEditButtonDisable = Boolean.FALSE;
	
	private Boolean canFileUpLoaderDisable = Boolean.FALSE;
	
	private Boolean showContractUpLoaderDisable = Boolean.FALSE;
	
	private Boolean showResumeUpLoaderDisable = Boolean.FALSE;
	
	private Boolean showDatatableDisabledNew = Boolean.FALSE;
	
	private Boolean showDatatableDisabledExisting = Boolean.FALSE;
	
	private Boolean addingEmployment = Boolean.FALSE;
	
	private Boolean addingCertification = Boolean.FALSE;
	
	private Boolean addingResume = Boolean.FALSE;
	
	private BusinessRecord selectedBusiness;
	
	private JobRecord selectedJobs;
	
	private ContractRecord selectedContract;
	
	private CandidateInfo selectedCandidateInfo;
	
	private TaskRecord selectedTaskRecord;
	
	private SaveComment selectedComment;
	
	private CertResume selectedCertResume;
	
	private ContractsJob selectedJobInfo;
	
	private ContractRecord selectedContractInfo; 
	
	private List<TaskRecord> allSelectedTaskInfo;
	
	private List<CandidateInfo> allSelectedCandidateInfoAll;
	
	private List<CandidateInfo> allSelectedCandidateInfo;
	
	private List<CandidateInfo> allSelectedCandidateInfoExisting;
	
	private List<CandidateInfo> allSelectedCandidateInfoTask;
	
	private List<ContractsJob> allSelectedJobs;
	
	private List<ContractRecord> allSelectedPOC;
	
	private List<ContractsJob> allSelectedJobs2;
	
	private List<ContractRecord> allSelectedContracts;
	
	private List<BusinessRecord> allSelectedBusiness;
	
	private List<CandidateInfo> allCandidatesEmail;
	
	private List<EmailRepo> allAddedEmailsInfo;	
	
	private List<EmailRepo> allAddedEmailsInfoExisting;
	
	private List<EmailRepo2> allAddedEmailsInfo2;
	
	private List<TaskRecord> allTaskInfoPosted;
	
	private List<TaskRecord> allAddedTaskInfo;

	private List<SaveComment> allSelectedComments;
	
	private List<BusinessRecord> allBusinessPosted;

	private JobRecord selectedJob;
	
	private JobRecord selectedJobAll;
	
	private JobRecord selectedJobExisting;
	
	private EmailRepo2 selectedEmail2;
	
	private TaskRecord selectedTask;
	
	private LoginBean loginbean;
		
	private ContractRecord selectedContracts;
	
	private Long passID;	
	
	private EmailRepo lpAEMP;
	
	private EmailRepo2 lpAEMP2;
	
	private TaskRecord tkRecord;
	
	
	
	private String destination = "\\Users\\Shared\\dumpTmp\\";

	private long jobbHourPerYear;

	private List<CandidateInfo> allCreatedDates;
	
	private String dueDate;
	
	

	
	public String getBeanName() {
		return "viewMessengerData";
	}
	
	public ViewMessengerData() {
		init();
		
	}
	
	private void init() {	
		
			
	messengerService = (MessengerService) getService(MessengerConstants.SERVICE_MESSENGER_DATA);

	mailService = (MailService) getService(MessengerConstants.SERVICE_MAIL_DATA); 
	
 
	
	//NEW
	allJobPosted = new ArrayList<JobRecord>();
	
	allJobPosted = messengerService.getAllJobs();
	
	//Existing
	allJobPostedExisting = new ArrayList<JobRecord>();
	
	allJobPostedExisting = messengerService.getAllJobs2();
	
	setAllJobPriority(new ArrayList<ContractsJob>());
	
	setAllJobPriority(messengerService.getAllJobPriority());
	
	allContractTitles = new ArrayList<JobRecord>();
	
	
	allCreatedDates = new ArrayList<CandidateInfo>();
	
	/*allCreatedDates = messengerService.getAllCreatedDates();*/
	
	
	//Completed Task
	allCompletedTask = new ArrayList<TaskRecord>();
			
	allCompletedTask = messengerService.getAllCompletedTask();
	
	//Canceled Task
	allCanceledTask = new ArrayList<TaskRecord>();
	
	allCanceledTask = messengerService.getAllCanceledTask();
	
	allEducationPosted = new ArrayList<EducationResume>();
    
    allEducationPosted = messengerService.getAllEducation();
	
	allEmployPosted = new ArrayList<EmployResume>();
	
	allEmployPosted = messengerService.getAllEmploy();
	 
	allBuisnessContracts = new ArrayList<ContractRecord>();
	
	allBuisnessContracts = messengerService.getAllBuisnessContract();
	
	allCertPosted = new ArrayList<CertResume>();
	
	allCertPosted = messengerService.getAllCert();
	
	allSelectedCerts = new ArrayList<CertResume>();
	
	selectedCerts = new ArrayList<CertResume>();
	
    allSelectedEducation = new ArrayList<EducationResume>();
	
    allSelectedEmploy = new ArrayList<EmployResume>();    
 
	cnd = new ArrayList<CandidateInfo>();
	
	comT = new ArrayList<TaskRecord>();
	
	jobR = new ArrayList<JobRecord>();
	
	cr = new ArrayList<ContractRecord>();
	
	br = new ArrayList<BusinessRecord>();
	
	jr = new ArrayList<JobRecord>();
	
	cat = new ArrayList<Category>();
	
	flat = new ArrayList<FlatFile>();
	
	category = new Category();
	
	flatfile = new FlatFile();
	
	regcan = new RegisterCandidate();
	
	candidateInfo = new CandidateInfo();
	
	cert = new ArrayList<CertResume>();
	
	certResume = new CertResume();
	
	mainER = new ArrayList<EmployResume>();
	
	employResume = new EmployResume();
	
    empEdu = new ArrayList<EducationResume>();
	
	educationResume = new EducationResume();
	
	allContractPosted = new ArrayList<ContractRecord>();
	
	allContractPosted = messengerService.getAllContracts();
	
	
	//NEW
	allCandidateInfoPosted = new ArrayList<CandidateInfo>();
	
	allCandidateInfoPosted = messengerService.getAllCandidateInfo();
	
	//Existing
	allCandidateInfoPostedExisting = new ArrayList<CandidateInfo>();
	
	allCandidateInfoPostedExisting = messengerService.getAllCandidateInfo2();
	
	//ALL
	allCandidateInfoPostedAll = new ArrayList<CandidateInfo>();
	
	allCandidateInfoPostedAll = messengerService.getAllCandidateInfoAll();
	
	//TASK
	allCandidateInfoPostedTask = new ArrayList<CandidateInfo>();
	
	allCandidateInfoPostedTask = messengerService.getAllCandidateInfoTask();
	
	//TASK INFO SIDE
	allTaskInfoPosted = new ArrayList<TaskRecord>();
	
	allTaskInfoPosted = messengerService.getAllTaskInfoPosted();
	
	
	allSelectedComments = new ArrayList<SaveComment>();
	
	allSelectedComments = messengerService.getAllSelectedComments();
	
	
    allEducationPosted = new ArrayList<EducationResume>();
    
    allEducationPosted = messengerService.getAllEducation();
	
	allEmployPosted = new ArrayList<EmployResume>();
	
	allEmployPosted = messengerService.getAllEmploy();
	
	allCertPosted = new ArrayList<CertResume>();
	
	allCertPosted = messengerService.getAllCert();
	
    allQueryRecord = new ArrayList<CandidateInfo>();
	
	allQueryRecord = messengerService.getAllQueryRecord();
	
	allBusinessPosted = new ArrayList<BusinessRecord>();
	
	allBusinessPosted = messengerService.getAllBusiness();
	
	allEmailPosted = new ArrayList<EmailRepo>();
	
	allEmailPosted = messengerService.getAllEmailRepos();
	
	allEmailPosted2 = new ArrayList<EmailRepo2>();
	
	allEmailPosted2 = messengerService.getAllEmailRepos2();
	
	jobRecord = new JobRecord();
	
	candidateFirstName = null;
	
	candidateLastName = null;
	
	candidateEmail = null;
	
	jobDescription = null;
	
	jobTitle = null;
	
	jobRequesttype = null;
		
	contractTitle = null;
	
	candinfoFile = null;
	
	allSelectedJobs = new ArrayList<ContractsJob>();
	
	allSelectedJobs2 = new ArrayList<ContractsJob>();
	
	allSelectedPOC = new ArrayList<ContractRecord>();
		
	allSelectedContracts = new ArrayList<ContractRecord>();
	
	allSelectedBusiness = new ArrayList<BusinessRecord>();
	
	allSelectedCandidateInfoAll = new ArrayList<CandidateInfo>();
	
	allSelectedCandidateInfo = new ArrayList<CandidateInfo>();
	
	allSelectedCandidateInfoExisting = new ArrayList<CandidateInfo>();
	
	allSelectedCandidateInfoTask = new ArrayList<CandidateInfo>();
	
	allSelectedTaskInfo = new ArrayList<TaskRecord>();
	
	allAddedEmailsInfo = new ArrayList<EmailRepo>();
	
	allAddedEmailsInfoExisting = new ArrayList<EmailRepo>();
	
	allAddedEmailsInfo2 = new ArrayList<EmailRepo2>();
	
	allAddedTaskInfo = new ArrayList<TaskRecord>();	
	
	lpAEMP = new EmailRepo();
	
	lpAEMP2 = new EmailRepo2();
	
	/*tkRecord = new TaskRecord();*/
	
	showSelectedLst = Boolean.FALSE;
	
	showSelectedLstDisable = Boolean.TRUE;
	
	showFileUpLoaderDisable = Boolean.FALSE;
	
	showEditButtonDisable = Boolean.FALSE;
	
	canFileUpLoaderDisable = Boolean.FALSE;
	
	showContractUpLoaderDisable = Boolean.FALSE;
	
	showResumeUpLoaderDisable = Boolean.FALSE;
	
	showDatatableDisabledNew = Boolean.FALSE;
	
	showDatatableDisabledExisting = Boolean.FALSE;
	
    showSelectedLst2 = Boolean.FALSE;
	
	showSelectedLstDisable2 = Boolean.TRUE;

	addingEducation = Boolean.FALSE;
	
	addingEmployment = Boolean.FALSE;
	
	addingCertification = Boolean.FALSE;
	
	addingResume = Boolean.FALSE;
	
	updateCandidate = new CandidateInfo();
	
	updateCandidateinfo = new CandidateInfo();
	
	updateBusiness = new BusinessRecord();
	
	deleteCandidate = new DeleteCandidate();
	
	contractRecord = new ContractRecord();
	
	deleteCertResume = new DeleteCertResume();
	
	businessRecord = new BusinessRecord();
	
	updateContract = new ContractRecord(); 
	
	deleteContract = new DeleteContract();
	
	deleteBusiness = new DeleteBusiness();
	
	degree = null;
	
	schoolName = null;
	
	major = null;
	
	compYear = null;
	
	certName = null;
	
	certYear = null;
	
	compName = null;
	
	title = null;
	
	projectRole = null;
	
	projDescpt = null; 
	
	fromMonth = null;
	
	fromYear = null;
	
	toMonth = null;
	
	toYear = null;
	
	projfromMonth = null;
	
	projfromYear = null;
	
	projtoMonth = null;
	
	projtoYear = null;
	
    candinfoFirstName = null;
	
    candinfoLastName = null;
	
    candinfoMiddle = null;
	
    candinfoEmail = null;
    
    candinfoNumber = null;
	
    candinfoQuestone = null;
	
    candinfoQuesttwo = null;
	
    candinfoQuesthree = null; 
	
    candinfoQuestfour = null;
	
	candinfoQuestfive = null;
	
	candinfoSkills = null;
	
	candinfoClearance = null;
	
	candinfoStatus = null;
	
	nameuser = null;
	
	dateCompleted = null;
	
	dateCanceled =  null;
	
	createUpdated = null;
	
	fNameFile = null;	
	
	contractDate = null;
	
    catName = null;
	
	catDescrp = null;
	
	cateDate = null;
	
	fileClob = null; 
	
	resFile = null;
	
	newName = null;
	
	oldName = null;
	
	dateCreated = null;
	
	newNameFile = null;
	
	userId = null;
	
	password = null;
	
	jobPriority = null;
	
	  jobID = null;
		
	  jobDate = null;
			
	  
	 jobDescription = null;
		
	 jobRequest = null;		

	 jobRequesttype = null;
		
	 jobSkill = null;
		
	 jobPosition = null;
		
	 jobClear = null;
		
	 jobLocation = null;
		
	 jobSkillsMandatory = null;
		
	 jobSkillsDesired = null;
		
	 jobCertRequired = null;
		
	 jobConus = null;
		
	 jobOconus = null;
		
	 //jobbHoursYear = (Long) null;
		
	 jobSchedComment = null;
		
	 jobNonPub = null;
		
	 jobMissionCritical = null;
		
	 jobWorkNight = null;
		
	 jobLocalTravel = null;
		
	 jobPager = null;
		
	 jobPagerComment = null;
		
	 jobWorkHoliday = null;
		
	 jobWorkWeekend = null;
		
	 jobShiftWork = null;
		
	 jobWarZone = null;
		
	 jobCoop = null;
	 
	 buisnessdevFirstname = null;
		
	 buisnessdevLastname = null;
		
	 buisnessdevPhonenumber = null;
		
	 buisnessdevEmail = null;
	 
	 candinfoResume = null;
	 
	 adminComments = null;
	 
	 cancelComments = null;
	 
	 commentString = null;
	 
	 dueDate = null;

	}
	
	
	public List<ContractRecord> getAllBuisnessContracts() {
		return allBuisnessContracts;
	}

	public void setAllBuisnessContracts(List<ContractRecord> allBuisnessContracts) {
		this.allBuisnessContracts = allBuisnessContracts;
	}

	public void shwJobFrm(){
		log.info("showing job form");
		jobRecord = new JobRecord();
		clearValues();
	}
	
		
	public void shwConFrm(){
		log.info("showing contract form");
		contractRecord = new ContractRecord();
		clearValues();
	}
	
	
	public void upCandfrm(){
		log.info("showing updated candidate info");
		updateCandidateinfo = new CandidateInfo();
		clearValues();
	}
	 
	public void upBusfrm(){
		log.info("showing business form");
		updateBusiness = new BusinessRecord();
		clearValues();
	}
	
	public void showCANfrm(){
		log.info("Showing candidates");
		candidateInfo = new CandidateInfo();
		clearValues();
	}
	
	
 
 	public String deleteCandidate(){
		log.info("Delete Canddiate");
		updateCandidateinfo.setCandinfo_id(selectedCandidateInfo.getCandinfo_id());
		log.info("The id we need is:" + selectedCandidateInfo.getCandinfo_id());
		messengerService.deleteCandidate(updateCandidateinfo);
		addMessage("Successfully Deleted: "+ selectedCandidateInfo.getCandinfoFirstName() + " record!" );
		clearValues();
		return null;
	}
 	
 	
 	
 	public String SetCompleted(){
		
 		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		nameuser = auth.getName();
 		
 		log.info("Set Task Compeleted");
 		
 		
		dateCompleted = new Date();
		
		System.out.println(selectedTaskRecord.gettask_ID());
		System.out.println(selectedTaskRecord.getCandidateFirstName());
		System.out.println(selectedTaskRecord.getCandidateLastName());
		System.out.println(dateCompleted);
		System.out.println(selectedTaskRecord.getNameuser());
		
	
		selectedTaskRecord.setCandidateID(selectedTaskRecord.gettask_ID());
		selectedTaskRecord.setDateCompleted(dateCompleted);
		selectedTaskRecord.setNameuser(nameuser);
	
		log.info("The id we need is:" + selectedTaskRecord.getCandidateID());
		messengerService.setTask(selectedTaskRecord);
		
		/*try {
			mailService.sendMail("kunta.little@acnc-md.com", "little230@gmail.com","Task Assigned", 
					  "Dear " + selectedTaskRecord.getNameuser()+",\n\n " +
			
					 selectedTaskRecord.getCandidateFirstName() + " "+selectedTaskRecord.getCandidateLastName() +
					 " has been completed by" +" "+" Tara"+" " +"on " + selectedTaskRecord.dateCompleted + " "
					  + "Please click on the link below to open completed task. (www.TaskMessenger.com)");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		addMessage("Successfully Compeleted: "+ selectedTaskRecord.getCandidateFirstName() + " task!" );
		clearValues();
		return null;
	}
 	
 	
 	public String CancelTask(){
 		
 		log.info("You are canceling this task.");
 	    log.info("This is the task id " + selectedTaskRecord.gettask_ID());
 	    log.info(cancelComments);
 	    
 	    System.out.println(dateCanceled);
 	    
 	    dateCanceled = new Date();
 		
 		selectedTaskRecord.gettask_ID();
 		selectedTaskRecord.setCancelComments(cancelComments);
 		selectedTaskRecord.setDateCanceled(dateCanceled);
 		
 		
 		
 		messengerService.CancelTask(selectedTaskRecord);
 		
 		try {
		mailService.sendMail("kunta.little@acnc-md.com", "little230@gmail.com","Task Canceled", 
				  "Dear " + "Tara" +",\n\n " +
		
				 selectedTaskRecord.getCandidateFirstName() + " "+selectedTaskRecord.getCandidateLastName() +" "+ "with ID of:" +" "+ selectedTaskRecord.gettask_ID() +
				 " has been canceled as of " +" "+ selectedTaskRecord.getDateCanceled()+ " " + "by " + selectedTaskRecord.getNameuser() + " "
			     + "for the following reasons:" + " "+ selectedTaskRecord.getCancelComments());
		
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 		
 		                                
 		addMessage("Successfully Canceled Task For: "+ selectedTaskRecord.getCandidateFirstName() + "." );
 		clearValues();
 		
 	
 		return null;
 	}
 	
 	

 	
 	//Add populate button	
   public String saveCandidateData(){
		log.info("saving the following data");
		log.info(candinfoFirstName);
		log.info(candinfoLastName);
		log.info(candinfoEmail);

		candidateInfo.setCandinfoFirstName(candinfoFirstName);
		candidateInfo.setCandinfoLastName(candinfoLastName);
		candidateInfo.setCandinfoEmail(candinfoEmail);
		candidateInfo.setCandinfoMiddle(getCandinfoMiddle());
		candidateInfo.setCandinfoSkills(getCandinfoSkills());
		
		// Dummy data for the other tables Education Certs and Employment..
		educationResume.setDegree(getDegree());
		educationResume.setSchoolName(getSchoolName());
		educationResume.setMajor(getMajor());
		educationResume.setCompYear(new Date());		
		
		certResume.setCertName(getCertName());
		certResume.setCertYear(new Date());
		
		employResume.setCompName(getCompName());
		employResume.setTitle(getTitle());
		employResume.setFromMonth(new Date());
		employResume.setToYear(new Date());
		employResume.setProjectRole(getProjectRole());
		employResume.setProjDescpt(getProjDescpt());
		employResume.setProjfromMonth(new Date());
		employResume.setProjtoYear(new Date());   			
		
		try {
			messengerService.saveCandidate(candidateInfo);
			messengerService.saveCandidateInfo(educationResume);
			messengerService.saveCandidateInfo(employResume);
			messengerService.saveCertResume(certResume);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		log.info("new candidate data candidate");
		addMessage("Successfully Saved: "+ candidateInfo.getCandinfoFirstName() + " record!" );
		clearValues();
		candidateInfo = new CandidateInfo();
		return null;

	}
   
	
 	     
	
	public String updateBusiness(){
       
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		nameuser = auth.getName();
		System.out.println("This is who is editing the contract" +" " +nameuser);	
		
		log.info("update Business Info");
        log.info(selectedBusiness.getBusinessID().toString());
		log.info(selectedBusiness.businessFirstName);
		log.info(selectedBusiness.businessLastName);
		log.info(selectedBusiness.businessPhoneNumber);
		log.info(selectedBusiness.businessEmail);
		
		updateBusiness.setBusinessID(selectedBusiness.getBusinessID());
		updateBusiness.setBusinessFirstName(selectedBusiness.getBusinessFirstName());
		updateBusiness.setBusinessLastName(selectedBusiness.getBusinessLastName());
		updateBusiness.setBusinessPhoneNumber(selectedBusiness.getBusinessPhoneNumber());
		updateBusiness.setBusinessEmail(selectedBusiness.getBusinessEmail());
		updateBusiness.setNameuser(nameuser);
		
		messengerService.updateBusiness(updateBusiness);
		addMessage("Successfully Updated: "+ selectedBusiness.getBusinessFirstName() + " record!");
		clearValues();
		return null;
	
	}
	
	public String updateContracts(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		nameuser = auth.getName();
		System.out.println("This is who is editing the contract" +" " +nameuser);	
		
		log.info("update Contract info");
		log.info(selectedContracts.contractTitle);
		log.info(selectedContracts.contractAgency);
		log.info(nameuser);
		
		log.info(selectedContracts.buisnessdevFirstname);
		log.info(selectedContracts.buisnessdevLastname);
		
		updateContract.setContractID(selectedContracts.getContractID());
		updateContract.setContractTitle(selectedContracts.getContractTitle());
		updateContract.setContractAgency(selectedContracts.getContractAgency());
		updateContract.setContractClass(selectedContracts.getContractClass());
		updateContract.setContractStatus(selectedContracts.getContractStatus());
		updateContract.setNameuser(nameuser);
		
		updateContract.setBuisnessdevFirstname(selectedContracts.getBuisnessdevFirstname());
		updateContract.setBuisnessdevLastname(selectedContracts.getBuisnessdevLastname());
		updateContract.setBuisnessdevPhonenumber(selectedContracts.getBuisnessdevPhonenumber());
		updateContract.setBuisnessdevEmail(selectedContracts.getBuisnessdevEmail());
		
		messengerService.updateContract(updateContract);
		

		addMessage("Successfully Updated: "+ selectedContracts.getContractTitle() + " record!");
		clearValues();
		return null;
		
	}
	
	public String updateJob(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		nameuser = auth.getName();
		System.out.println(nameuser);
		
		log.info("Updating Job info.");
		jobRecord.setJobID(selectedJobs.getJobID());
		jobRecord.setJobRequesttype(selectedJobs.getJobRequesttype());
		jobRecord.setJobTitle(selectedJobs.getJobTitle());
		jobRecord.setJobDescription(selectedJobs.getJobDescription());
		jobRecord.setJobSkill(selectedJobs.getJobSkill());
		jobRecord.setJobPosition(selectedJobs.getJobPosition());
		jobRecord.setJobClear(selectedJobs.getJobClear());
		jobRecord.setJobLocation(selectedJobs.getJobLocation());;
		jobRecord.setJobSkillsMandatory(selectedJobs.getJobSkillsMandatory());
		jobRecord.setJobSkillsDesired(selectedJobs.getJobSkillsDesired());
		jobRecord.setJobCertRequired(selectedJobs.getJobCertRequired());
		jobRecord.setJobConus(selectedJobs.getJobConus());
		jobRecord.setJobOconus(selectedJobs.getJobOconus());
		jobRecord.setJobbHoursYear(selectedJobs.getJobbHoursYear());
		jobRecord.setJobSchedComment(selectedJobs.getJobSchedComment());
		jobRecord.setJobNonPub(selectedJobs.getJobNonPub());
		jobRecord.setJobMissionCritical(selectedJobs.getJobMissionCritical());
		jobRecord.setJobWorkNight(selectedJobs.getJobWorkNight());
		jobRecord.setJobLocalTravel(selectedJobs.getJobLocalTravel());
		jobRecord.setJobPager(selectedJobs.getJobPager());
		jobRecord.setJobPagerComment(selectedJobs.getJobPagerComment());
		jobRecord.setJobWorkHoliday(selectedJobs.getJobWorkHoliday());
		jobRecord.setJobWorkWeekend(selectedJobs.getJobWorkWeekend());
		jobRecord.setJobShiftWork(selectedJobs.getJobShiftWork());
		jobRecord.setJobWarZone(selectedJobs.getJobWarZone());
		jobRecord.setJobCoop(selectedJobs.getJobCoop());
		jobRecord.setJobCloseDate(selectedJobs.getJobCloseDate());
		jobRecord.setContractTitle(selectedJobs.getContractTitle());
		jobRecord.setJobPriority(selectedJobs.getJobPriority());
		jobRecord.setNameuser(nameuser);
		
		messengerService.updateJob(jobRecord);
		addMessage("Successfully Updated: "+ jobRecord.getJobTitle() + " record!" );
		clearValues();
		return null;
	}

	public String deleteContract(){
		log.info("deleting Contract");
		contractRecord.setContractID(selectedContracts.getContractID());		
		messengerService.deleteContract(contractRecord);
		addMessage("Successfully Deleted: "+ selectedContracts.getContractTitle() + " record!");
		clearValues();
		return null;
	}
	
	
	
	
	public String deleteJob(){
		log.info("deleting Job");
		jobRecord.setJobID(selectedJobs.getJobID());
	
		
		
		messengerService.deleteJob(jobRecord);
		addMessage("Successfully Deleted: "+ selectedJobs.getJobTitle() + " record!");
		clearValues();
		return null;
	}
	
	public String deleteBusiness(){ 
		log.info("deleting Business");
		businessRecord.setBusinessID(selectedBusiness.getBusinessID());
		messengerService.deleteBusiness(businessRecord);
		addMessage("Successfully Deleted: "+ selectedBusiness.getBusinessFirstName() + selectedBusiness.getBusinessLastName() + " record!");
		clearValues();
		return null;
	}
	
	
	public String saveJobData(){
		log.info("saving the following data");
		log.info(jobTitle);
		log.info(jobDescription);
		log.info(jobSkill);
		log.info(jobPosition);
		log.info(jobLocation);;
		log.info(jobSkillsDesired);
		log.info(jobSkillsMandatory);
		log.info(jobClear);
		log.info(contractTitle);
		
		log.info(jobNonPub);
		log.info(jobMissionCritical);
		log.info(jobSchedComment);
		log.info(jobWarZone);
		log.info(jobWorkNight);
		log.info(jobWorkHoliday);
		log.info(jobCloseDate);
		log.info(jobCoop);
		log.info(jobPager);
		log.info(jobPagerComment);
		
		
		
     	jobRecord.setJobTitle(jobTitle);
		jobRecord.setJobDescription(jobDescription);
		jobRecord.setJobSkill(jobSkill);
		jobRecord.setJobPosition(jobPosition);
		jobRecord.setJobClear(jobClear);
		jobRecord.setJobLocation(jobLocation);;
		jobRecord.setJobSkillsMandatory(jobSkillsMandatory);
		jobRecord.setJobSkillsDesired(jobSkillsDesired);
		jobRecord.setJobCertRequired(jobCertRequired);
		jobRecord.setJobConus(jobConus);
		jobRecord.setJobOconus(jobOconus);
		jobRecord.setJobbHoursYear(jobbHoursYear);
		jobRecord.setJobSchedComment(jobSchedComment);
		jobRecord.setJobNonPub(jobNonPub);
		jobRecord.setJobMissionCritical(jobMissionCritical);
		jobRecord.setJobWorkNight(jobWorkNight);
		jobRecord.setJobLocalTravel(jobLocalTravel);
		jobRecord.setJobPager(jobPager);
		jobRecord.setJobPagerComment(jobPagerComment);
		jobRecord.setJobWorkHoliday(jobWorkHoliday);
		jobRecord.setJobWorkWeekend(jobWorkWeekend);
		jobRecord.setJobShiftWork(jobShiftWork);
		jobRecord.setJobWarZone(jobWarZone);
		jobRecord.setJobCoop(jobCoop);
		jobRecord.setJobCloseDate(jobCloseDate);
		jobRecord.setContractTitle(contractTitle);
		jobRecord.setJobPriority(jobPriority);
		jobRecord.setNameuser(nameuser);
		jobRecord.setJobSkill(jobSkill);
		
		
		
		messengerService.saveJob(jobRecord);
		log.info(" new job data saved");
		addMessage("Successfully Saved: "+ jobRecord.getJobTitle() + " record!" );
		clearValues();
		return null;	
	}
	
	
	public String saveBusinessData() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		nameuser = auth.getName();
		System.out.println(nameuser);
		
		log.info("saving the following data");
		log.info(businessFirstName);
		log.info(businessLastName);
		log.info(businessEmail);

		businessRecord.setBusinessFirstName(businessFirstName);
		businessRecord.setBusinessLastName(businessLastName);
		businessRecord.setBusinessPhoneNumber(businessPhoneNumber);
		businessRecord.setBusinessEmail(businessEmail);
		businessRecord.setNameuser(nameuser);
		
		messengerService.saveBusiness(businessRecord);
		log.info("new business data candidate");
		addMessage("Successfully Saved: "+ businessRecord.getBusinessFirstName() + " record!" );
		clearValues();
		return null;
		
	}
	
	//Resume wizard candidate
	public String saveCandidateInfo() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		nameuser = auth.getName();
		System.out.println(nameuser);
		
		log.info("Resume Candinfo General Info");		
		candidateInfo.setCandinfoFirstName(candinfoFirstName);
		candidateInfo.setCandinfoLastName(candinfoLastName);
		candidateInfo.setCandinfoMiddle(candinfoMiddle);
		candidateInfo.setCandinfoEmail(candinfoEmail);
		candidateInfo.setCandinfoNumber(candinfoNumber);
		candidateInfo.setCandinfoSkills(candinfoSkills);
		candidateInfo.setCandinfoClearance(candinfoClearance);
		log.info("The clearance type is : " + candinfoClearance);
		candidateInfo.setCandinfoStatus(candinfoStatus);
		candidateInfo.setNameuser(nameuser);
		//candidateInfo.setResFile(resFile);
		candidateInfo.setCandinfoFile(candinfoFile);
		log.info(candinfoFirstName);
		log.info(candinfoLastName);
		System.out.print("Trying to get the file name to print :" + candinfoFile);
		
		try {
			//FileFlat();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messengerService.saveCandidate(candidateInfo);
		cnd.add(candidateInfo);
		addMessage("Successfully Saved: "+ candidateInfo.getCandinfoFirstName() + " record!" );
		clearValues();
		return null;

	}
   //Resume Education Wizard
	public String saveEducationResume() {
		educationResume.setDegree(degree);
		educationResume.setSchoolName(schoolName);
		educationResume.setMajor(major);
		educationResume.setCompYear(compYear);
      
		messengerService.sveducationinfo(educationResume);
		empEdu.add(educationResume);
		for(EducationResume ed: empEdu){
        	System.out.println("stored vaules first : " + ed.getDegree());
        }        

		clearValues();
		return null;

	}
   //Resume Certs Wizard
	public String saveCertResume() {
		certResume.setCertName(certName);
		certResume.setCertYear(certYear);
        messengerService.svcertresume(certResume);
		cert.add(certResume);

		clearValues();
		return null;

	}
  //Resume Employment Wizard
	public String saveEmployResumeinfo() {
		employResume.setCompName(compName);
		employResume.setTitle(title);
		employResume.setFromMonth(fromMonth);
		employResume.setToYear(toYear);
		employResume.setProjectRole(projectRole);
		employResume.setProjDescpt(projDescpt);
		employResume.setProjfromMonth(projfromMonth);
		employResume.setProjtoYear(projtoYear);        
		messengerService.svemployresume(employResume);
		mainER.add(employResume);
       
		clearValues();
		return null;

	}
	
	
	
	public String fileDownloadView() {
		  		
	@SuppressWarnings("unused")
	StreamedContent file;
		
		InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("doc.pdf");
        file = new DefaultStreamedContent(stream, "application/pdf", "doc.pdf");
		
        return null;
		
      
			
	      
	}
	
	public String saveComment(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		nameuser = auth.getName();
		
		createUpdated = new Date();
		
		log.info(commentString);
		System.out.println(selectedTaskRecord.gettask_ID());
		System.out.println(createUpdated);
		log.info(nameuser);
		
		
		selectedTaskRecord.setCommentString(commentString);
		selectedTaskRecord.settask_ID(selectedTaskRecord.task_ID);
		selectedTaskRecord.setCreateUpdated(createUpdated);
		selectedTaskRecord.setNameuser(nameuser);
		
		
		messengerService.saveComment(selectedTaskRecord);
			
	/*	try {
			mailService.sendMail("kunta.little@acnc-md.com", "little230@gmail.com","Comment Has Been Made", 
					  "Dear " + selectedTaskRecord.getNameuser() + ",\n\n " +
			
					  "A Comment has been made on task ID:"+ selectedTaskRecord.gettask_ID() +". "+
					 "Please click on the link below to view the Comment. (www.TaskMessenger.com)");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		}*/
		
		clearValues();
		return null;
	}
	
	
	
	public void DownloadResumeBlob(){
		
		
		Connection myConn = null;
		Statement myStmt = null;
		
		candidateInfo = new CandidateInfo();
		
		
		
		File theFile = new File("/Users/Administrator/Downloads/" +tkRecord.getCandidateFirstName()+ tkRecord.getCandidateLastName()+".pdf");
	
		try {
			
			
			// 1. Get a connection to database
			//myConn = DriverManager.getConnection("jdbc:mysql://192.168.10.8:3306", "ace", "mypass");
			myConn = DriverManager.getConnection("jdbc:mysql://192.168.10.188:3306", "root", "Qazwsx1234");

			// 2. Execute statement
			myStmt = myConn.createStatement();
						
			// take the result of the query
			ResultSet rs = myStmt.executeQuery("Select resume from massEmail.candinfo where candinfo_id = " + tkRecord.getCandidateID());
			while(rs.next()) { // for each row
			   // take the blob
				
			   Blob blob = rs.getBlob("resume");
			   BufferedInputStream is = new BufferedInputStream(blob.getBinaryStream());	   
			   FileOutputStream fos = new FileOutputStream(theFile);
			   
			   // you can set the size of the buffer
			   byte[] buffer = new byte[2048];
			   int r = 0;
			   while((r = is.read(buffer))!=-1) {
			      fos.write(buffer, 0, r);
			      
			      
			   }
			   fos.flush();
			   fos.close();
			   is.close();
			   blob.free();
			   
			    System.out.println("\nSaved to file: " + theFile.getAbsolutePath());
			    			    
			    System.out.println(selectedJobAll.getJobTitle()); 
			      
				System.out.println("\nCompleted successfully!");
			}
			myStmt.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
		
public String DownloadTemplateBlob(){
		
	
	
		
		Connection myConn = null;
		Statement myStmt = null;
		
		System.out.println("This is what you should see"+ selectedTaskRecord.getJobTitle());
		
		
		
		File theFile = new File("/Users/Administrator/Downloads/" +selectedTaskRecord.getJobTitle() +".docx");
	
		try {
			
			
			
			// 1. Get a connection to database
			//myConn = DriverManager.getConnection("jdbc:mysql://192.168.10.8:3306", "ace", "mypass");
			myConn = DriverManager.getConnection("jdbc:mysql://192.168.10.188:3306", "root", "Qazwsx1234");
			// 2. Execute statement
			myStmt = myConn.createStatement();
						
			// take the result of the query
			ResultSet rs = myStmt.executeQuery("Select jobTemplate from massEmail.tasks where task_ID = " + selectedTaskRecord.gettask_ID());
			while(rs.next()) { // for each row
			   // take the blob
				
			   Blob blob = rs.getBlob("jobTemplate");
			   
			   if(rs.getBlob("jobTemplate") == null){
					 
				 addMessage("There Is No Template Attached");
				   
				 return "tasks.xhtml";
			   }
		
			   
			   
			   
			   
			   BufferedInputStream is = new BufferedInputStream(blob.getBinaryStream());	   
			   FileOutputStream fos = new FileOutputStream(theFile);
			   
			   // you can set the size of the buffer
			   byte[] buffer = new byte[2048];
			   int r = 0;
			   while((r = is.read(buffer))!=-1) {
			      fos.write(buffer, 0, r);
			      
			     
			   }
			   fos.flush();
			   fos.close();
			   is.close();
			   blob.free();
			   
			    System.out.println("\nSaved to file: " + theFile.getAbsolutePath());
			    			    
			    System.out.println(selectedTaskRecord.getJobTitle()); 
			      
				System.out.println("\nCompleted successfully!");
			}
			myStmt.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
 	
public String DownloadResumeBlobUser(){
	
	
	Connection myConn = null;
	Statement myStmt = null;
	
	candidateInfo = new CandidateInfo();
	
	System.out.println("This is what you should see:" + selectedTaskRecord.gettask_ID());
	
	File theFile = new File("/Users/Administrator/Downloads/" +selectedTaskRecord.getCandidateFirstName()+ selectedTaskRecord.getCandidateLastName()+".pdf");

	try {
		
		
		// 1. Get a connection to database
		//myConn = DriverManager.getConnection("jdbc:mysql://192.168.10.8:3306", "ace", "mypass");
		myConn = DriverManager.getConnection("jdbc:mysql://192.168.10.188:3306", "root", "Qazwsx1234");
		// 2. Execute statement
		myStmt = myConn.createStatement();
					
		// take the result of the query
		ResultSet rs = myStmt.executeQuery("Select candinfoResume from massEmail.tasks where task_ID = " + selectedTaskRecord.gettask_ID());
		while(rs.next()) { // for each row
		   // take the blob
			
		   Blob blob = rs.getBlob("candinfoResume");
		   
		   if(rs.getBlob("candinfoResume") == null){
				 
				 addMessage("There Is No Resume Attached");
				   
				 return "tasks.xhtml";
			   }
		   
		   BufferedInputStream is = new BufferedInputStream(blob.getBinaryStream());	   
		   FileOutputStream fos = new FileOutputStream(theFile);
		   
		   // you can set the size of the buffer
		   byte[] buffer = new byte[2048];
		   int r = 0;
		   while((r = is.read(buffer))!=-1) {
		      fos.write(buffer, 0, r);
		      
		      
		   }
		   fos.flush();
		   fos.close();
		   is.close();
		   blob.free();
		   
		    System.out.println("\nSaved to file: " + theFile.getAbsolutePath());
		    			    
		    System.out.println(selectedTaskRecord.getJobTitle()); 
		      
			System.out.println("\nCompleted successfully!");
		}
		myStmt.close();
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}




	public String DownloadCompletedTemplate(){
	
	Connection myConn = null;
	Statement myStmt = null;
	
		log.info(selectedTaskRecord.getCandidateFirstName());
		System.out.println(selectedTaskRecord.getCandidateLastName());
	
	
		System.out.println("This is what you should see:" + selectedTaskRecord.gettask_ID());
		System.out.println("This is what you should see:");
	
		File theFile = new File("/Users/Administrator/Downloads/" +selectedTaskRecord.getCandidateFirstName()+ selectedTaskRecord.getCandidateLastName()+".pdf");

		try {
		
		
		// 1. Get a connection to database
			//myConn = DriverManager.getConnection("jdbc:mysql://192.168.10.8:3306", "ace", "mypass");
			myConn = DriverManager.getConnection("jdbc:mysql://192.168.10.188:3306", "root", "Qazwsx1234");
		// 2. Execute statement
		myStmt = myConn.createStatement();
					
		// take the result of the query
		ResultSet rs = myStmt.executeQuery("Select completedTemplate from massEmail.tasks where task_ID = " + selectedTaskRecord.gettask_ID());
		while(rs.next()) { // for each row
		   // take the blob
			
		   Blob blob = rs.getBlob("completedTemplate");
		   
		   if(rs.getBlob("completedTemplate") == null){
			 
			   addMessage("There Is No Completed Template Attached");
			   
			   return "tasksAdmin.xhtml";
		   }
	
		   
		   BufferedInputStream is = new BufferedInputStream(blob.getBinaryStream());
		 
		   
		   FileOutputStream fos = new FileOutputStream(theFile);
		   
		   // you can set the size of the buffer
		   byte[] buffer = new byte[2048];
		   int r = 0;
		   while((r = is.read(buffer))!=-1) {
		      fos.write(buffer, 0, r);
		      
		      
		   }
		   
		   
		   fos.flush();
		   fos.close();
		   is.close();
		   blob.free();
		   
		    System.out.println("\nSaved to file: " + theFile.getAbsolutePath());
		    			    
		    System.out.println(selectedTaskRecord.getJobTitle()); 
		      
			System.out.println("\nCompleted successfully!");
		}
		myStmt.close();
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
	

	
}



	
	public String getIDNUmber(){
		passID = selectedCandidateInfo.getCandinfo_id();
		System.out.println(selectedCandidateInfo.getCandinfo_id());
			
	    allSelectedCerts = new ArrayList<CertResume>();
		allSelectedCerts = messengerService.getSelectedCertsInfo(passID);
	
	    allSelectedEducation = new ArrayList<EducationResume>();
	    allSelectedEducation = messengerService.getSelectedEducationInfo(passID);
		
	    allSelectedEmploy = new ArrayList<EmployResume>();
	    allSelectedEmploy = messengerService.getSelectedEmployInfo(passID);
	    
	    //showEditButtonDisable = Boolean.TRUE;
		
		return null;
	}
	
	public String getJobIDNumber(){
		passID = selectedJobInfo.getContractID();
		System.out.println("Printing the passedID: " + passID);
	    allSelectedJobs = new ArrayList<ContractsJob>();
	    allSelectedJobs = messengerService.getSelectedJobInfo(passID);
	    System.out.print("better print");	    
	    System.out.print("better print twoooo");
		
		return null;
	}
	
		
	public String contractID(){
		passID = selectedContractInfo.getContractID();
		System.out.println("Printing the passedID: " + passID);
		allSelectedPOC = new ArrayList<ContractRecord>();
		allSelectedPOC = messengerService.getSelectedPOCInfo(passID);
		for(ContractRecord trc: allSelectedPOC){
			System.out.println("This the busniess id " + trc.getBuisnessdevID());
			System.out.println("This is the first name : " + trc.getBuisnessdevFirstname());
		}
		System.out.print("better print");
	    System.out.print("better print twoooo");
		
		return null;
	}
	
	
		
	public void updateViewCand(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		nameuser = auth.getName();
		System.out.println(nameuser);
		
		candidateInfo.setCandinfo_id(selectedCandidateInfo.getCandinfo_id());
		candidateInfo.setCandinfoFirstName(selectedCandidateInfo.candinfoFirstName);
		candidateInfo.setCandinfoLastName(selectedCandidateInfo.candinfoLastName);
		candidateInfo.setCandinfoMiddle(selectedCandidateInfo.candinfoMiddle);
		candidateInfo.setCandinfoEmail(selectedCandidateInfo.candinfoEmail);
		candidateInfo.setCandinfoNumber(selectedCandidateInfo.candinfoNumber);
		candidateInfo.setCandinfoSkills(selectedCandidateInfo.candinfoSkills);
		candidateInfo.setCandinfoStatus(selectedCandidateInfo.candinfoStatus);
		candidateInfo.setCandinfoClearance(selectedCandidateInfo.candinfoClearance);
		candidateInfo.setNameuser(nameuser);
		addMessage("Successfully Updated: "+ selectedCandidateInfo.getCandinfoFirstName() + " record!");		
		messengerService.upDateCandidate(candidateInfo);
		
		clearValues();
	}
	
	
	
	
	//Resume  view wizard employment edit
	public void rowEditEmploy(RowEditEvent event){
		Long num = (Long)((EmployResume) event.getObject()).getEmployID();
		String emp = (String) ((EmployResume) event.getObject()).getCompName();
		String tt = (String) ((EmployResume) event.getObject()).getTitle();
		
		System.out.printf("Company Name " + emp + " Title " + tt + " With ID NUm : " + num);
		System.out.println("\n");
		 
		 messengerService.updateEmploy(((EmployResume)event.getObject()));
	     FacesMessage msg = new FacesMessage("Employ Edited", (String) ((EmployResume) event.getObject()).getCompName()); 	  
	     FacesContext.getCurrentInstance().addMessage(null, msg); 
		
	}
	//Resume  view employment wizard cancel
	public void cancelEmploy(RowEditEvent event){
		Long num = (Long)((EmployResume) event.getObject()).getEmployID();
		String emp = (String) ((EmployResume) event.getObject()).getCompName();
		String tt = (String) ((EmployResume) event.getObject()).getTitle();
		
		System.out.printf("Company Name " + emp + " Title " + tt + " With ID NUm : " + num);
		System.out.println("\n");
	    FacesMessage msg = new FacesMessage("Employ cancel", (String) ((EmployResume) event.getObject()).getCompName()); 	  
	    FacesContext.getCurrentInstance().addMessage(null, msg); 
		
	}

	//Resume  view wizard education edit
	public void rowEditEducation(RowEditEvent event){
		Long num = (Long)((EducationResume) event.getObject()).getEdID();
		String scname = (String) ((EducationResume) event.getObject()).getSchoolName();
		String mj = (String) ((EducationResume) event.getObject()).getMajor();
		String dg = (String) ((EducationResume) event.getObject()).getDegree();
		System.out.printf("Degree " + dg + " school name " + scname + " Major " + mj + " With ID NUm : " + num);
		System.out.println("\n");
		messengerService.updateEducation(((EducationResume) event.getObject()));
	    FacesMessage msg = new FacesMessage("education Edited", ((EducationResume) event.getObject()).getDegree()); 	  
	    FacesContext.getCurrentInstance().addMessage(null, msg);  
		
	}
	//Resume  view wizard cancel education
	public void cancelEducation(RowEditEvent event){
		
		Long num = (Long)((EducationResume) event.getObject()).getEdID();
		String scname = (String) ((EducationResume) event.getObject()).getSchoolName();
		String mj = (String) ((EducationResume) event.getObject()).getMajor();
		String dg = (String) ((EducationResume) event.getObject()).getDegree();
		System.out.printf("Degree " + dg + " school name " + scname + " Major " + mj + " With ID NUm : " + num);
		System.out.println("\n");
	    FacesMessage msg = new FacesMessage("Education Cancelled", ((EducationResume) event.getObject()).getDegree()); 	  
	    FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	////Resume  view wizard Certs edit
	 public void rowEditCerts(RowEditEvent event) {  
		   Long num = (Long)((CertResume) event.getObject()).getCerID();
		   String limitName =  (String) ((CertResume) event.getObject()).getCertName();
		   Date dt = (Date) ((CertResume) event.getObject()).getCertYear();
		   System.out.printf("Cert Name Edited " + limitName + " Cert Date " + dt + " With ID NUm : " + num);
		   System.out.println("\n");
		   messengerService.updateCert(((CertResume) event.getObject()));
	       FacesMessage msg = new FacesMessage("Cert Edited", ((CertResume) event.getObject()).getCertName()); 	  
	       FacesContext.getCurrentInstance().addMessage(null, msg);  
	    } 
	 ////Resume  view wizard Certs cancel
	 public void cancelCerts(RowEditEvent event) { 
		 Long num = (Long)((CertResume) event.getObject()).getCerID();
		 String limit = (String) ((CertResume) event.getObject()).getCertName();
		 System.out.printf("Car Cancelled " + limit + " With ID NUm : " + num);
		 System.out.println("\n");		  
		 
	     FacesMessage msg = new FacesMessage("Car Cancelled", ((CertResume) event.getObject()).getCertName());	  
	     FacesContext.getCurrentInstance().addMessage(null, msg);  
	    } 
	 
	 //Register Candidate method
	 public void Register(){
		log("Register new Candidate");
		regcan.setFirstName(regFirstName);
		regcan.setLastName(regLastName);
		regcan.setUsername(regusername);
		regcan.setPassword(regpassword);
		regcan.setEmail(regEmail);
		System.out.println(regFirstName);
		System.out.println(regLastName);
		System.out.println(regusername);
		System.out.println(regpassword);
		
        /* FacesMessage msg = new FacesMessage( getRegFirstName() + " Registration is complete");
		FacesContext.getCurrentInstance().addMessage(null, msg);	*/	
		addMessage( getRegFirstName() + " Registration is complete");
		messengerService.newUser(regcan);
	    
		 
	 }
	 
	
	 
	 
	 //UpLoad event Process 
	 private static void log(String message)
	  {
	          System.out.println(message);
	  }
	
	private static String GetFileExtension(String fname2)
	  {
	      String fileName = fname2;
	      String ext="";
	      int mid = fileName.lastIndexOf(".");
	      //String fname = fileName.substring(0,mid);
	      ext = fileName.substring(mid+1,fileName.length());
	      return ext;
	  }

	public void handleFileUpload(FileUploadEvent event) throws NoSuchAlgorithmException, SerialException, SQLException {  
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        newNameFile = event.getFile().getFileName();
        
        // Do what you want with the file        
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
            addMessage("File Did Not Upload Please Refresh the Page and Try Again" );
        }
        
        String fileExtn = GetFileExtension(event.getFile().getFileName());
          Workbook wb = null;
          Sheet sheet = null;
          try {

			    FileInputStream file = new FileInputStream(new File(destination + event.getFile().getFileName()));


		    if (fileExtn.equalsIgnoreCase("xlsx"))
			      {
				       wb = new XSSFWorkbook(file);
				      log("xlsx="+wb.getSheetName(0));
				      sheet = wb.getSheetAt(0);
			      }
		    else if (fileExtn.equalsIgnoreCase("xls"))
			      {
				  POIFSFileSystem fs = new POIFSFileSystem(file);
			    	  wb = new HSSFWorkbook(fs);
			    	  log("xls="+wb.getSheetName(0));
			    	  sheet = wb.getSheetAt(0);
			      }
			      int sheets = wb.getNumberOfSheets();			     
				     for(int i =0; i< sheets; i++) {

				    	 sheet = wb.getSheetAt(i);

			    //Iterate through each rows from first sheet
			    Iterator<Row> rows= sheet.rowIterator();
			    while (rows.hasNext ())
			    {
			    Row row = rows.next ();
			    if(row.getRowNum()==0 || row.getRowNum()==0){
			           continue; //just skip the rows if row number is 0 or 1
			          }


			     /*  Double num= 0.0;		*/        
			        String Column1 = "";
			        String Column2 = "";
			        String Column3 = "";
			        String Column4 = "";
			        String Column5 = "";
			        String Column6 = "";
			        String Column7 = "";
			        String Column8 = "";
			        String Column9 = "";
			        String Column10 = "";
			        String Column11 = "";
			        String Column12="";
					String Column14 = "";
			      /*  double Column13 = 0;*/
			        String Column15 = "";
				    String Column16 = "";
				    String Column17 = "";
				    String Column18 = "";
				    String Column19 = "";
				    String Column20 = "";
				    String Column21 = "";
				    String Column22 = "";
				    String Column23 = "";
				    String Column24 = "";
				    String Column25 = "";
				    String Column26 = "";
				    String Column27= "";
                    String Column28= "";
                    String Column29="";
                    
                   
                    
                    
                    
                    
			        //For each row, iterate through each columns
			        Iterator<Cell> cellIterator = row.cellIterator();
			        while(cellIterator.hasNext()) {

			            Cell cell = cellIterator.next();
			            
			               switch(cell.getCellType()) {
			                case Cell.CELL_TYPE_BOOLEAN:
			                    System.out.print(cell.getBooleanCellValue() + "\t\t");
			                    break;
			                case Cell.CELL_TYPE_NUMERIC:
			                	
			                	cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			                     cell.getNumericCellValue();
						
							
			                        jobbHourPerYear = (long) cell.getNumericCellValue();
			                        jobRecord.setJobbHoursYear(jobbHourPerYear);
			                        System.out.println(jobbHourPerYear + " The Job Request");
			                		jobRecord.setJobbHoursYear(jobbHourPerYear);
			                     
			                  
			                     
			                	
			                	
			                    break;
			                case Cell.CELL_TYPE_STRING:
			                	if(Column1.equalsIgnoreCase("")){
			                		Column1 = cell.getStringCellValue().trim();
			                		jobRecord.setJobRequest(Column1);
			                	}else if (Column2.equalsIgnoreCase("")){
			                		Column2 = cell.getStringCellValue().trim();
			                		jobRecord.setJobRequesttype(Column2);
			                	}else if (Column3.equalsIgnoreCase("")){
			                		Column3 = cell.getStringCellValue().trim();
			                		jobRecord.setJobTitle(Column3);		
			                	}else if (Column4.equalsIgnoreCase("")){
			                		Column4 = cell.getStringCellValue().trim();
			                		jobRecord.setJobSkill(Column4);
			                	}else if (Column5.equalsIgnoreCase("")){
			                		Column5 = cell.getStringCellValue().trim();
			                		jobRecord.setJobPosition(Column5);
			                	}else if (Column6.equalsIgnoreCase("")){
			                		Column6 = cell.getStringCellValue().trim();
			                		jobRecord.setJobClear(Column6);
			                	}else if (Column7.equalsIgnoreCase("")){
			                		Column7 = cell.getStringCellValue().trim();
			                		jobRecord.setJobLocation(Column7);
			                	}else if (Column8.equalsIgnoreCase("")){
			                		Column8 = cell.getStringCellValue().trim();
			                		jobRecord.setJobDescription(Column8);
			                	}else if (Column9.equalsIgnoreCase("")){
			                		Column9 = cell.getStringCellValue().trim();
			                		jobRecord.setJobSkillsMandatory(Column9);
			                	}else if (Column10.equalsIgnoreCase("")){
			                		Column10 = cell.getStringCellValue().trim();
			                		jobRecord.setJobSkillsDesired(Column10);
			                	}else if (Column11.equalsIgnoreCase("")){
			                		Column11 = cell.getStringCellValue().trim();
			                		jobRecord.setJobCertRequired(Column11);
			                	}else if (Column12.equalsIgnoreCase("")){
			                		Column12 = cell.getStringCellValue().trim();
			                		jobRecord.setJobConus(Column12);
			                	}else if (Column14.equalsIgnoreCase("")){
			                		Column14 = cell.getStringCellValue().trim();
			                		jobRecord.setJobOconus(Column14);
			                		
			                	/*   }else if (Column13.equalsIgnoreCase("")){
			                		Column13 = cell.getNumericCellValue();
			                		jobRecord.setJobbHoursYear(Column13);*/
			             	
			               		
			                    }else if (Column15.equalsIgnoreCase("")){
			                        Column15 = cell.getStringCellValue().trim();
			                		jobRecord.setJobSchedComment(Column15);
			                	}
			                	
			                    else if (Column16.equalsIgnoreCase("")){
			                		Column16 = cell.getStringCellValue().trim();
			                		jobRecord.setJobNonPub(Column16);
			                	}
			                	
			                	else if (Column17.equalsIgnoreCase("")){
			                		Column17 = cell.getStringCellValue().trim();
			                		jobRecord.setJobMissionCritical(Column17);
			                	}
			                	else if (Column18.equalsIgnoreCase("")){
			                		Column18 = cell.getStringCellValue().trim();
			                		jobRecord.setJobWorkNight(Column18);
			                	}

			                	else if (Column19.equalsIgnoreCase("")){
			                		Column19 = cell.getStringCellValue().trim();
			                		jobRecord.setJobLocalTravel(Column19);
			                	}
			                	else if (Column20.equalsIgnoreCase("")){
			                		Column20 = cell.getStringCellValue().trim();
			                		jobRecord.setJobPager(Column20);
			                	}
			                	else if (Column21.equalsIgnoreCase("")){
			                		Column21 = cell.getStringCellValue().trim();
			                		jobRecord.setJobPagerComment(Column21);
			                	}
			                	else if (Column22.equalsIgnoreCase("")){
			                		Column22 = cell.getStringCellValue().trim();
			                		jobRecord.setJobWorkHoliday(Column22);
			                	}
			                	else if (Column23.equalsIgnoreCase("")){
			                		Column23 = cell.getStringCellValue().trim();
			                		jobRecord.setJobWorkWeekend(Column23);
			                	}
			                	else if (Column24.equalsIgnoreCase("")){
			                		Column24 = cell.getStringCellValue().trim();
			                		jobRecord.setJobShiftWork(Column24);
			                	}
			                	else if (Column25.equalsIgnoreCase("")){
			                		Column25 = cell.getStringCellValue().trim();
			                		jobRecord.setJobWarZone(Column25);
			                	}
			                	else if (Column26.equalsIgnoreCase("")){
			                		Column26 = cell.getStringCellValue().trim();
			                		jobRecord.setJobCoop(Column26);
			                	
			                	}
			                	else if (Column27.equalsIgnoreCase("")){
			                		Column27 = cell.getStringCellValue().trim();
			                		jobRecord.setJobStatusChange(Column27);		
			                	
			                	
			                	}
			                	else if (Column28.equalsIgnoreCase("")){
			                		Column28 = cell.getStringCellValue().trim();
			                		jobRecord.setJobUpdateDate(Column28);	
			                		
			                	}
			                	else if (Column29.equalsIgnoreCase("")){
			                		Column29 = cell.getStringCellValue().trim();
			                		jobRecord.setJobCloseDate(Column29);	
			                		
			                	
			                		
			                		
			                		
			                	}else{ 
		                    System.out.print("Random STuff::" + cell.getStringCellValue() + "\t\t");
			                	}
			                	
			                	
			                	
			                    break;
			            }
			        }
			        if(jobRecord.getJobRequest() == null){

			        	log.info("row is blank");
			        	}
			        	else{
			        	log.info("row is not blank, adding now");
			        	
			        	
			        	jr.add(jobRecord);
			        	

			        	jobRecord = new JobRecord();
			        	}
			    }
				     } // sheets loop

			    file.close();
			    FileOutputStream out = 
			        new FileOutputStream(new File(event.getFile().getFileName()).getAbsoluteFile());
			    wb.write(out);
			    out.close();

			} catch (FileNotFoundException e) {
			    e.printStackTrace();
			} catch (IOException e) {
			    e.printStackTrace();
			}
          try {
				FileFlat();
				//messengerService.saveJob(jr);
			} catch (Exception e) {
			
				e.printStackTrace();
			}
           
          
        
    }  
				
			
    public void copyFile(String fileName, InputStream in) throws NoSuchAlgorithmException, SerialException, SQLException {
    	       MessageDigest md = MessageDigest.getInstance("MD5");
           try {
                             
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(destination + fileName);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();               
                System.out.println(destination + fileName);
                System.out.println("This is the size of the file " + fileName.length());
                System.out.println("The size is " + in.available());
                int read = 0;
                byte[] bytes = new byte[1024];
             
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                    bos.write(bytes);   
                }
                bytes = md.digest();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < bytes.length; i++) {
                 sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                 
                }
                
                lilb = sb.toString();
                clobFile = bos.toByteArray();
                System.out.println("Digest(in hex format):: " + sb.toString());
                System.out.println(clobFile); 
                System.out.println(clobFile.equals(bos));
                System.out.println(bos.size());
                in.close();
                out.flush();
                out.close();
             
                System.out.println("New file created!");
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
           
         
    } 
    
    public void contFileUpload(FileUploadEvent event) throws NoSuchAlgorithmException, SerialException, SQLException{
    	    log.info("Contract upload files");
		    
	        FacesMessage msg = new FacesMessage("Succesful Contract File " + event.getFile().getFileName() + " is uploaded.");  
		    FacesContext.getCurrentInstance().addMessage(null, msg);
		    newNameFile = event.getFile().getFileName();
		    try {
	            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		    
		     String fileExtn = GetFileExtension(event.getFile().getFileName());
	          Workbook wb = null; //Declare Workbook
	          Sheet sheet = null; //Declare Sheets
	          
	          try {
	        	   FileInputStream file = new FileInputStream(destination + event.getFile().getFileName());
				     
				    if(fileExtn.equalsIgnoreCase("xlsx")){
						   wb = new XSSFWorkbook(file);
						   log("xlsx="+wb.getSheetName(0));
					   }else if(fileExtn.equalsIgnoreCase("xls")){
						 POIFSFileSystem fs = new POIFSFileSystem(file);
						 wb = new HSSFWorkbook(fs);
						 log("xls="+wb.getSheetName(0));
					   }
					     int sheets = wb.getNumberOfSheets();			     
					     for(int i =0; i< sheets; i++) {
					      
					    	 sheet = wb.getSheetAt(i);
				   
			   				     
				    //Iterate through each rows from first sheet
					    	 Iterator<Row> rowIterator = sheet.iterator();
							    while(rowIterator.hasNext()) {
							        Row row = rowIterator.next();
							          
							          String Column1 = "";
								      String Column2 = "";
								      String Column3 = "";
								      String Column4 = "";
								      String Column5 = "";
							          String Column6 = "";
							        
							          							         
							        //For each row, iterate through each columns
							        Iterator<Cell> cellIterator = row.cellIterator();
							        while(cellIterator.hasNext()) {
							             
							            Cell cell = cellIterator.next();
							             
							            switch(cell.getCellType()) {
							                case Cell.CELL_TYPE_BOOLEAN:
							                    System.out.print(cell.getBooleanCellValue() + "\t\t");
							                    break;
							                case Cell.CELL_TYPE_NUMERIC:
							                	if (DateUtil.isCellDateFormatted(cell)) {
							                		contractDate = cell.getDateCellValue();
							                		contractRecord.setContractDate(contractDate);
							                        System.out.println("");
							                    } else {
							                        System.out.println(cell.getNumericCellValue());
							                    }
							              
							                    break;
							                case Cell.CELL_TYPE_STRING:
							                	if(Column1.equalsIgnoreCase("")){
							                		Column1 = cell.getStringCellValue().trim();
							                		System.out.print(Column1);
							                		contractRecord.setContractTitle(Column1);
							                	}
							                	else if(Column2.equalsIgnoreCase("")){
							                		Column2 = cell.getStringCellValue().trim();
							                		System.out.print(Column2);
							                		contractRecord.setContractAgency(Column2);
							                	}
							                	else if(Column3.equalsIgnoreCase("")){
							                		 Column3 = cell.getStringCellValue().trim();
							                		 businessRecord.setBusinessFirstName(Column3);
							                	}
							                	else if(Column4.equalsIgnoreCase("")){
							                		Column4 = cell.getStringCellValue().trim();
							                		businessRecord.setBusinessEmail(Column4);
							                	}
							                	else if (Column5.equalsIgnoreCase("")){
							                		Column5 = cell.getStringCellValue().trim();
							                	}
							                	else if(Column6.equalsIgnoreCase("")){
							                		Column6 = cell.getStringCellValue().trim();
							                	}
							                	else {
						                        System.out.print(" Random stuff : " + cell.getStringCellValue() + "\t\t");
						                        
							                	}
							                    break;
							            }
							        } 
							        br.add(businessRecord);
							        cr.add(contractRecord);	
							        contractRecord = new ContractRecord();
							        businessRecord = new BusinessRecord();
							        
							       }
							    
					     } 
					     for (ContractRecord r : cr){
					    	 System.out.println("My contents : " + r.getContractTitle() + " " + r.getContractAgency() + " " + r.getContractDate());
					     }
					      for (BusinessRecord b : br){
					    	  System.out.print(b.getBusinessFirstName() + " " + b.getBusinessEmail());
					      }
					       System.out.println(" ");
					      
				    file.close();
				    FileOutputStream out = 
				        new FileOutputStream(new File(event.getFile().getFileName()).getAbsoluteFile());
				    wb.write(out);
				    out.close();
				     
				} catch (FileNotFoundException e) {
				    e.printStackTrace();
				} catch (IOException e) {
				    e.printStackTrace();
				}
	          		      
	           try {
				FileFlat();
				messengerService.saveBusinessFile(br);
		        messengerService.saveContractFile(cr);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		     

    }// end contFileUploader
    
   public void FileFlat() throws Exception{ 
    	  flatfile = new FlatFile();
    	  newName = lilb;    		
    	  oldName = newNameFile;
    	  flatfile.setClobFile(clobFile);
    	  flatfile.setNewName(newName);
    	  flatfile.setOldName(oldName);
    	  flat.add(flatfile);    	  
    		
    	  System.out.println("the flat file: " + clobFile +  " : " + newName + " :: " + oldName); 
    	  messengerService.saveFlatFile(flat);
     }
	
	 
	 public void candFileUpload(FileUploadEvent event) throws IOException{
		   log.info("I get Called to upload files");
		    
	        FacesMessage msg = new FacesMessage("Succesful FILE " + event.getFile().getFileName() + " is uploaded.");  
		    FacesContext.getCurrentInstance().addMessage(null, msg);
	       
		    try {
		    	File targetFolder = new File(destination + event.getFile().getFileName());
		    	InputStream inputStream = event.getFile().getInputstream();
		    	OutputStream out = new FileOutputStream(new File(targetFolder,
		    	event.getFile().getFileName()));
		    	int read = 0;
		    	byte[] bytes = new byte[1024];
		    	while ((read = inputStream.read(bytes)) != -1) {
		    	out.write(bytes, 0, read);
		    	}
		    	inputStream.close();
		       	out.flush();
		    	out.close();
		    	} catch (IOException e) {
		    	e.printStackTrace();
		    	}
		    	
	 }    
	 
	 
	 public void resumeFileUploader(FileUploadEvent event) throws Exception{
		   
		 candidateInfo = new CandidateInfo();
		 
		System.out.println(selectedCandidateInfo.candinfo_id);
		System.out.println(selectedCandidateInfo.candinfoFirstName);
		System.out.println(selectedCandidateInfo.candinfoLastName); 
		  
		 
		
		    log.info("I get Called to upload RESUME files");		    
	        FacesMessage msg = new FacesMessage("Succesful FILE " + event.getFile().getFileName() + " is uploaded.");  
		    FacesContext.getCurrentInstance().addMessage(null, msg);
		    newNameFile = event.getFile().getFileName();
		  
		    try {
	            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
	  		  
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		    long te = (long) 1.0;
		    category.setCatID(te);
	          messengerService.passID(category);
	          candidateInfo.setCandinfo_id(selectedCandidateInfo.candinfo_id);
	          candidateInfo.setResFile(clobFile);	          

		/*   FileFlat();*/
		   messengerService.upDateCandResume(candidateInfo);
		   selectedCandidateInfo = null;

		   
	 }
		  
	 public void jobTemplateUploader(FileUploadEvent event) throws Exception{
		   
		 jobRecord = new JobRecord();
		System.out.println(selectedJobs.getJobID());
		System.out.println(selectedJobs.getJobTitle());
	 
		
		    log.info("I get Called to upload a Job Template");		    
	        FacesMessage msg = new FacesMessage("Succesful FILE " + event.getFile().getFileName() + " is uploaded.");  
		    FacesContext.getCurrentInstance().addMessage(null, msg);
		    newNameFile = event.getFile().getFileName();
		  
		    try {
	            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
	  		  
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		    long te = (long) 1.0;
		    category.setCatID(te);
	          messengerService.passID(category);
	          jobRecord.setJobID(selectedJobs.jobID);
	          jobRecord.setResFile(clobFile);
	         
		   messengerService.svJobTemplateBlob(jobRecord);
		   selectedJobs = null;
	 }
	 
	
	 public void completedTemplateFileUploader(FileUploadEvent event) throws Exception{
		   
	log.info(selectedTaskRecord.getCandidateFirstName());
	System.out.println("This is what you should see" + selectedTaskRecord.getCandidateFirstName());	 
		 
	
	tkRecord = new TaskRecord();
		 
		System.out.println(selectedTaskRecord.candidateFirstName);
		System.out.println(selectedTaskRecord.candidateLastName);
		System.out.println(selectedTaskRecord.task_ID); 
		  
		 
		
		    log.info("I get Called to upload RESUME files");		    
	        FacesMessage msg = new FacesMessage("Succesful FILE " + event.getFile().getFileName() + " is uploaded.");  
		    FacesContext.getCurrentInstance().addMessage(null, msg);
		    newNameFile = event.getFile().getFileName();
		  
		    try {
	            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
	  		  
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		    long te = (long) 1.0;
		    category.setCatID(te);
	          messengerService.passID(category);
	          tkRecord.settask_ID(selectedTaskRecord.task_ID);
	          tkRecord.setResFile(clobFile);	          

	         
		   messengerService.UploadCompletedTemplate(tkRecord);
		   selectedCandidateInfo = null;

		   
	 } 
	 
	 
	  public void radiobutton(){
		 		  
		  switch (number){
		  case 0:
			  break;
		  case 1: showFileUpLoaderDisable = Boolean.TRUE;
		          canFileUpLoaderDisable = Boolean.FALSE; 
		          showContractUpLoaderDisable = Boolean.FALSE;
		          showResumeUpLoaderDisable = Boolean.FALSE; 
		          Long t = (long) 1.0;
		          category.setCatID(t);
		          messengerService.passID(category);
			  break;
		  case 2: canFileUpLoaderDisable = Boolean.TRUE;
		          showFileUpLoaderDisable = Boolean.FALSE;
		          showContractUpLoaderDisable = Boolean.FALSE;
		          showResumeUpLoaderDisable = Boolean.FALSE; 
		          long tt = (long) 2.0;
		          category.setCatID(tt);
		          messengerService.passID(category);
		      break;
		  case 3: showContractUpLoaderDisable = Boolean.TRUE;  
		          showFileUpLoaderDisable = Boolean.FALSE;
		          canFileUpLoaderDisable = Boolean.FALSE;
		          showResumeUpLoaderDisable = Boolean.FALSE; 
		          long ttt = (long) 3.0;
		          category.setCatID(ttt);
		          messengerService.passID(category);
		      break;    
		  case 4: showResumeUpLoaderDisable = Boolean.TRUE; 
		          showContractUpLoaderDisable = Boolean.FALSE;
                  showFileUpLoaderDisable = Boolean.FALSE;
                  canFileUpLoaderDisable = Boolean.FALSE;
                  long tttt = (long) 4.0;
                  category.setCatID(tttt);
                  messengerService.passID(category);       
			  break;
		  case 5: 
			  showResumeUpLoaderDisable = Boolean.FALSE;
			  showDatatableDisabledNew = Boolean.TRUE;
			  showDatatableDisabledExisting = Boolean.FALSE;
			  long ttttt = (long) 5.0;
			  category.setCatID(ttttt);
			  messengerService.passID(category);
			  break;
		  case 6: 
			  showResumeUpLoaderDisable = Boolean.FALSE;
			  showDatatableDisabledExisting = Boolean.TRUE;
			  showDatatableDisabledNew = Boolean.FALSE;
			  long tttttt = (long) 6.0;
			  category.setCatID(tttttt);
			  messengerService.passID(category);
			  break;
		  case 7:
			  showResumeUpLoaderDisable = Boolean.FALSE;
			  showDatatableDisabledExisting = Boolean.FALSE;
			  showDatatableDisabledNew = Boolean.FALSE;
			  showFileUpLoaderDisable = Boolean.FALSE;
	          canFileUpLoaderDisable = Boolean.FALSE; 
	          showContractUpLoaderDisable = Boolean.FALSE;
	          long ttttttt = (long) 7.0;
			  category.setCatID(ttttttt);
			  messengerService.passID(category);
		  }
		  
	  }
		  
	
	
	
	//Login Method
	/*public void Login(ActionEvent e) throws java.io.IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/massMessenger/j_spring_security_check?j_username=" + userId + "&j_password=" + password);
		 System.out.println("DO I get Called");
         System.out.println("The User name is: " + userId + " password : " + password);
        
    }*/
	 
			
	 public void save(ActionEvent actionEvent) {  
	        //Persist user  
		    candidateInfo = new CandidateInfo();
		  
		    FacesMessage msg = new FacesMessage("Successful", "Welcome :"  );  
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
	
	        currentLevel = 1; 
	        clearValues();
	    }  
	 
	public String saveContractData(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		nameuser = auth.getName();
		System.out.println(nameuser);
		
		log.info("saving the Contract following data");
		log.info(contractTitle);
		log.info(contractAgency);
		log.info(contractClass);
		log.info(nameuser);
		log.info(buisnessdevFirstname);
		log.info(buisnessdevLastname);
		log.info(buisnessdevPhonenumber);
		log.info(buisnessdevEmail);
		
		// SAVE CONTRACT FILE
		contractRecord.setContractTitle(contractTitle);
		contractRecord.setContractAgency(contractAgency);
		contractRecord.setContractClass(contractClass);
		contractRecord.setContractStatus(contractStatus);
		contractRecord.setNameuser(nameuser);
		/*contractRecord.setBuisnessdevFirstname(buisnessdevFirstname);
		contractRecord.setBuisnessdevLastname(buisnessdevLastname);
		contractRecord.setBuisnessdevPhonenumber(buisnessdevPhonenumber);
		contractRecord.setBuisnessdevEmail(buisnessdevEmail);*/
		
		messengerService.saveContract(contractRecord);
			
		
		
		log.info(" new contract data saved");
		addMessage("Successfully Saved: "+ contractRecord.getContractTitle() + " record!");
		clearValues();
		return null;
	}
	
	public String saveTaskData(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		nameuser = auth.getName();
		System.out.println(nameuser);	
		

		log.info("saving the Task Data");
		log.info(candidateFirstName);
		log.info(candidateLastName);
		log.info(jobTitle);
		log.info(jobDescription);
		log.info(adminComments);
		//log.info(tkRecord.getDueDate());
		

		//SAVE TASK
		tkRecord.setJobID(selectedJobAll.getJobID());
		tkRecord.setCandidateFirstName(tkRecord.getCandidateFirstName());
		tkRecord.setCandidateLastName(tkRecord.getCandidateLastName());
		tkRecord.setJobTitle(selectedJobAll.getJobTitle());
		tkRecord.setJobDescription(selectedJobAll.getJobDescription());
		tkRecord.setAdminComments(adminComments);
		tkRecord.setNameuser(nameuser);
		tkRecord.setcandinfoResume(tkRecord.getcandinfoResume());
		tkRecord.setDueDate(tkRecord.getDueDate());
		tkRecord.setJobTemplate(tkRecord.getJobTemplate());
	
		
		messengerService.saveTaskRecord(tkRecord);
		
		
			/*try {
				mailService.sendMail("kunta.little@acnc-md.com", "little230@gmail.com","Task Assigned", 
						  "Dear Tara" +",\n\n " +
				
						  "You have been designated to work on task"+ selectedJobAll.getJobID() +". "+
						 "Please click on the link below to open your task. (www.massMessenger.com) Task to be completed no later than" +" " +tkRecord.getDueDate()+ ".");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
		
		
		log.info(" Task has been assigned");
		addMessage("Task has been assigned to Tara!");
		 allAddedTaskInfo.remove(tkRecord);
		clearValues();
		return null;
	}
	
	
	
public String SaveCompletedTask(){
		
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		nameuser = auth.getName();
		System.out.println(nameuser);	
		
		log.info("Saving data for completed task");
		
		System.out.println(selectedTaskRecord.getCandidateFirstName());
		System.out.println(selectedTaskRecord.getCandidateLastName());
		System.out.println(selectedTaskRecord.getJobTitle());
		System.out.println(selectedTaskRecord.getNameuser());

		
		//SAVE TASK		
		selectedTaskRecord.setCandidateFirstName(selectedTaskRecord.getCandidateFirstName());
		selectedTaskRecord.setCandidateLastName(selectedTaskRecord.getCandidateLastName());
	
		selectedTaskRecord.setJobTitle(selectedTaskRecord.getJobTitle());
		selectedTaskRecord.setNameuser(selectedTaskRecord.getNameuser());
		selectedTaskRecord.setDateCompleted(selectedTaskRecord.getDateCompleted());

			
		
		messengerService.saveCompletedTask(selectedTaskRecord);
		
		
			/*try {
				mailService.sendMail("kunta.little@acnc-md.com", "little230@gmail.com","A Task Has Been Completed", "MotherFucker");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
		
		
		log.info("Task Has Been Completed");
		addMessage("You Have Completed A Task!");
		clearValues();
		return null;
	}
	
	
	
	
	// Delete if not being used
	 public String onFlowProcess(FlowEvent event) { 
	    	log.info("Current wizard step:" + event.getOldStep());  
	        log.info("Next step:" + event.getNewStep());  
	          
	        if(skip) {  
	            skip = false;   //reset in case user goes back  
	            return "confirm";  
	        }  
	        else {  
	            return event.getNewStep();  
	        }  
	    }  
	
	
	// Delete if not being used..
     public void save() { 		  
	        FacesContext context = FacesContext.getCurrentInstance(); 	       
	        context.addMessage(null, new FacesMessage("Successfully Updated: "+ selectedContract.getContractTitle() + " record!" ));  
	         
	    }  
	
    	  
     public void addMessage(String summary) {  
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null));  
	    }  

	public String addLinkedCanandJobs(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		nameuser = auth.getName();
		System.out.println(nameuser);
		
		lpAEMP = new EmailRepo();
		
		for(CandidateInfo cr : allSelectedCandidateInfo){
			lpAEMP = new EmailRepo();
			
			lpAEMP.setJobID(selectedJob.getJobID());
			log.info ("Job ID: " + lpAEMP.getJobID());
			lpAEMP.setJobTitle(selectedJob.getJobTitle());
			log.info ("Job Title: " + lpAEMP.getJobTitle());
			lpAEMP.setJobDescription(selectedJob.getJobDescription());
			log.info ("Job Description: " + lpAEMP.getJobDescription());
			lpAEMP.setCandidateEmail(cr.getCandinfoEmail());
			log.info ("Candidate Email: " + lpAEMP.getCandidateEmail());
			lpAEMP.setCandidateFirstName(cr.getCandinfoFirstName());
			log.info ("Candidate First Name: " + lpAEMP.getCandidateFirstName());
			lpAEMP.setCandidateLastName(cr.getCandinfoLastName());
			log.info ("Candidate Last Name: " + lpAEMP.getCandidateLastName());
			lpAEMP.setCandidateID(cr.getCandinfo_id());
			log.info ("Candidate ID: " + lpAEMP.getCandidateID());
			lpAEMP.setNameuser(nameuser);
			
			allAddedEmailsInfo.add(lpAEMP);
			
		}
		
		
		
		
		allSelectedCandidateInfo = new ArrayList<CandidateInfo>();
		selectedJob = new JobRecord();
		addMessage("Selected Data stored! Please click on the Selected Data tab to continue with mass email.");		
		return null;
	}
	
public String addLinkedCanandJobsExisting(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		nameuser = auth.getName();
		System.out.println(nameuser);
		
		lpAEMP = new EmailRepo();
		
		for(CandidateInfo cr : allSelectedCandidateInfoExisting){
			lpAEMP = new EmailRepo();
			
			lpAEMP.setJobID(selectedJobExisting.getJobID());
			log.info ("Job ID: " + lpAEMP.getJobID());
			lpAEMP.setJobTitle(selectedJobExisting.getJobTitle());
			log.info ("Job Title: " + lpAEMP.getJobTitle());
			lpAEMP.setJobDescription(selectedJobExisting.getJobDescription());
			log.info ("Job Description: " + lpAEMP.getJobDescription());
			lpAEMP.setCandidateEmail(cr.getCandinfoEmail());
			log.info ("Candidate Email: " + lpAEMP.getCandidateEmail());
			lpAEMP.setCandidateFirstName(cr.getCandinfoFirstName());
			log.info ("Candidate First Name: " + lpAEMP.getCandidateFirstName());
			lpAEMP.setCandidateLastName(cr.getCandinfoLastName());
			log.info ("Candidate Last Name: " + lpAEMP.getCandidateLastName());
			lpAEMP.setCandidateID(cr.getCandinfo_id());
			log.info ("Candidate ID: " + lpAEMP.getCandidateID());
			lpAEMP.setNameuser(nameuser);
			
			allAddedEmailsInfoExisting.add(lpAEMP);
			
		}
		
		
		
		
		allSelectedCandidateInfoExisting = new ArrayList<CandidateInfo>();
		selectedJobExisting = new JobRecord();
		addMessage("e with mass email.Selected Data stored! Please click on the Selected Data tab to continu");		
		return null;
	}
	


public String addLinkedCanandJobsTask(){
	
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	nameuser = auth.getName();
	System.out.println(nameuser);
	
	
	tkRecord = new TaskRecord();
	
	for(CandidateInfo cr : allSelectedCandidateInfoTask){
		tkRecord = new TaskRecord();
		
		
		
		
		tkRecord.setJobID(selectedJobAll.getJobID());
		log.info ("Job ID: " + tkRecord.getJobID());
		tkRecord.setJobTitle(selectedJobAll.getJobTitle());
		log.info ("Job Title: " + tkRecord.getJobTitle());
		tkRecord.setJobDescription(selectedJobAll.getJobDescription());
		log.info ("Job Description: " + tkRecord.getJobDescription());

		tkRecord.setJobTemplate(selectedJobAll.getJobTemplate());
		log.info("Job Template" + tkRecord.getJobTemplate());
		
		tkRecord.setAdminComments(adminComments);
	
		
		tkRecord.settask_ID(tkRecord.gettask_ID());
		log.info("Task ID:" + tkRecord.gettask_ID());
		
		tkRecord.setCandidateEmail(cr.getCandinfoEmail());
		log.info ("Candidate Email: " + tkRecord.getCandidateEmail());
		tkRecord.setCandidateFirstName(cr.getCandinfoFirstName());
		log.info ("Candidate First Name: " + tkRecord.getCandidateFirstName());
		tkRecord.setCandidateLastName(cr.getCandinfoLastName());
		log.info ("Candidate Last Name: " + tkRecord.getCandidateLastName());
		tkRecord.setCandidateID(cr.getCandinfo_id());
		log.info ("Candidate ID: " + tkRecord.getCandidateID());
		tkRecord.setNameuser(nameuser);
		
		tkRecord.setcandinfoResume(cr.getCandinfoResume());
		log.info ("Candidate's Resume: " + tkRecord.getcandinfoResume());
		
		
		allAddedTaskInfo.add(tkRecord);
		
	}
	
	
	
	allSelectedCandidateInfoTask = new ArrayList<CandidateInfo>();
	selectedJob = new JobRecord();
	addMessage("Selected Data stored! Please click on the Selected Data tab to continue with mass email.");		
	return null;
}

	
	public String sendToEmailService(){
		
		
		
		for(EmailRepo er  : allAddedEmailsInfoExisting){
		
		try {
			mailService.sendMail("kunta.little@acnc-md.com", er.getCandidateEmail(), "Urgent Job Opening for " + er.getJobTitle(), 
					" Dear " + er.getCandidateFirstName() +" " + er.getCandidateLastName() + ",\n\n" +
							" I am a representative of Advanced Computer and Network\n" +
							" Concepts, LLC, our company is currently looking for talented,\n" +
							" dynamic, and motivated " + er.getJobTitle() + " to become part of our\n" +
							" technical team. After reviewing your resume on clearancejobs.com\n" +
							" we were very pleased with your skill set and your experience.\n\n" +
							
							"Here is a Description of the Job--" +er.getJobDescription() +
							
							" As part of our initial screening process, we would like for\n" +
							" you to provide the following information:\n\n" +
							" - what is your clearance level?\n" +
							" - are you still seeking new opportunities, if so what is your\n" +
							" availability?\n" +
							" - provide us with your most current resume.\n" +
							" - provide a good contact number and time to best reach you at.\n" +
							" - provide a desired salary range and/or hourly contracting 1099 rate.\n\n" +
			" We look forward to speaking with you further about potential\n" +
			" opportunities.\n" +
			" ACNC L.L.C. is a steadily growing small business that\n" +
			" specializes in intelligence, engineering, and information\n" +
			" technology. As a government contracting company, we have\n" +
			" subcontracts supporting clients throughout the Intelligence\n" +
			" Community in the MD, DC, and VA area. We maintain a small-\n" +
			"company feel while also offering benefits and opportunities\n" +
			" competitive with larger companies in the industry. A preview\n" +
			" of the offered benefits:\n\n " +
			"Medical insurance is the choice of a PPO plan or an Open\n" +
			" Access HMO plan with a health savings account funded 100% by\n" +
			" ACNC L.L.C..\n" +
			" ACNC L.L.C. pays 100% of employee health coverage.\n" +
			" ACNC L.L.C. matches your 401(K) up to 5% contribution after\n" +
			" inital 90 day start date.\n" +
			" ACNC L.L.C. pays up to $4,500 a year for any training and\n" +
			" education you want to receive.\n" +
			" 15 days paid vacation, 5 days sick leave, 24 hours of facility\n" +
			" closing paid leave (natural disasters and storms), and 10\n" +
			" holidays a year- all paid leave time.\n" +
			" Health insurance, life insurance, short and long term\n" +
			" disability plans.\n\n" +
			" Vr\n" +
			" Human Resource Representative\n" +
			" Advanced Computer and Network Concepts, L.L.C.\n" + 
			" 5707 Calverton St, Suite 2B\n" +
			" Catonsville, MD 21228\n" +
			" Voice:  410.294.8369 | 410.298.0887\n");
			System.out.println("Sending your email now");
		
			
						
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		

	
		}
		
		messengerService.saveEmailQueue(allAddedEmailsInfoExisting);
		allAddedEmailsInfoExisting = new ArrayList<EmailRepo>();
		allEmailPosted = new ArrayList<EmailRepo>();		
		allEmailPosted = messengerService.getAllEmailRepos();
		addMessage("Selected Data sent out to the email service and sotred to reposiotry auto emails!");
		return null;
		
		
	}

	
	
	
	
public String sendToEmailServiceExisting(){
		
		
		
		for(EmailRepo er  : allAddedEmailsInfo){
		
		try {
			mailService.sendMail("kunta.little@acnc-md.com", er.getCandidateEmail(), "Urgent Job Opening for " + er.getJobTitle(), 
					" Dear " + er.getCandidateFirstName() +" " + er.getCandidateLastName() + ",\n\n" +
							" I am a representative of Advanced Computer and Network\n" +
							" Concepts, LLC, our company is currently looking for talented,\n" +
							" dynamic, and motivated " + er.getJobTitle() + " to become part of our\n" +
							" technical team. After reviewing your resume on clearancejobs.com\n" +
							" we were very pleased with your skill set and your experience.\n\n" +
							
							"Here is a Description of the Job--" +er.getJobDescription() +
							
							" As part of our initial screening process, we would like for\n" +
							" you to provide the following information:\n\n" +
							" - what is your clearance level?\n" +
							" - are you still seeking new opportunities, if so what is your\n" +
							" availability?\n" +
							" - provide us with your most current resume.\n" +
							" - provide a good contact number and time to best reach you at.\n" +
							" - provide a desired salary range and/or hourly contracting 1099 rate.\n\n" +
			" We look forward to speaking with you further about potential\n" +
			" opportunities.\n" +
			" ACNC L.L.C. is a steadily growing small business that\n" +
			" specializes in intelligence, engineering, and information\n" +
			" technology. As a government contracting company, we have\n" +
			" subcontracts supporting clients throughout the Intelligence\n" +
			" Community in the MD, DC, and VA area. We maintain a small-\n" +
			"company feel while also offering benefits and opportunities\n" +
			" competitive with larger companies in the industry. A preview\n" +
			" of the offered benefits:\n\n " +
			"Medical insurance is the choice of a PPO plan or an Open\n" +
			" Access HMO plan with a health savings account funded 100% by\n" +
			" ACNC L.L.C..\n" +
			" ACNC L.L.C. pays 100% of employee health coverage.\n" +
			" ACNC L.L.C. matches your 401(K) up to 5% contribution after\n" +
			" inital 90 day start date.\n" +
			" ACNC L.L.C. pays up to $4,500 a year for any training and\n" +
			" education you want to receive.\n" +
			" 15 days paid vacation, 5 days sick leave, 24 hours of facility\n" +
			" closing paid leave (natural disasters and storms), and 10\n" +
			" holidays a year- all paid leave time.\n" +
			" Health insurance, life insurance, short and long term\n" +
			" disability plans.\n\n" +
			" Vr\n" +
			" Human Resource Representative\n" +
			" Advanced Computer and Network Concepts, L.L.C.\n" + 
			" 5707 Calverton St, Suite 2B\n" +
			" Catonsville, MD 21228\n" +
			" Voice:  410.294.8369 | 410.298.0887\n");
			System.out.println("Sending your email now");
		
			
						
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		
		//mailService.sendAlertMail("Exception occurred");
	
		}
		
		messengerService.saveEmailQueue(allAddedEmailsInfo);
		allAddedEmailsInfo = new ArrayList<EmailRepo>();
		allEmailPosted = new ArrayList<EmailRepo>();		
		allEmailPosted = messengerService.getAllEmailRepos();
		addMessage("Selected Data sent out to the email service and sotred to reposiotry auto emails!");
		return null;
		
	}
	
	
	
	
  public String SendToEmailService3() {
	
	  for(CandidateInfo ci: allCandidatesEmail){
	  
		  try {

		mailService.sendMail("little230@gmail.com",ci.getCandinfoEmail(), "hey wassup", "nothing much");
		System.out.println("Sending Your Email Now!!!!!!!!!");	                                               
		}
	   catch (MessagingException e)
	   {
    e.printStackTrace();
	}
	catch (Exception e)
	{
	e.printStackTrace();
	
	} 
    addMessage("Successfully Sent Email" + ci.getCandinfoFirstName() + ci.getCandinfoLastName() + "Candidates!");
	}
	return null;
					
  }

    
	
	
	public String addLinkedConandBDD(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		nameuser = auth.getName();
		System.out.println(nameuser);
		
		lpAEMP2 = new EmailRepo2();
		
		for(BusinessRecord br : allSelectedBusiness){
			lpAEMP2 = new EmailRepo2();
			
			lpAEMP2.setContractID(selectedContract.getContractID());
			log.info ("Contract ID: " + lpAEMP2.getContractID());
			lpAEMP2.setContractTitle(selectedContract.getContractTitle());
			log.info ("Contract Title: " + lpAEMP2.getContractTitle());
			lpAEMP2.setContractAgency(selectedContract.getContractAgency());
			log.info ("Contract Agency: " + lpAEMP2.getContractAgency());
			lpAEMP2.setBusinessEmail(br.getBusinessEmail());
			log.info ("Business Email: " + lpAEMP2.getBusinessEmail());
			lpAEMP2.setBusinessFirstName(br.getBusinessFirstName());
			log.info ("Business First Name: " + lpAEMP2.getBusinessFirstName());
			lpAEMP2.setBusinessLastName(br.getBusinessLastName());
			log.info ("Business Last Name: " + lpAEMP2.getBusinessLastName());
			lpAEMP2.setBusinessID(br.getBusinessID());
			log.info ("Business ID: " + lpAEMP2.getBusinessID());
			lpAEMP2.setNameuser(nameuser);
			
		allAddedEmailsInfo2.add(lpAEMP2);
			
		}
		
		allSelectedBusiness = new ArrayList<BusinessRecord>();
		
		selectedContract = new ContractRecord();
		setShowSelectedLst2(Boolean.TRUE);
		setShowSelectedLstDisable2(Boolean.FALSE);		
		addMessage("Selected Data stored! Please click on the Selected Data tab to continue with mass email.");		
		return null;
	}
	
	public String removeAction(EmailRepo2 emailRepo2) {
		 
	    System.out.println("Do I get called to delele a row ");
		allAddedEmailsInfo2.remove(emailRepo2);
		clearValues();
		return null;
	}
	
	public String removeAction2(EmailRepo emailRepo) {
		 
	    System.out.println("Do I get called to delele a row ");
		allAddedEmailsInfoExisting.remove(emailRepo);
		//clearValues();
		return null;
	}
	
	public String removeAction3(EmailRepo emailRepo) {
		 
	    System.out.println("Do I get called to delele a row ");
	    allAddedEmailsInfo.remove(emailRepo);
		//clearValues();
		return null;
	}
	
	public String removeAction4(TaskRecord tkRecord) {
		 
	    System.out.println("Do I get called to delele a row ");
	    allAddedTaskInfo.remove(tkRecord);
		//clearValues();
		return null;
	}
	
	
	

	
    public String sendToEmailService2(){
		
		
		
		for(EmailRepo2 er : allAddedEmailsInfo2){
		
		try {
			mailService.sendMail("careers@acnc-md.com", er.getBusinessEmail(), "Urgent Job Opening for " + er.getContractTitle(), 
					" Hello " + er.getBusinessFirstName() +" " + er.getBusinessLastName() + ",\n\n" +
							" I would like to take this opportunity to introduce myself. My name is " +
							"Kunta A. Little and I represent a small minority veteran " +
							"owned company called Advanced Computer and Network " +
							"Concepts (ACNC), L.L.C.  My counterparts and I would like to " +
							"discuss teaming opportunities with your company for services we can " +
							"render to assist you on your new " + er.getContractTitle() + " contract.  " +
							"We are eager to discuss any current and future " + er.getBusinessFirstName()  + " teaming " +
							"opportunities with your company.  Thank you for your time and we look " +
							"forward to partnering with you and your company." +
							"Attached you will find a copy of Advanced Computer and Network " +
							"Concepts (ACNC), L.L.C. Capability Statement.\n\n " +
							
			
			" Vr\n" +
			" Human Resource Representative\n" +
			" Advanced Computer and Network Concepts, L.L.C.\n" + 
			" 5707 Calverton St, Suite 2B\n" +
			" Catonsville, MD 21228\n" +
			" Voice:  410.294.8369 | 410.298.0887\n");
				
			
			
		
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		
		//mailService.sendAlertMail("Exception occurred");

				
		}
		
		messengerService.saveEmailQueue2(allAddedEmailsInfo2);
		allAddedEmailsInfo2 = new ArrayList<EmailRepo2>();
		allEmailPosted2 = new ArrayList<EmailRepo2>();		
		allEmailPosted2 = messengerService.getAllEmailRepos2();
		showSelectedLst2 = Boolean.FALSE;
		showSelectedLstDisable2 = Boolean.TRUE;
		addMessage("Selected Data sent out to the email service and sotred to reposiotry auto emails!");
		return null;
		
	}
	
    
    
	public void resetValues() {	
		
		log.info ("Resetting values");
		
		allBuisnessContracts = new ArrayList<ContractRecord>();
		
//		allSelectedPOC = new ArrayList<ContractRecord>();
//		
//		allSelectedPOC = messengerService.getSelectedPOCInfo(passID);
//		
//		allSelectedJobs = new ArrayList<ContractsJob>();
//		
//		allSelectedJobs = messengerService.getSelectedJobInfo(passID);
//		
		allBuisnessContracts = messengerService.getAllBuisnessContract();
		
		//NEW
		allJobPosted = new ArrayList<JobRecord>();
		
		allJobPosted = messengerService.getAllJobs();
		
		//Existing
		allJobPostedExisting = new ArrayList<JobRecord>();
		
		allJobPostedExisting = messengerService.getAllJobs2();
		
		setAllJobPriority(new ArrayList<ContractsJob>());
		
		setAllJobPriority(messengerService.getAllJobPriority());
		
		
		allSelectedCandidateInfoAll = new ArrayList<CandidateInfo>();
		
		
		allContractPosted = new ArrayList<ContractRecord>();
		
		allContractPosted = messengerService.getAllContracts();
		
		allEducationPosted = new ArrayList<EducationResume>();
	    
	    allEducationPosted = messengerService.getAllEducation();
		
		allEmployPosted = new ArrayList<EmployResume>();
		
		allEmployPosted = messengerService.getAllEmploy();
		
		allCertPosted = new ArrayList<CertResume>();
		
		allCertPosted = messengerService.getAllCert();
		
		allSelectedCerts = new ArrayList<CertResume>();
		
		allSelectedCerts = messengerService.getSelectedCertsInfo(passID);
		
		selectedCerts = new ArrayList<CertResume>();
		
		allBusinessPosted = new ArrayList<BusinessRecord>();
		
		allBusinessPosted = messengerService.getAllBusiness();
		
        allQueryRecord = new ArrayList<CandidateInfo>();
		
		allQueryRecord = messengerService.getAllQueryRecord();		
	
		//NEW
		allCandidateInfoPosted = new ArrayList<CandidateInfo>();
		
		allCandidateInfoPosted = messengerService.getAllCandidateInfo();
		//Existing
		 allCandidateInfoPostedExisting = new ArrayList<CandidateInfo>();
		
		 allCandidateInfoPostedExisting = messengerService.getAllCandidateInfo2();
		 
		 //All
		 allCandidateInfoPostedAll = new ArrayList<CandidateInfo>();
		 
		 allCandidateInfoPostedAll = messengerService.getAllCandidateInfoAll();
		 
		 
		//TASK
		allCandidateInfoPostedTask = new ArrayList<CandidateInfo>();
			
		allCandidateInfoPostedTask = messengerService.getAllCandidateInfoTask(); 
		 
		//TASK INFO SIDE
		allTaskInfoPosted = new ArrayList<TaskRecord>();
		
		allTaskInfoPosted = messengerService.getAllTaskInfoPosted();
		
		
		//Completed Task
		allCompletedTask = new ArrayList<TaskRecord>();
		
		allCompletedTask = messengerService.getAllCompletedTask();
		
		allCanceledTask = new ArrayList<TaskRecord>();
		
		allCanceledTask = messengerService.getAllCanceledTask();
		
		
		allSelectedComments = new ArrayList<SaveComment>();
		
		allSelectedComments = messengerService.getAllSelectedComments();
		
		
		educationResume = new EducationResume();
		
		certResume = new CertResume();
		
		employResume = new EmployResume();
		
		candidateInfo = new CandidateInfo();
		
		selectedJob = new JobRecord();
		
		selectedJobAll = new JobRecord();
		
		selectedBusiness = new BusinessRecord();
		
		selectedContract = new ContractRecord();
		
		selectedCandidateInfo = new CandidateInfo();
		
		selectedTaskRecord = new TaskRecord();
		
		selectedCertResume = new CertResume();
		
		jobRecord = new JobRecord();
		
		contractRecord = new ContractRecord();
		
		businessRecord = new BusinessRecord();
		 
		updateContract = new ContractRecord(); 	
		
		candidateFirstName = null;
		
		candidateLastName = null;
		
		candidateEmail = null;
		
		jobDescription = null;
		
		jobTitle = null;
		
		degree = null;
		
		schoolName = null;
		
		major = null;
		
		compYear = null;
		
		certName = null;
		
		certYear = null;
		
		resFile = null;
		
		compName = null;
		
		title = null;
		
		projectRole = null;
		
		projDescpt = null; 
		
		fromMonth = null;
		
		fromYear = null;
		
		toMonth = null;
		
		toYear = null;
		
		projfromMonth = null;
		
		projfromYear = null;
		
		projtoMonth = null;
		
		projtoYear = null;
		
        candinfoFirstName = null;
		
	    candinfoLastName = null;
		
	    candinfoMiddle = null;
		
	    candinfoEmail = null;
	    
	    candinfoNumber = null;
		
	    candinfoQuestone = null;
		
	    candinfoQuesttwo = null;
		
	    candinfoQuesthree = null; 
		
	    candinfoQuestfour = null;
		
		candinfoQuestfive = null;
		
		candinfoSkills = null;
		
		candinfoClearance = null;
		
		candinfoStatus = null;
		
		nameuser = null;
		
		businessFirstName = null;
		
		businessLastName = null;
		
	    businessPhoneNumber = null;
		
	    businessEmail = null;
		
		
		catName = null;
		
		catDescrp = null;
		
		cateDate = null;
		
		fileClob = null; 
		
		newName = null;
		
		oldName = null;
		
		dateCreated = null;
		
		candinfoFile = null;
		
		showSelectedLstDisable = Boolean.FALSE;
		
		showFileUpLoaderDisable = Boolean.FALSE;
		
		showEditButtonDisable = Boolean.FALSE;
		
		canFileUpLoaderDisable = Boolean.FALSE;
		
		showContractUpLoaderDisable = Boolean.FALSE;
		
		showResumeUpLoaderDisable = Boolean.FALSE;
		
		showDatatableDisabledNew = Boolean.FALSE;
		
		showDatatableDisabledExisting = Boolean.FALSE;
		
		
		
		contractTitle = null;
		
	     contractAgency = null;
		
		contractClass = null;

		contractDate = null;
		
		 contractStatus = null;
		 
		 jobID = null;
			
		  jobDate = null;
			
		 jobTitle = null;
			
		 jobDescription = null;
			
		 jobRequest = null;		

		 jobRequesttype = null;
			
		 jobSkill = null;
			
		 jobPosition = null;
			
		 jobClear = null;
			
		 jobLocation = null;
			
		 jobSkillsMandatory = null;
			
		 jobSkillsDesired = null;
			
		 jobCertRequired = null;
			
		 jobConus = null;
			
		 jobOconus = null;
			
		 //jobbHoursYear = (Long) null;
			
		 jobSchedComment = null;
			
		 jobNonPub = null;
			
		 jobMissionCritical = null;
			
		 jobWorkNight = null;
			
		 jobLocalTravel = null;
			
		 jobPager = null;
			
		 jobPagerComment = null;
			
		 jobWorkHoliday = null;
			
		 jobWorkWeekend = null;
			
		 jobShiftWork = null;
			
		 jobWarZone = null;
			
		 jobCoop = null;
		
		 buisnessdevFirstname = null;
			
		 buisnessdevLastname = null;
			
		 buisnessdevPhonenumber = null;
			
		 buisnessdevEmail = null;
		
		log.info ("Successfully reset Records object.");
	}
	
	public String clearValues() {
		resetValues();
		return "next";
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

	public Date getCateDate() {
		return cateDate;
	}

	public void setCateDate(Date cateDate) {
		this.cateDate = cateDate;
	}

	public Blob getFileClob() {
		return fileClob;
	}

	public void setFileClob(Blob fileClob) {
		this.fileClob = fileClob;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public MessengerService getMessengerService() {
		return messengerService;
	}

	public void setMessengerService(MessengerService messengerService) {
		this.messengerService = messengerService;
	}

	public List<JobRecord> getAllJobPosted() {
		return allJobPosted;
	}

	public void setAllJobPosted(List<JobRecord> allJobPosted) {
		this.allJobPosted = allJobPosted;
	}
	
	public String getJobPriority() {
		return jobPriority;
	}

	public void setJobPriority(String jobPriority) {
		this.jobPriority = jobPriority;
	}

	public JobRecord getJobRecord() {
		return jobRecord;
	}

	public void setJobRecord(JobRecord jobRecord) {
		this.jobRecord = jobRecord;
	}

	public Boolean getShowEmailPushOption() {
		return showEmailPushOption;
	}

	public void setShowEmailPushOption(Boolean showEmailPushOption) {
		this.showEmailPushOption = showEmailPushOption;
	}

	public Boolean getShowMainData() {
		return showMainData;
	}

	public void setShowMainData(Boolean showMainData) {
		this.showMainData = showMainData;
	}

	public Boolean getShowJobDataInput() {
		return showJobDataInput;
	}

	public void setShowJobDataInput(Boolean showJobDataInput) {
		this.showJobDataInput = showJobDataInput;
	}

	public Boolean getShowCanDataInput() {
		return showCanDataInput;
	}

	public void setShowCanDataInput(Boolean showCanDataInput) {
		this.showCanDataInput = showCanDataInput;
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

	public List<EmailRepo> getAllEmailPosted() {
		return allEmailPosted;
	}

	public void setAllEmailPosted(List<EmailRepo> allEmailPosted) {
		this.allEmailPosted = allEmailPosted;
	}

	public List<EmailRepo> getAllAddedEmailsInfo() {
		return allAddedEmailsInfo;
	}

	public void setAllAddedEmailsInfo(List<EmailRepo> allAddedEmailsInfo) {
		this.allAddedEmailsInfo = allAddedEmailsInfo;
	}
	
	
	
	public List<EmailRepo> getAllAddedEmailsInfoExisting() {
		return allAddedEmailsInfoExisting;
	}

	public void setAllAddedEmailsInfoExisting(
			List<EmailRepo> allAddedEmailsInfoExisting) {
		this.allAddedEmailsInfoExisting = allAddedEmailsInfoExisting;
	}

	public List<EmailRepo2> getAllEmailPosted2() {
		return allEmailPosted2;
	}

	public void setAllEmailPosted2(List<EmailRepo2> allEmailPosted2) {
		this.allEmailPosted2 = allEmailPosted2;
	}

	public List<EmailRepo2> getAllAddedEmailsInfo2() {
		return allAddedEmailsInfo2;
		
	}

	public void setAllAddedEmailsInfo2(List<EmailRepo2> allAddedEmailsInfo2) {
		this.allAddedEmailsInfo2 = allAddedEmailsInfo2;
	}

	public EmailRepo2 getLpAEMP2() {
		return lpAEMP2;
	}

	public void setLpAEMP2(EmailRepo2 lpAEMP2) {
		this.lpAEMP2 = lpAEMP2;
	}
	
	public List<ContractsJob> getAllSelectedJobs() {
		return allSelectedJobs;
	}

	public void setAllSelectedJobs(List<ContractsJob> allSelectedJobs) {
		this.allSelectedJobs = allSelectedJobs;
	}
	
	
		
	public List<ContractRecord> getAllSelectedPOC() {
		return allSelectedPOC;
	}

	public void setAllSelectedPOC(List<ContractRecord> allSelectedPOC) {
		this.allSelectedPOC = allSelectedPOC;
	}

	public List<ContractsJob> getAllSelectedJobs2() {
		return allSelectedJobs2;
	}

	public void setAllSelectedJobs2(List<ContractsJob> allSelectedJobs2) {
		this.allSelectedJobs2 = allSelectedJobs2;
	}

	public JobRecord getSelectedJob() {
		return selectedJob;
	}

	public void setSelectedJob(JobRecord selectedJob) {
		this.selectedJob = selectedJob;
	}
	

	public JobRecord getSelectedJobAll() {
		return selectedJobAll;
	}

	public void setSelectedJobAll(JobRecord selectedJobAll) {
		this.selectedJobAll = selectedJobAll;
	}

	public EmailRepo getLpAEMP() {
		return lpAEMP;
	}

	public void setLpAEMP(EmailRepo lpAEMP) {
		this.lpAEMP = lpAEMP;
	}

	
	public TaskRecord gettkRecord(){
		return tkRecord;
	}
	
	public void settkRecord(TaskRecord tkRecord){
		this.tkRecord = tkRecord;
	}
	
	
	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public Boolean getShowSelectedLst() {
		return showSelectedLst;
	}

	public void setShowSelectedLst(Boolean showSelectedLst) {
		this.showSelectedLst = showSelectedLst;
	}

	public Boolean getShowSelectedLstDisable() {
		return showSelectedLstDisable;
	}

	public void setShowSelectedLstDisable(Boolean showSelectedLstDisable) {
		this.showSelectedLstDisable = showSelectedLstDisable;
	}
	
	
	public CandidateInfo getUpdateCandidateinfo() {
		return updateCandidateinfo;
	}

	public void setUpdateCandidateinfo(CandidateInfo updateCandidateinfo) {
		this.updateCandidateinfo = updateCandidateinfo;
	}

	public Boolean getShowFileUpLoaderDisable() {
		return showFileUpLoaderDisable;
	}

	public void setShowFileUpLoaderDisable(Boolean showFileUpLoaderDisable) {
		this.showFileUpLoaderDisable = showFileUpLoaderDisable;
	}
	
	
	public Boolean getCanFileUpLoaderDisable() {
		return canFileUpLoaderDisable;
	}

	public void setCanFileUpLoaderDisable(Boolean canFileUpLoaderDisable) {
		this.canFileUpLoaderDisable = canFileUpLoaderDisable;
	}
	
	

	public Boolean getShowContractUpLoaderDisable() {
		return showContractUpLoaderDisable;
	}

	public void setShowContractUpLoaderDisable(Boolean showContractUpLoaderDisable) {
		this.showContractUpLoaderDisable = showContractUpLoaderDisable;
	}
	
	public Boolean getShowResumeUpLoaderDisable() {
		return showResumeUpLoaderDisable;
	}

	public void setShowResumeUpLoaderDisable(Boolean showResumeUpLoaderDisable) {
		this.showResumeUpLoaderDisable = showResumeUpLoaderDisable;
	}
	
	

	public Boolean getshowDatatableDisabledNew() {
		return showDatatableDisabledNew;
	}

	public void setshowDatatableDisabledNew(Boolean showDatatableDisabledNew) {
		this.showDatatableDisabledNew = showDatatableDisabledNew;
	}
	
	

	public Boolean getShowDatatableDisabledExisting() {
		return showDatatableDisabledExisting;
	}

	public void setShowDatatableDisabledExisting(Boolean showDatatableDisabledExisting) {
		this.showDatatableDisabledExisting = showDatatableDisabledExisting;
	}

	public JobRecord getSelectedJobs() {
		return selectedJobs;
	}

	public void setSelectedJobs(JobRecord selectedJobs) {
		this.selectedJobs = selectedJobs;
	}

	public List<ContractRecord> getAllContractPosted() {
		return allContractPosted;
	}

	public void setAllContractPosted(List<ContractRecord> allContractPosted) {
		this.allContractPosted = allContractPosted;
	}

	public List<ContractRecord> getAllSelectedContracts() {
		return allSelectedContracts;
	}

	public void setAllSelectedContracts(List<ContractRecord> allSelectedContracts) {
		this.allSelectedContracts = allSelectedContracts;
	}
	
	
	public String getContractTitle() {
		return contractTitle;
	}

	public void setContractTitle(String contractTitle) {
		this.contractTitle = contractTitle;
	}

	public BusinessRecord getBusinessRecord() {
		return businessRecord;
	}

	public void setBusinessRecord(BusinessRecord businessRecord) {
		this.businessRecord = businessRecord;
	}

	public String getBusinessFirstName() {
		return businessFirstName;
	}

	public void setBusinessFirstName(String businessFirstName) {
		this.businessFirstName = businessFirstName;
	}

	public String getBusinessLastName() {
		return businessLastName;
	}

	public void setBusinessLastName(String businessLastName) {
		this.businessLastName = businessLastName;
	}

	public String getBusinessEmail() {
		return businessEmail;
	}

	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}

	public List<BusinessRecord> getAllBusinessPosted() {
		return allBusinessPosted;
	}

	public void setAllBusinessPosted(List<BusinessRecord> allBusinessPosted) {
		this.allBusinessPosted = allBusinessPosted;
	}

	public CandidateInfo getUpdateCandidate() {
		return updateCandidate;
	}

	public void setUpdateCandidate(CandidateInfo updateCandidate) {
		this.updateCandidate = updateCandidate;
	}

	public DeleteCandidate getDeleteCandidate() {
		return deleteCandidate;
	}

	public void setDeleteCandidate(DeleteCandidate deleteCandidate) {
		this.deleteCandidate = deleteCandidate;
	}

	public List<BusinessRecord> getAllSelectedBusiness() {
		return allSelectedBusiness;
	}

	public void setAllSelectedBusiness(List<BusinessRecord> allSelectedBusiness) {
		this.allSelectedBusiness = allSelectedBusiness;
	}

	public String getBusinessPhoneNumber() {
		return businessPhoneNumber;
	}

	public void setBusinessPhoneNumber(String businessPhoneNumber) {
		this.businessPhoneNumber = businessPhoneNumber;
	}

	public BusinessRecord getSelectedBusiness() {
		return selectedBusiness;
	}

	public void setSelectedBusiness(BusinessRecord selectedBusiness) {
		this.selectedBusiness = selectedBusiness;
	}

	public ContractRecord getUpdateContract() {
		return updateContract;
	}

	public void setUpdateContract(ContractRecord updateContract) {
		this.updateContract = updateContract;
	}

	public ContractRecord getSelectedContract() {
		return selectedContract;
	}

	public void setSelectedContract(ContractRecord selectedContract) {
		this.selectedContract = selectedContract;
	}

	public BusinessRecord getUpdateBusiness() {
		return updateBusiness;
	}

	public void setUpdateBusiness(BusinessRecord updateBusiness) {
		this.updateBusiness = updateBusiness;
	}

	public DeleteContract getDeleteContract() {
		return deleteContract;
	}

	public void setDeleteContract(DeleteContract deleteContract) {
		this.deleteContract = deleteContract;
	}

	public DeleteBusiness getDeleteBusiness() {
		return deleteBusiness;
	}

	public void setDeleteBusiness(DeleteBusiness deleteBusiness) {
		this.deleteBusiness = deleteBusiness;
	}

	public ContractRecord getContractRecord() {
		return contractRecord;
	}

	public void setContractRecord(ContractRecord contractRecord) {
		this.contractRecord = contractRecord;
	}

	public String getContractAgency() {
		return contractAgency;
	}

	public void setContractAgency(String contractAgency) {
		this.contractAgency = contractAgency;
	}

	public ContractRecord getSelectedContracts() {
		return selectedContracts;
	}

	public void setSelectedContracts(ContractRecord selectedContracts) {
		this.selectedContracts = selectedContracts;
	}

	public Boolean getShowSelectedLst2() {
		return showSelectedLst2;
	}

	public void setShowSelectedLst2(Boolean showSelectedLst2) {
		this.showSelectedLst2 = showSelectedLst2;
	}

	public Boolean getShowSelectedLstDisable2() {
		return showSelectedLstDisable2;
	}

	public void setShowSelectedLstDisable2(Boolean showSelectedLstDisable2) {
		this.showSelectedLstDisable2 = showSelectedLstDisable2;
	}

	public CandidateInfo getCandidateInfo() {
		return candidateInfo;
	}

	public void setCandidateInfo(CandidateInfo candidateInfo) {
		this.candidateInfo = candidateInfo;
	}

	
	public EducationResume getEducationResume() {
		return educationResume;
	}

	public void setEducationResume(EducationResume educationResume) {
		this.educationResume = educationResume;
	}

	public EmployResume getEmployResume() {
		return employResume;
	}

	public void setEmployResume(EmployResume employResume) {
		this.employResume = employResume;
	}

			
	public Boolean getAddingEducation() {
		return addingEducation;
	}

	public void setAddingEducation(Boolean addingEducation) {
		this.addingEducation = addingEducation;
	}

	public List<CandidateInfo> getCnd() {
		return cnd;
	}

	public void setCnd(List<CandidateInfo> cnd) {
		this.cnd = cnd;
	}
	
	

	public List<TaskRecord> getComT() {
		return comT;
	}

	public void setComT(List<TaskRecord> comT) {
		this.comT = comT;
	}

	public Boolean getAddingResume() {
		return addingResume;
	}

	public void setAddingResume(Boolean addingResume) {
		this.addingResume = addingResume;
	}

	public List<CertResume> getCert() {
		return cert;
	}

	public void setCert(List<CertResume> cert) {
		this.cert = (ArrayList<CertResume>) cert;
	}

	public CertResume getCertResume() {
		return certResume;
	}

	public void setCertResume(CertResume certResume) {
		this.certResume = certResume;
	}

	
	public Boolean getAddingEmployment() {
		return addingEmployment;
	}

	public void setAddingEmployment(Boolean addingEmployment) {
		this.addingEmployment = addingEmployment;
	}

	public Boolean getAddingCertification() {
		return addingCertification;
	}

	public void setAddingCertification(Boolean addingCertification) {
		this.addingCertification = addingCertification;
	}

	public List<EmployResume> getMainER() {
		return mainER;
	}

	public void setMainER(List<EmployResume> mainER) {
		this.mainER = mainER;
	}

	public List<CandidateInfo> getAllCandidateInfoPosted() {
		return allCandidateInfoPosted;
	}

	public void setAllCandidateInfoPosted(List<CandidateInfo> allCandidateInfoPosted) {
		this.allCandidateInfoPosted = allCandidateInfoPosted;
	}

	public List<EducationResume> getEmpEdu() {
		return empEdu;
	}

	public void setEmpEdu(List<EducationResume> empEdu) {
		this.empEdu = empEdu;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Date getCompYear() {
		return compYear;
	}

	public void setCompYear(Date compYear) {
		this.compYear = compYear;
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

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProjectRole() {
		return projectRole;
	}

	public void setProjectRole(String projectRole) {
		this.projectRole = projectRole;
	}

	public String getProjDescpt() {
		return projDescpt;
	}

	public void setProjDescpt(String projDescpt) {
		this.projDescpt = projDescpt;
	}

	public Date getFromMonth() {
		return fromMonth;
	}

	public void setFromMonth(Date fromMonth) {
		this.fromMonth = fromMonth;
	}

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToMonth() {
		return toMonth;
	}

	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}

	public Date getToYear() {
		return toYear;
	}

	public void setToYear(Date toYear) {
		this.toYear = toYear;
	}

	public Date getProjfromMonth() {
		return projfromMonth;
	}

	public void setProjfromMonth(Date projfromMonth) {
		this.projfromMonth = projfromMonth;
	}

	public String getProjfromYear() {
		return projfromYear;
	}

	public void setProjfromYear(String projfromYear) {
		this.projfromYear = projfromYear;
	}

	public String getProjtoMonth() {
		return projtoMonth;
	}

	public void setProjtoMonth(String projtoMonth) {
		this.projtoMonth = projtoMonth;
	}

	public Date getProjtoYear() {
		return projtoYear;
	}

	public void setProjtoYear(Date projtoYear) {
		this.projtoYear = projtoYear;
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

	public int getCurrentLevel() {  
        return currentLevel;  
    }  
  
    public void setCurrentLevel(int currentLevel) {  
        this.currentLevel = currentLevel;  
    }

	public CandidateInfo getSelectedCandidateInfo() {
		return selectedCandidateInfo;
	}

	public void setSelectedCandidateInfo(CandidateInfo selectedCandidateInfo) {
		this.selectedCandidateInfo = selectedCandidateInfo;
	}
	
	
	public TaskRecord getSelectedTaskRecord() {
		return selectedTaskRecord;
	}

	public void setSelectedTaskRecord(TaskRecord selectedTaskRecord) {
		this.selectedTaskRecord = selectedTaskRecord;
	}

	public CertResume getSelectedCertResume() {
		return selectedCertResume;
	}

	public void setSelectedCertResume(CertResume selectedCertResume) {
		this.selectedCertResume = selectedCertResume;
	}


	
	public List<CandidateInfo> getAllSelectedCandidateInfoTask() {
		return allSelectedCandidateInfoTask;
	}

	public void setAllSelectedCandidateInfoTask(
			List<CandidateInfo> allSelectedCandidateInfoTask) {
		this.allSelectedCandidateInfoTask = allSelectedCandidateInfoTask;
	}
	

	public List<CandidateInfo> getAllSelectedCandidateInfo() {
		return allSelectedCandidateInfo;
	}

	public void setAllSelectedCandidateInfo(
			List<CandidateInfo> allSelectedCandidateInfo) {
		this.allSelectedCandidateInfo = allSelectedCandidateInfo;
	}
	
	

	public List<CandidateInfo> getAllSelectedCandidateInfoAll() {
		return allSelectedCandidateInfoAll;
	}

	public void setAllSelectedCandidateInfoAll(
			List<CandidateInfo> allSelectedCandidateInfoAll) {
		this.allSelectedCandidateInfoAll = allSelectedCandidateInfoAll;
	}

	
	
	
	public List<TaskRecord> getAllSelectedTaskInfo() {
		return allSelectedTaskInfo;
	}

	public void setAllSelectedTaskInfo(List<TaskRecord> allSelectedTaskInfo) {
		this.allSelectedTaskInfo = allSelectedTaskInfo;
	}

	public List<CandidateInfo> getAllSelectedCandidateInfoExisting() {
		return allSelectedCandidateInfoExisting;
	}

	public void setAllSelectedCandidateInfoExisting(
			List<CandidateInfo> allSelectedCandidateInfoExisting) {
		this.allSelectedCandidateInfoExisting = allSelectedCandidateInfoExisting;
	}

	public List<EducationResume> getAllEducationPosted() {
		return allEducationPosted;
	}

	public void setAllEducationPosted(List<EducationResume> allEducationPosted) {
		this.allEducationPosted = allEducationPosted;
	}

	public List<EmployResume> getAllEmployPosted() {
		return allEmployPosted;
	}

	public void setAllEmployPosted(List<EmployResume> allEmployPosted) {
		this.allEmployPosted = allEmployPosted;
	}

	public List<CertResume> getAllCertPosted() {
		return allCertPosted;
	}

	public void setAllCertPosted(List<CertResume> allCertPosted) {
		this.allCertPosted = allCertPosted;
	}
	
	
	public List<CertResume> getAllSelectedCerts() {
		return allSelectedCerts;
	}

	public void setAllSelectedCerts(List<CertResume> allSelectedCerts) {
		this.allSelectedCerts = allSelectedCerts;
	}	
	

	public List<CertResume> getSelectedCerts() {
		return selectedCerts;
	}

	public void setSelectedCerts(List<CertResume> selectedCerts) {
		this.selectedCerts = selectedCerts;
	}

	public List<CertResume> getAllQueryCertPosted() {
		return allQueryCertPosted;
	}

	public void setAllQueryCertPosted(List<CertResume> allQueryCertPosted) {
		this.allQueryCertPosted = allQueryCertPosted;
	}

	public List<CandidateInfo> getAllQueryRecord() {
		return allQueryRecord;
	}

	public void setAllQueryRecord(List<CandidateInfo> allQueryRecord) {
		this.allQueryRecord = allQueryRecord;
	}

	public Long getCandinfo_id() {
		return candinfo_id;
	}

	public void setCandinfo_id(Long candinfo_id) {
		this.candinfo_id = candinfo_id;
	}

	public Long getEdID() {
		return edID;
	}

	public void setEdID(Long edID) {
		this.edID = edID;
	}

	public Long getCerID() {
		return cerID;
	}

	public void setCerID(Long cerID) {
		this.cerID = cerID;
	}

	public Long getEmployID() {
		return employID;
	}

	public void setEmployID(Long employID) {
		this.employID = employID;
	}

	public Long getPassID() {
		return passID;
	}

	public void setPassID(Long passID) {
		this.passID = passID;
	}

	public List<EducationResume> getAllSelectedEducation() {
		return allSelectedEducation;
	}

	public void setAllSelectedEducation(List<EducationResume> allSelectedEducation) {
		this.allSelectedEducation = allSelectedEducation;
	}

	public List<EmployResume> getAllSelectedEmploy() {
		return allSelectedEmploy;
	}

	public void setAllSelectedEmploy(List<EmployResume> allSelectedEmploy) {
		this.allSelectedEmploy = allSelectedEmploy;
	}

	public boolean isValueOne() {
		return valueOne;
	}

	public void setValueOne(boolean valueOne) {
		this.valueOne = valueOne;
	}

	public boolean isValueTwo() {
		return valueTwo;
	}

	public void setValueTwo(boolean valueTwo) {
		this.valueTwo = valueTwo;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getfNameFile() {
		return fNameFile;
	}

	public void setfNameFile(String fNameFile) {
		this.fNameFile = fNameFile;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public List<ContractRecord> getCr() {
		return cr;
	}

	public void setCr(List<ContractRecord> cr) {
		this.cr = cr;
	}

	public List<BusinessRecord> getBr() {
		return br;
	}

	public void setBr(List<BusinessRecord> br) {
		this.br = br;
	}

	public List<Category> getCat() {
		return cat;
	}

	public void setCat(List<Category> cat) {
		this.cat = cat;
	}

	public List<FlatFile> getFlat() {
		return flat;
	}

	public void setFlat(List<FlatFile> flat) {
		this.flat = flat;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public FlatFile getFlatfile() {
		return flatfile;
	}

	public void setFlatfile(FlatFile flatfile) {
		this.flatfile = flatfile;
	}

	
	public byte[] getClobFile() {
		return clobFile;
	}

	public void setClobFile(byte[] clobFile) {
		this.clobFile = clobFile;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public Date getJobDate() {
		return jobDate;
	}

	public void setJobDate(Date jobDate) {
		this.jobDate = jobDate;
	}

	public String getJobStatusChange() {
		return jobStatusChange;
	}

	public void setJobStatusChange(String jobStatusChange) {
		this.jobStatusChange = jobStatusChange;
	}


	public Boolean getShowEditButtonDisable() {
		return showEditButtonDisable;
	}

	public void setShowEditButtonDisable(Boolean showEditButtonDisable) {
		this.showEditButtonDisable = showEditButtonDisable;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegFirstName() {
		return regFirstName;
	}

	public void setRegFirstName(String regFirstName) {
		this.regFirstName = regFirstName;
	}

	public String getRegusername() {
		return regusername;
	}

	public void setRegusername(String regusername) {
		this.regusername = regusername;
	}

	public String getRegpassword() {
		return regpassword;
	}

	public void setRegpassword(String regpassword) {
		this.regpassword = regpassword;
	}

	public String getRegLastName() {
		return regLastName;
	}

	public void setRegLastName(String regLastName) {
		this.regLastName = regLastName;
	}

	public String getRegEmail() {
		return regEmail;
	}

	public void setRegEmail(String regEmail) {
		this.regEmail = regEmail;
	}

	public Date getRegmoddate() {
		return regmoddate;
	}

	public void setRegmoddate(Date regmoddate) {
		this.regmoddate = regmoddate;
	}

	public Date getRegstartdate() {
		return regstartdate;
	}

	public void setRegstartdate(Date regstartdate) {
		this.regstartdate = regstartdate;
	}

/*	public String getJobHourPerYear() {
		return jobbHourPerYear;
	}

	public void setJobHourPerYear(String jobHourPerYear) {
		this.jobbHourPerYear = jobHourPerYear;
	}*/

	public String getJobCloseDate() {
		return jobCloseDate;
	}

	public void setJobCloseDate(String jobCloseDate) {
		jobCloseDate = this.jobCloseDate;
	}

	public String getJobUpdateDate() {
		return jobUpdateDate;
	}

	public void setJobUpdateDate(String jobUpdateDate) {
		jobUpdateDate = this.jobUpdateDate;
	}

	public DeleteCertResume getDeleteCertResume() {
		return deleteCertResume;
	}

	public void setDeleteCertResume(DeleteCertResume deleteCertResume) {
		this.deleteCertResume = deleteCertResume;
	}

	public String getContractClass() {
		return contractClass;
	}

	public void setContractClass(String contractClass) {
		this.contractClass = contractClass;
	}
	
	
	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public List<CandidateInfo> getAllCandidatesEmail() {
		return allCandidatesEmail;
	}

	public void setAllCandidatesEmail(List<CandidateInfo> allCandidatesEmail) {
		this.allCandidatesEmail = allCandidatesEmail;
	}

	public String getJobRequesttype() {
		return jobRequesttype;
	}

	public void setJobRequesttype(String jobRequesttype) {
		this.jobRequesttype = jobRequesttype;
	}

	public List<ContractsJob> getAllJobPriority() {
		return allJobPriority;
	}

	public void setAllJobPriority(List<ContractsJob> allJobPriority) {
		this.allJobPriority = allJobPriority;
	}

	public List<JobRecord> getAllContractTitles() {
		return allContractTitles;
	}

	public void setAllContractTitles(List<JobRecord> allContractTitles) {
		this.allContractTitles = allContractTitles;
	}

	public String getCandinfoNumber() {
		return candinfoNumber;
	}

	public void setCandinfoNumber(String candinfoNumber) {
		this.candinfoNumber = candinfoNumber;
	}

	public String getCandinfoClearance() {
		return candinfoClearance;
	}

	public void setCandinfoClearance(String candinfoClearance) {
		this.candinfoClearance = candinfoClearance;
	}
	
	

	public String getCandinfoStatus() {
		return candinfoStatus;
	}

	public void setCandinfoStatus(String candinfoStatus) {
		this.candinfoStatus = candinfoStatus;
	}

	public List<CandidateInfo> getAllCreatedDates() {
		return allCreatedDates;
	}

	public void setAllCreatedDates(List<CandidateInfo> allCreatedDates) {
		this.allCreatedDates = allCreatedDates;
	}

	public List<JobRecord> getAllJobPostedExisting() {
		return allJobPostedExisting;
	}

	public void setAllJobPostedExisting(List<JobRecord> allJobPostedExisting) {
		this.allJobPostedExisting = allJobPostedExisting;
	}

	public List<CandidateInfo> getAllCandidateInfoPostedExisting() {
		return allCandidateInfoPostedExisting;
	}

	public void setAllCandidateInfoPostedExisting(
			List<CandidateInfo> allCandidateInfoPostedExisting) {
		this.allCandidateInfoPostedExisting = allCandidateInfoPostedExisting;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LoginBean getLoginbean() {
		return loginbean;
	}

	public void setLoginbean(LoginBean loginbean) {
		this.loginbean = loginbean;
	}

	public String getNameuser() {
		return nameuser;
	}

	public void setNameuser(String nameuser) {
		this.nameuser = nameuser;
	}

	public ContractsJob getSelectedJobInfo() {
		return selectedJobInfo;
	}

	public void setSelectedJobInfo(ContractsJob selectedJobInfo) {
		this.selectedJobInfo = selectedJobInfo;
	}
	
	

	public ContractRecord getSelectedContractInfo() {
		return selectedContractInfo;
	}

	public void setSelectedContractInfo(ContractRecord selectedContractInfo) {
		this.selectedContractInfo = selectedContractInfo;
	}

	public byte[] getResFile() {
		return resFile;
	}

	public void setResFile(byte[] resFile) {
		this.resFile = resFile;
	}

	public String getCandinfoFile() {
		return candinfoFile;
	}

	public void setCandinfoFile(String candinfoFile) {
		this.candinfoFile = candinfoFile;
	}

	public JobRecord getSelectedJobExisting() {
		return selectedJobExisting;
	}

	public void setSelectedJobExisting(JobRecord selectedJobExisting) {
		this.selectedJobExisting = selectedJobExisting;
	}

	
	public Long getJobID() {
		return jobID;
	}

	public void setJobID(Long jobID) {
		this.jobID = jobID;
	}

	public String getJobRequest() {
		return jobRequest;
	}

	public void setJobRequest(String jobRequest) {
		this.jobRequest = jobRequest;
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

	public String getJobSchedComment() {
		return jobSchedComment;
	}

	public void setJobSchedComment(String jobSchedComment) {
		this.jobSchedComment = jobSchedComment;
	}

	public String getJobNonPub() {
		return jobNonPub;
	}

	public void setJobNonPub(String jobNonPub) {
		this.jobNonPub = jobNonPub;
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

	public long getJobbHoursYear() {
		return jobbHoursYear;
	}

	public void setJobbHoursYear(long jobbHoursYear) {
		this.jobbHoursYear = jobbHoursYear;
	}

	public EmailRepo2 getremove() {
		return selectedEmail2;
	}

	public void setSelectedEmail2(EmailRepo2 selectedEmail2) {
		this.selectedEmail2 = selectedEmail2;
	}

	
	
	public TaskRecord getSelectedTask() {
		return selectedTask;
	}

	public void setSelectedTask(TaskRecord selectedTask) {
		this.selectedTask = selectedTask;
	}

	public List<CandidateInfo> getAllCandidateInfoPostedAll() {
		return allCandidateInfoPostedAll;
	}

	public void setAllCandidateInfoPostedAll(List<CandidateInfo> allCandidateInfoPostedAll) {
		this.allCandidateInfoPostedAll = allCandidateInfoPostedAll;
	}

	public String getBuisnessdevFirstname() {
		return buisnessdevFirstname;
	}

	public void setBuisnessdevFirstname(String buisnessdevFirstname) {
		this.buisnessdevFirstname = buisnessdevFirstname;
	}

	public String getBuisnessdevLastname() {
		return buisnessdevLastname;
	}

	public void setBuisnessdevLastname(String buisnessdevLastname) {
		this.buisnessdevLastname = buisnessdevLastname;
	}

	public String getBuisnessdevPhonenumber() {
		return buisnessdevPhonenumber;
	}

	public void setBuisnessdevPhonenumber(String buisnessdevPhonenumber) {
		this.buisnessdevPhonenumber = buisnessdevPhonenumber;
	}

	public String getBuisnessdevEmail() {
		return buisnessdevEmail;
	}

	public void setBuisnessdevEmail(String buisnessdevEmail) {
		this.buisnessdevEmail = buisnessdevEmail;
	}

	public java.sql.Blob getCandinfoResume() {
		return candinfoResume;
	}

	public void setCandinfoResume(java.sql.Blob candinfoResume) {
		this.candinfoResume = candinfoResume;
	}

	public List<TaskRecord> getAllAddedTaskInfo() {
		return allAddedTaskInfo;
	}

	public void setAllAddedTaskInfo(List<TaskRecord> allAddedTaskInfo) {
		this.allAddedTaskInfo = allAddedTaskInfo;
	}

	public List<CandidateInfo> getAllCandidateInfoPostedTask() {
		return allCandidateInfoPostedTask;
	}

	public void setAllCandidateInfoPostedTask(List<CandidateInfo> allCandidateInfoPostedTask) {
		this.allCandidateInfoPostedTask = allCandidateInfoPostedTask;
	}

	public List<TaskRecord> getAllTaskInfoPosted() {
		return allTaskInfoPosted;
	}

	public void setAllTaskInfoPosted(List<TaskRecord> allTaskInfoPosted) {
		this.allTaskInfoPosted = allTaskInfoPosted;
	}

	public String getAdminComments() {
		return adminComments;
	}

	public void setAdminComments(String adminComments) {
		this.adminComments = adminComments;
	}

	
	public String getCancelComments() {
		return cancelComments;
	}

	public void setCancelComments(String cancelComments) {
		this.cancelComments = cancelComments;
	}

	
	
	public String getCommentString() {
		return commentString;
	}

	public void setCommentString(String commentString) {
		this.commentString = commentString;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}
	
	
	

	public Date getDateCanceled() {
		return dateCanceled;
	}

	public void setDateCanceled(Date dateCanceled) {
		this.dateCanceled = dateCanceled;
	}

	public List<TaskRecord> getAllCompletedTask() {
		return allCompletedTask;
	}

	public void setAllCompletedTask(List<TaskRecord> allCompletedTask) {
		this.allCompletedTask = allCompletedTask;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public List<JobRecord> getJobR() {
		return jobR;
	}

	public void setJobR(List<JobRecord> jobR) {
		this.jobR = jobR;
	}

	public List<TaskRecord> getAllCanceledTask() {
		return allCanceledTask;
	}

	public void setAllCanceledTask(List<TaskRecord> allCanceledTask) {
		this.allCanceledTask = allCanceledTask;
	}



	public Date getCreateUpdated() {
		return createUpdated;
	}

	public void setCreateUpdated(Date createUpdated) {
		this.createUpdated = createUpdated;
	}

	public SaveComment getSelectedComment() {
		return selectedComment;
	}

	public void setSelectedComment(SaveComment selectedComment) {
		this.selectedComment = selectedComment;
	}

	public List<SaveComment> getAllSelectedComments() {
		return allSelectedComments;
	}

	public void setAllSelectedComments(List<SaveComment> allSelectedComments) {
		this.allSelectedComments = allSelectedComments;
	}

	
	
	
	
	
}

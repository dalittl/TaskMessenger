package com.acnc.mm.domain.messenger;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.acnc.mm.dao.db.jdbc.MessengerDAOImpl;
import com.acnc.mm.services.mail.MailService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
  
/**
*
* @author DanteLittle
*/

public class FirstContactJob extends QuartzJobBean {
	
	
	// Mail Service used to Sending Email's
	private MailService mailService;

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
	private List<CandidateInfo> allCandidatesEmail;

	public List<CandidateInfo> getAllCandidatesEmail() {
		return allCandidatesEmail;
	}


	public void setAllCandidatesEmail(List<CandidateInfo> allCandidatesEmail) {
		this.allCandidatesEmail = allCandidatesEmail;
	}


	{
		allCandidatesEmail = new ArrayList<CandidateInfo>();
		
		
	}
	
	 

		@Override
		protected void executeInternal(JobExecutionContext context)
				throws JobExecutionException {

			 ArrayList<CandidateInfo> allCandidatesEmail = new ArrayList<CandidateInfo>();
		      
		        try {
		            // Step 1: Load the JDBC driver.
		            Class.forName("com.mysql.jdbc.Driver");
		 
		            // Step 2: Establish the connection to the database.
		            //String url = "jdbc:mysql://192.168.10.8:3306";
		            //java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.10.8:3306", "ace", "mypass");
		            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.10.188:3306", "root", "Qazwsx1234");

		            java.sql.Statement st = conn.createStatement();
		            ResultSet srs = st.executeQuery("SELECT email, firstname, lastname FROM massEmail.candinfo WHERE status=1");
		            while (srs.next()) {
		                CandidateInfo cand = new CandidateInfo();
		                cand.setCandinfoFirstName(srs.getString("FirstName"));
		                cand.setCandinfoLastName(srs.getString("LastName"));
		                cand.setCandinfoEmail(srs.getString("Email"));
		                allCandidatesEmail.add(cand);
		            }
		 
		            //Initialize Variable With ArrayList
		            for(CandidateInfo er: allCandidatesEmail){
		            	
		            	try {
							mailService.sendMail("little230@gmail.com", er.getCandinfoEmail(),"Urgent Job Opening","hey wassup");
							System.out.println("Sending Email to"+"  "+ er.getCandinfoFirstName() +"  "+er.getCandinfoLastName()+"  ");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		            
		            
		        //Catch Exception If There Is One
		        } catch (Exception e) {
		            System.err.println("Got an exception! ");
		            System.err.println(e.getMessage());
		        }
		    }
		
		
		}


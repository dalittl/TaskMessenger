package com.acnc.mm.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.acnc.mm.application.AuthenticationService;
import com.acnc.mm.domain.messenger.ContractRecord;



@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8257630497025659422L;
	
	private String username;
	private String password;
	public String nameuser;
	boolean isUser = false;
    boolean isAdmin = false;
	

	@ManagedProperty(value = "#{authenticationService}")
	private AuthenticationService authenticationService; 

	
	public String login() {

		boolean success = authenticationService.login(username, password);
		System.out.println("Username at LoginBean:" + username);
		
		if (success){
			 Authentication auth = SecurityContextHolder.getContext().getAuthentication();	
			 //auth.getAuthorities().equals("ROLE_USER");
				nameuser = auth.getName();		
				System.out.println(nameuser);
				
				if (auth.getAuthorities().toString().equals("[ROLE_ADMIN]")){
//				   System.out.println(auth.getAuthorities().toString().equals("[ROLE_ADMIN]"));
//				   System.out.println(auth.getAuthorities().toString());
				return "main.xhtml";
				}
				else if(auth.getAuthorities().toString().equals("[ROLE_USER]")) {
					
					return "tasks.xhtml";
					
				}
				
	

		}
		FacesContext.getCurrentInstance().addMessage(
			null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:",
					"Login or password incorrect."));
		 return "login.xhtml";
		
	}
	
	public String logout(){
		 authenticationService.logout();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", "You have been successfully logged out."));
        return "login.xhtml";
		
		
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

    

	public String getNameuser() {
		return nameuser;
	}


	public void setNameuser(String nameuser) {
		this.nameuser = nameuser;
	}


	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	
	
}

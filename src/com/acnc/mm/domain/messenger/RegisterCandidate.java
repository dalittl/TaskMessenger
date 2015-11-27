package com.acnc.mm.domain.messenger;

import java.io.Serializable;
import java.util.Date;

import org.apache.jasper.tagplugins.jstl.core.If;




public class RegisterCandidate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 449966358551853580L;
	private String username;
	private String password;
	private String FirstName;
	private String LastName;
	private String Email;
	private Date moddate;	
	private Date startdate;
	private Long enabled;
	private String UpdateUser;
	
	public RegisterCandidate(){ 
		
			
		
	}
	
   public RegisterCandidate(String username,String password,String FirstName,String LastName,String Email,Date moddate, Date startdate, Long enabled, String UpdateUser){ 
		
	   this.username=username;
	   this.password=password;
	   this.FirstName=FirstName;
	   this.LastName=LastName;
	   this.Email=Email;
	   this.moddate=moddate;
	   this.startdate=startdate;
	   this.enabled=enabled;
	   this.UpdateUser=UpdateUser;
	

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

public String getFirstName() {
	return FirstName;
}

public void setFirstName(String firstName) {
	FirstName = firstName;
}

public String getLastName() {
	return LastName;
}

public void setLastName(String lastName) {
	LastName = lastName;
}

public String getEmail() {
	return Email;
}

public void setEmail(String email) {
	Email = email;
}

public Date getModdate() {
	return moddate;
}

public void setModdate(Date moddate) {
	this.moddate = moddate;
}

public Date getStartdate() {
	return startdate;
}

public void setStartdate(Date startdate) {
	this.startdate = startdate;
}

public Long getEnabled() {
	return enabled;
}

public void setEnabled(Long enabled) {
	this.enabled = enabled;
}

public String getUpdateUser() {
	return UpdateUser;
}

public void setUpdateUser(String updateUser) {
	UpdateUser = updateUser;
}

}//end class

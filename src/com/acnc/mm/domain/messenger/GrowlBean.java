package com.acnc.mm.domain.messenger;

import java.awt.event.ActionEvent;

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;


public class GrowlBean {
	
	 private String text;  
     
	    public String getText() {  
	        return text;  
	    }  
	    public void setText(String text) {  
	        this.text = text;  
	    }  
	  
	    public void save(ActionEvent actionEvent) {  
	        FacesContext context = FacesContext.getCurrentInstance();  
	          
	        context.addMessage(null, new FacesMessage("Successful", "Edit " + text));  
	        //context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));  
	    }  

}

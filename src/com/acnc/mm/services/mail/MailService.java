package com.acnc.mm.services.mail;

import java.io.Serializable;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;





/**
 * @author Kunta Little
 *
 */
@Service("mailService")
public class MailService implements Serializable {
	
	
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -7949032054034544907L;
	
	@Autowired
    private MailSender mailSender;
    public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Autowired
    private SimpleMailMessage alertMailMessage;
    
    public void sendMail(String from, String to, String subject, String body) throws MessagingException {
        
        SimpleMailMessage message = new SimpleMailMessage();
         
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        
    }
    
    public void sendAlertMail(String alert) {
        
        SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);
        mailMessage.setText(alert);
        mailSender.send(mailMessage);
        
    }

	
}
	

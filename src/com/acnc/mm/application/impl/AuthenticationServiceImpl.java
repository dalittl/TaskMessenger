package com.acnc.mm.application.impl;

import java.io.Serializable;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("authenticationService")
public class AuthenticationServiceImpl implements com.acnc.mm.application.AuthenticationService, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2434229583406285254L;

	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager; // specific for Spring Security
	
	@Override
	public boolean login(String username, String password) {
		System.out.println("Boolean at Login Authenitcation impl:" + username);
		try {
			Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							username, password));
			if (authenticate.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(
						authenticate);				
				return true;
			}
		} catch (AuthenticationException e) {			
		}
		return false;
	}
	


	
	@Override
	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		/*SecurityContextHolder.clearContext();*/
	}
}

package com.acnc.mm.application.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acnc.mm.dao.db.jdbc.BaseSpringJdbcDAO;

@Service("userDetailsService")
public class UserDetailsServiceImpl extends BaseSpringJdbcDAO implements UserDetailsService {
	
	private HashMap<String, org.springframework.security.core.userdetails.User> users = new HashMap<String, org.springframework.security.core.userdetails.User>();
	

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		org.springframework.security.core.userdetails.User user = users.get(username);
		
		/*Collection<GrantedAuthority> adminAuthorities = new ArrayList<GrantedAuthority>();
		adminAuthorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		
		Collection<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>();
		userAuthorities.add(new GrantedAuthorityImpl("ROLE_REGISTERED"));
		
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		
		JdbcTemplate template = getJdbcTemplate();
		UserDetails user = new User(null, null, accountNonLocked, accountNonLocked, accountNonLocked, accountNonLocked, userAuthorities);
	
		user = users.get(username);
		*/
		
		/*UserDetails user = template.query("Select UserName =?, password =? from " + MessengerConstants.MM_SCHEMA_NAME + ".user " + "Where UserName ="+ user.getUsername() +
                "and password = " + user.getPassword(), new Object[]);
*/			
		 
		 if (user == null) {
			throw new UsernameNotFoundException("UserAccount for name \""
					+ username + "\" not found.");
		}
		
		return user;
	}
	
	
	@PostConstruct
	public void init() {
//		
//		for (GrantedAuthority grantedAuthority : authorities) {
//            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
//                isUser = true;
//                break;
//            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
//                isAdmin = true;
//                break;
//            }
//        }
// 
//        if (isUser) {
//            return "/homepage.html";
//        } else if (isAdmin) {
//            return "/console.html";
//        } else {
//            throw new IllegalStateException();
//        }
//    }
		
		Collection<GrantedAuthority> adminAuthorities = new ArrayList<GrantedAuthority>();
		adminAuthorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		
		Collection<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>();
		userAuthorities.add(new GrantedAuthorityImpl("ROLE_REGISTERED"));
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		
		// sample users with roles set
		users.put("admin", new org.springframework.security.core.userdetails.User("admin", "admin", enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, adminAuthorities));
		
		users.put("user", new org.springframework.security.core.userdetails.User("user", "user", enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, userAuthorities));
		
		
		
		 
	}
	

	
}

package com.projectmanager.pm.auditing;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


public class SpringSecurityAuditorAware implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
 
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return Optional.ofNullable(((User) authentication.getPrincipal()).getUsername());
		//return Optional.ofNullable("Ive");
		
    }
	
//	public Optional<User> getCurrentAuditor() {
//
//	    return Optional.ofNullable(SecurityContextHolder.getContext())
//				  .map(SecurityContext::getAuthentication)
//				  .filter(Authentication::isAuthenticated)
//				  .map(Authentication::getPrincipal)
//				  .map(User.class::cast);
//	  }
	
}

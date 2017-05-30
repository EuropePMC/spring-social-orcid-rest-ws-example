package org.europepmc.springsocial;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.europepmc.user.User;
import org.europepmc.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

public class SocialSignInAdapter implements SignInAdapter {
	
	UserDao userDao;
	
	@Autowired
	TokenBasedRememberMeServices myRememberMeServices;
	
	public SocialSignInAdapter(UserDao userDao) {
		this.userDao = userDao;
	}

	public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
    	
		User user = userDao.findUserById(localUserId);
  	
		RememberMeAuthenticationToken authentication = new RememberMeAuthenticationToken(MyWebSecurityConfiguration.REMEMBER_SERVICE_KEY, user.getUsername(), user.getGrantedAuthorities());        
		// Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getGrantedAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
       
//		myRememberMeServices.loginSuccess(
//                (HttpServletRequest) request.getNativeRequest(),
//                (HttpServletResponse) request.getNativeResponse(),
//                authentication);
		Cookie[] cookies = ((HttpServletRequest) request.getNativeRequest()).getCookies();
		if (cookies != null) {
		    for (int i = 0; i < cookies.length; i++) {
		        if ("do_remember_me".equalsIgnoreCase(cookies[i].getName())
		                && "true".equalsIgnoreCase(cookies[i].getValue())) {
		            System.out.println("Do remember me");
		            myRememberMeServices.onLoginSuccess(
		                    (HttpServletRequest) request.getNativeRequest(),
		                    (HttpServletResponse) request.getNativeResponse(),
		                    authentication);            
		        }
		    }
		}
		        
		for (ServiceProvider serviceProvider: ServiceProvider.values()) {
		    if (connection.getKey() != null 
		            && serviceProvider.getId().equalsIgnoreCase(connection.getKey().getProviderId())) {
		        return serviceProvider.getRedirectUrl();
		    }
		}
		
		return null;
    }
}
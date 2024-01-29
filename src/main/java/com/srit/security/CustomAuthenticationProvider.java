package com.srit.security;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.srit.model.User;
import com.srit.repo.CustomerCrudRepository;
import com.srit.repo.UserRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	Logger log;
	@Autowired
	SecurityService securityService;
	@Autowired
	CustomUserDetailsService service;
	@Autowired
	UserRepository repository;
//	@Value("${validdays}")
	private long validdays=3;
	@Autowired
	private Environment env;

	public CustomAuthenticationProvider() {
		this.log = LoggerFactory.getLogger((Class) CustomAuthenticationProvider.class);
	}

	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		this.log.debug("Entered into CustomAuthenticationProvider*********");
		Authentication auth = null;
		final String name = authentication.getName().toUpperCase();
		final String password = authentication.getCredentials().toString();
		this.log.debug("name*********" + name);
		System.out.println("name : "+name);
		
		try {
			final User user = this.repository.findByName(name);
			if (user == null) {
				throw new UsernameNotFoundException("INVALID CREDENTIALS");
			}
			this.log.info(" not null name*********" + user.getName());
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.log.info("**************LastLoginTime******************" + user.getLastlogintime());
			long numberOfDays = 0L;
			this.log.info("user Get By EmployeeId is *********" + user.getName());
				UserDetails oliveUser = null;
				oliveUser = this.service.loadUserByUsername(user.getName());
				if (oliveUser == null) {
					System.out.println("Sorry No Authorisation");
					this.log.debug("Sorry No Authorisation");
					throw new UsernameNotFoundException("INVALID CREDENTIALS");
				}
				auth = (Authentication) new UsernamePasswordAuthenticationToken((Object) oliveUser, (Object) password,
						oliveUser.getAuthorities());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return auth;
	}

	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private long daysBetween(final Date one, final Date two) {
		final long difference = (one.getTime() - two.getTime()) / 86400000L;
		return Math.abs(difference);
	}
}

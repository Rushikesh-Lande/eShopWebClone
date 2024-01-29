// 
// Decompiled by Procyon v0.5.36
// 

package com.srit.security;

import java.util.EventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService customUserDetailsService;
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return (PasswordEncoder) new BCryptPasswordEncoder();
	}

	public void configure(final WebSecurity web) throws Exception {

		web.ignoring().antMatchers(new String[] { "/resources/**","/static/**","/update-quantity","/increment","/addToCart","/cart-list","/item-details2","/remove-from-cart" , "/webjars/**", "/assets/**", "/insert" , "/create" , "/save", "/list", "/details/{id}", "/create-item","/submitData","/uSignIn","/showHome","/saveCategory","/stockForm","/generate-bill","/saveStock","/homePage","/stockList","/generate-bill-form","/showCustomer","/search","/search2","/billItemList","/modern-list","/item","/addCustomer","/addToBill","/saveBillDetails"});
	}

	protected void configure(final HttpSecurity http) throws Exception {
		System.out.println("**********************************5************************************************");
		((HttpSecurity) ((HttpSecurity) ((FormLoginConfigurer) ((FormLoginConfigurer) ((FormLoginConfigurer) ((FormLoginConfigurer) ((HttpSecurity) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) http
				.authorizeRequests().antMatchers(new String[] { "/", "/forgotPwd", "/resetPwd" , "/static/**"})).permitAll()
						.anyRequest()).authenticated().and()).formLogin()
								.defaultSuccessUrl("/home")).failureUrl("/login?error")).permitAll())).and())
												.logout()
												.logoutRequestMatcher(
														(RequestMatcher) new AntPathRequestMatcher("/logout"))
												.invalidateHttpSession(true).permitAll().and()).sessionManagement()
														.invalidSessionUrl("/login").maximumSessions(1).sessionRegistry(sessionRegistry());
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		final SessionRegistry sessionRegistry = (SessionRegistry) new SessionRegistryImpl();
		System.out.println("SESSION REGISTRTY* ************************************" + sessionRegistry);
		return sessionRegistry;
	}

	@Bean
	public static ServletListenerRegistrationBean httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean((EventListener) new HttpSessionEventPublisher());
	}

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("**********************************4************************************************");
		auth.authenticationProvider((AuthenticationProvider) this.customAuthenticationProvider);
	}

}

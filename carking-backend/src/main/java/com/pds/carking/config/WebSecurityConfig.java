package com.pds.carking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pds.carking.exception.InvalidCredentialsException;
import com.pds.carking.exception.handlers.InvalidCredentialsExceptionHandler;
import com.pds.carking.model.Employee;
import com.pds.carking.security.JwtRequestFilter;
import com.pds.carking.services.EmployeeService;

import javassist.NotFoundException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Autowired
	private EmployeeService employeeService;

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(new AbstractUserDetailsAuthenticationProvider() {

			@Override
			protected void additionalAuthenticationChecks(UserDetails userDetails,
					UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
			}

			@Override
			protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
					throws AuthenticationException {
				Employee employee = null;
				try {
					employee = employeeService.loginEmployee(username, authentication.getCredentials().toString());
				} catch (NotFoundException e) {
					throw new InvalidCredentialsException("User not found");
				}
				
				if (employee != null) {
					User user = new User(employee.getUsername(), employee.getPassword(),
							authentication.getAuthorities());
					return user;
				} else {
					throw new InvalidCredentialsException("User not found");
				}
			}
		});
	}

	@Bean
	public AuthenticationEntryPoint authenticationFailureHandler() {
		return new InvalidCredentialsExceptionHandler();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers("/swagger-ui.html", "/v2/api-docs", "/swagger-resources/**", "/configuration/**",
						"/webjars/**")
				.permitAll().anyRequest().authenticated().and()
				.exceptionHandling().authenticationEntryPoint(authenticationFailureHandler());
	//			.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}

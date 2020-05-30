package com.pds.carking.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pds.carking.exception.NotFoundException;
import com.pds.carking.exception.handlers.AccessDeniedExceptionHandler;
import com.pds.carking.model.Employee;
import com.pds.carking.services.EmployeeService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private EmployeeService employeeService;

	private final boolean REQUIRED_TOKEN = false;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (REQUIRED_TOKEN) {

			final String requestTokenHeader = request.getHeader("Authorization");
			String username = null;

			if (requestTokenHeader != null) {
				username = jwtTokenUtil.getUsernameFromToken(requestTokenHeader);
			}
			try {
				if (username != null) {
					if (jwtTokenUtil.validateToken(requestTokenHeader, username)) {
						Employee employee = null;

						employee = employeeService.getEmployeeFromUsername(username);

						if (employee != null) {
							UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
									employee.getUsername(), employee.getPassword());
							SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
						}
					}
				}

				filterChain.doFilter(request, response);
			} catch (NotFoundException e) {
				new AccessDeniedExceptionHandler(response, e.getMessage());
			}

		} else {
			filterChain.doFilter(request, response);
		}

	}

}

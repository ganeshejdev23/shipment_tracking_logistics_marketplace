package com.shipment.logistics.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shipment.logistics.entity.User;
import com.shipment.logistics.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)

			throws ServletException, IOException {

		String path = request.getRequestURI();

		System.out.println("REQUEST PATH: " + path);

		// PUBLIC URLS (NO TOKEN REQUIRED)
		if (path.startsWith("/auth") || path.equals("/tracking-page") || path.startsWith("/tracking")
				|| path.startsWith("/topic") || path.startsWith("/app") || path.equals("/favicon.ico")
				|| path.startsWith("/ws")

				// SWAGGER
				|| path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs")
				|| path.startsWith("/swagger-resources")) {

			filterChain.doFilter(request, response);

			return;
		}

		// GET AUTH HEADER
		String authHeader = request.getHeader("Authorization");

		// NO TOKEN
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {

			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			response.getWriter().write("Unauthorized");

			return;
		}

		// EXTRACT TOKEN
		String token = authHeader.substring(7);

		try {

			// GET USERNAME FROM TOKEN
			String username = jwtUtil.extractUsername(token);

			// GET USER FROM DATABASE
			User user = userRepository.findByUsername(username);

			if (user == null) {

				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

				response.getWriter().write("User not found");

				return;
			}

			// CREATE AUTHENTICATION
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(

					username,

					null,

					List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())));

			// SET LOGIN USER
			SecurityContextHolder.getContext().setAuthentication(authentication);

			filterChain.doFilter(request, response);

		} catch (Exception e) {

			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			response.getWriter().write("Invalid Token");
		}
	}
}
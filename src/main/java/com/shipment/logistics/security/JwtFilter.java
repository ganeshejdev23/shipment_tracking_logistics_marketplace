package com.shipment.logistics.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)

			throws ServletException, IOException {

		String path = request.getRequestURI();

		// PUBLIC APIs
		if (path.startsWith("/auth") || path.equals("/tracking-page")) {

			filterChain.doFilter(request, response);

			return;
		}

		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {

			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			response.getWriter().write("Unauthorized");

			return;
		}

		String token = authHeader.substring(7);

		try {

			String username = jwtUtil.extractUsername(token);

			// TELL SPRING USER IS LOGGED IN
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,
					Collections.emptyList());

			SecurityContextHolder.getContext().setAuthentication(authentication);

			filterChain.doFilter(request, response);

		} catch (Exception e) {

			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			response.getWriter().write("Invalid Token");
		}
	}
}
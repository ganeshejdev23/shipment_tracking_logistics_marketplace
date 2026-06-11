package com.shipment.logistics.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.cors(cors -> {})

				.csrf(csrf -> csrf.disable())

				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.authorizeHttpRequests(auth -> auth

						// PUBLIC URLS
						.requestMatchers("/auth/**", "/tracking-page", "/tracking/**", "/favicon.ico", "/error",

								// SWAGGER
								"/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html")
						.permitAll()

						// SHIPPER
						.requestMatchers("/api/loads/**").hasRole("SHIPPER")

						// CARRIER
						.requestMatchers("/api/bids/**").hasRole("CARRIER")

						// CUSTOMER & CARRIER
						.requestMatchers("/api/tracking/**").hasAnyRole("SHIPPER", "CUSTOMER", "CARRIER")

						.anyRequest().authenticated())

				.httpBasic(Customizer.withDefaults())

				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOrigins(List.of("http://localhost:3000"));

		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));

		configuration.setAllowedHeaders(List.of("*"));

		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}

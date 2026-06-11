package com.shipment.logistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shipment.logistics.dto.RegisterRequestDto;
import com.shipment.logistics.dto.LoginRequestDto;
import com.shipment.logistics.dto.LoginResponseDto;
import com.shipment.logistics.entity.User;
import com.shipment.logistics.service.AuthService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication", description = "APIs for user registration and login")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Operation(summary = "Register new user", description = "Registers shipper or carrier account")

	// REGISTER
	@PostMapping("/register")
	public User register(@Valid @RequestBody RegisterRequestDto request) {

		User user = new User();

		user.setUsername(request.getUsername());

		user.setPassword(request.getPassword());

		user.setRole(request.getRole());

		return authService.register(user);
	}

	@Operation(summary = "Login user", description = "Authenticates user and returns JWT token")

	@PostMapping("/login")
	public LoginResponseDto login(@Valid @RequestBody LoginRequestDto request) {

		String token = authService.login(request.getUsername(), request.getPassword());

		User user = authService.findByUsername(request.getUsername());

		LoginResponseDto response = new LoginResponseDto();

		response.setUsername(user.getUsername());

		response.setRole(user.getRole());

		response.setToken(token);

		return response;
	}
}

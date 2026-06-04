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

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	// REGISTER
	@PostMapping("/register")
	public User register(@RequestBody RegisterRequestDto request) {

		User user = new User();

		user.setUsername(request.getUsername());

		user.setPassword(request.getPassword());

		user.setRole(request.getRole());

		return authService.register(user);
	}

	@PostMapping("/login")
	public LoginResponseDto login(@RequestBody LoginRequestDto request) {

		String token = authService.login(request.getUsername(), request.getPassword());

		User user = authService.findByUsername(request.getUsername());

		LoginResponseDto response = new LoginResponseDto();

		response.setUsername(user.getUsername());

		response.setRole(user.getRole());

		response.setToken(token);

		return response;
	}
}

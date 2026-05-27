package com.shipment.logistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shipment.logistics.entity.User;
import com.shipment.logistics.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	// REGISTER
	@PostMapping("/register")
	public User register(@RequestBody User user) {

		return authService.register(user);
	}

	@PostMapping("/login")
	public String login(@RequestBody User user) {

		return authService.login(user.getUsername(), user.getPassword());
	}
}

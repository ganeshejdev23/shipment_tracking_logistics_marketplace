package com.shipment.logistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shipment.logistics.entity.User;
import com.shipment.logistics.repository.UserRepository;

import com.shipment.logistics.security.JwtUtil;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtUtil jwtUtil;

	// REGISTER USER
	public User register(User user) {

		return userRepository.save(user);
	}

	public String login(String username, String password) {

		User user = userRepository.findByUsername(username);

		if (user != null && user.getPassword().equals(password)) {

			return jwtUtil.generateToken(username);
		}

		throw new RuntimeException("Invalid username or password");
	}
}

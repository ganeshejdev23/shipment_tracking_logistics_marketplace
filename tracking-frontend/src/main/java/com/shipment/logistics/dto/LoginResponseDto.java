package com.shipment.logistics.dto;

public class LoginResponseDto {

	private String username;
	private String role;
	private String token;

	// GETTERS AND SETTERS

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {

		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {

		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {

		this.token = token;
	}
}
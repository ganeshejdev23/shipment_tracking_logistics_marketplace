package com.shipment.logistics.dto;

public class LoginRequestDto {

	private String username;
	private String password;

	// GETTERS AND SETTERS

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {

		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}
}
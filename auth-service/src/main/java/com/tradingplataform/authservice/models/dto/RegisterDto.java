package com.tradingplataform.authservice.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RegisterDto {
	
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;
	
	public RegisterDto() {

	}

	public RegisterDto(String email, String password) {

		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
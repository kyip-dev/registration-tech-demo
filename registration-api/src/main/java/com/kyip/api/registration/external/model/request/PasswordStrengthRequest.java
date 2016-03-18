package com.kyip.api.registration.external.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PasswordStrengthRequest {
	@JsonProperty("password")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

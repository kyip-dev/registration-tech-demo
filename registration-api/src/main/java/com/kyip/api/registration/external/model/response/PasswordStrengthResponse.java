package com.kyip.api.registration.external.model.response;

public class PasswordStrengthResponse extends BasicResponse {

	private Integer level; // 1- weak, 2 normal, 3 strong

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}

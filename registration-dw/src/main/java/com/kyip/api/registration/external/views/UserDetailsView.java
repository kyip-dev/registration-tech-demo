package com.kyip.api.registration.external.views;

public class UserDetailsView extends BaseView {

	private String username;

	public UserDetailsView() {
		this.setSectionTemplatePath("/views/userDetails.ftl");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

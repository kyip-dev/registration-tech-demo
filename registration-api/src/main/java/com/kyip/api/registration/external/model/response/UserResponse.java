package com.kyip.api.registration.external.model.response;

import com.kyip.api.registration.external.model.User;

public class UserResponse extends BasicResponse{
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

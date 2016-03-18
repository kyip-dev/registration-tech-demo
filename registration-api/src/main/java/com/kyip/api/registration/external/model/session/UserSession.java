package com.kyip.api.registration.external.model.session;

import java.util.List;

import com.kyip.api.registration.external.model.User;

/**
 * To store the all users data
 */
public class UserSession {
	public static final String SESSION_KEY = "USER_SESSION";

	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}

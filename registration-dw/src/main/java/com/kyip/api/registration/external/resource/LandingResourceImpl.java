package com.kyip.api.registration.external.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.auth.InvalidCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.kyip.api.registration.external.model.User;
import com.kyip.api.registration.external.model.request.LoginRequest;
import com.kyip.api.registration.external.model.response.LoginResponse;
import com.kyip.api.registration.external.model.response.UserResponse;
import com.kyip.api.registration.external.model.session.UserSession;
import com.kyip.api.registration.external.service.LandingService;
import com.kyip.api.registration.external.views.LandingView;
import com.kyip.api.registration.external.views.UserDetailsView;

import io.dropwizard.views.View;

public class LandingResourceImpl implements LandingResource {

	private static final Logger logger = LoggerFactory.getLogger(LandingResourceImpl.class);

	@Context
	HttpServletRequest httpRequest;

	@Inject
	LandingService landingService;

	@Override
	public View getLandingView() throws Exception {
		LandingView view = new LandingView();
		view.setSectionTemplatePath("/views/landing.ftl");
		view.setScriptPath("/assets/js/controllers/landing_app");

		landingService.getUserEvent(httpRequest.getSession());
		return view;
	}

	@Override
	public LoginResponse login(LoginRequest request) throws Exception {
		UserSession userSession = (UserSession) httpRequest.getSession().getAttribute(UserSession.SESSION_KEY);
		LoginResponse response = new LoginResponse();
		if (CollectionUtils.isEmpty(userSession.getUsers())) {
			return response;
		}

		boolean success = false;
		for (User user: userSession.getUsers()) {
			if (user.getUsername().equals(request.getUsername()) && user.getPassword().equals(request.getPassword())) {
				success = true;
				logger.info("Login success with [USERNAME:{}]", request.getUsername());
			}
		}

		if (!success) {
			throw new InvalidCredentialsException();
		}
		response.setSuccess(true);
		response.setUsername(request.getUsername());
		return response;
	}


	@Override
	public View getUserDetailsView(String username) throws Exception {
		UserDetailsView view = new UserDetailsView();
		view.setScriptPath("/assets/js/controllers/user_details_app");
		view.setUsername(username);
		return view;
	}

	@Override
	public UserResponse getUserDetails(String username) throws Exception {
		UserSession userSession = (UserSession) httpRequest.getSession().getAttribute(UserSession.SESSION_KEY);

		UserResponse response = new UserResponse();
		for (User user: userSession.getUsers()) {
			if (user.getUsername().equals(username)) {
				response.setUser(user);
			}
		}
		return response;
	}
}

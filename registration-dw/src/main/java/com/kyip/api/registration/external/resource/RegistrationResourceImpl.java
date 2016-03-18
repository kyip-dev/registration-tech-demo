package com.kyip.api.registration.external.resource;

import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.kyip.api.registration.external.model.User;
import com.kyip.api.registration.external.model.request.PasswordStrengthRequest;
import com.kyip.api.registration.external.model.request.RegistrationRequest;
import com.kyip.api.registration.external.model.response.BasicResponse;
import com.kyip.api.registration.external.model.response.PasswordStrengthResponse;
import com.kyip.api.registration.external.model.session.UserSession;
import com.kyip.api.registration.external.service.LandingService;
import com.kyip.api.registration.external.validator.PasswordValidator;
import com.kyip.api.registration.external.views.LandingView;

import io.dropwizard.views.View;

public class RegistrationResourceImpl implements RegistrationResource {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationResourceImpl.class);

	@Context
	HttpServletRequest httpRequest;

	@Inject
	LandingService landingService;

	@Override
	public View getRegistrationView() throws Exception {
		LandingView view = new LandingView();
		view.setSectionTemplatePath("/views/registration.ftl");
		view.setScriptPath("/assets/js/controllers/registration_app");

		return view;
	}

	@Override
	public BasicResponse register(RegistrationRequest request) throws Exception {
		if (request == null) {
			throw new IllegalArgumentException();
		}
		if (StringUtils.isBlank(request.getUsername())) {
			throw new IllegalArgumentException();
		}
		if (StringUtils.isBlank(request.getPassword())) {
			throw new IllegalArgumentException();
		}
		if (StringUtils.isBlank(request.getPasswordConfirm())) {
			throw new IllegalArgumentException();
		}
		PasswordValidator pv = PasswordValidator.getInstance();
		if (!pv.validateRules(request.getPassword())) {
			throw new IllegalArgumentException("password does not meet standard");
		}
		if (!request.getPassword().equals(request.getPasswordConfirm())) {
			throw new IllegalArgumentException();
		}

		UserSession userSession = (UserSession) httpRequest.getSession().getAttribute(UserSession.SESSION_KEY);
		if (CollectionUtils.isNotEmpty(userSession.getUsers())) {
			for (User user: userSession.getUsers()) {
				if (user.getUsername().equals(request.getUsername())) {
					throw new IllegalArgumentException("Username already exist");
				}
			}
		}

		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(request.getPassword());
		user.setNickname(request.getNickname());

		// save to db
		landingService.saveUser(user, user.getUsername());

		// create response
		BasicResponse response = new BasicResponse();
		response.setSuccess(true);
		response.setLocalTime(LocalTime.now());

		return response;
	}

	@Override
	public PasswordStrengthResponse getPasswordStrength(PasswordStrengthRequest request) throws Exception {
		PasswordValidator pv = PasswordValidator.getInstance();
		Integer level = pv.checkPasswordStrength(request.getPassword());

		PasswordStrengthResponse response = new PasswordStrengthResponse();
		response.setLocalTime(LocalTime.now());
		response.setLevel(level);
		return response;
	}
}

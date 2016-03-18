package com.kyip.api.registration.external.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kyip.api.registration.external.model.request.PasswordStrengthRequest;
import com.kyip.api.registration.external.model.request.RegistrationRequest;
import com.kyip.api.registration.external.model.response.BasicResponse;
import com.kyip.api.registration.external.model.response.PasswordStrengthResponse;

import io.dropwizard.views.View;

@Path("/kyip/v1/registration")
public interface RegistrationResource {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public View getRegistrationView() throws Exception;


	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BasicResponse register(RegistrationRequest request) throws Exception;

	@POST
	@Path("/password/strength")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PasswordStrengthResponse getPasswordStrength(PasswordStrengthRequest request) throws Exception;

}

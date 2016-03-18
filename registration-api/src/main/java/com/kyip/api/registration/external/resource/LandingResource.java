package com.kyip.api.registration.external.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kyip.api.registration.external.model.request.LoginRequest;
import com.kyip.api.registration.external.model.response.LoginResponse;
import com.kyip.api.registration.external.model.response.UserResponse;

import io.dropwizard.views.View;

@Path("/kyip/v1/landing")
public interface LandingResource {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public View getLandingView() throws Exception;


	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LoginResponse login(LoginRequest request) throws Exception;

	@GET
	@Path("/details/{username}")
	@Produces(MediaType.TEXT_HTML)
	public View getUserDetailsView(@PathParam("username") String username) throws Exception;

	@GET
	@Path("/details/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserResponse getUserDetails(@PathParam("username") String username) throws Exception;

}

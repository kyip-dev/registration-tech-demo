package com.kyip.api.registration.external.provider;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<WebApplicationException> {

	private static Logger logger = LoggerFactory.getLogger(ServiceExceptionMapper.class);

	@Override
	public Response toResponse(final WebApplicationException exception) {
		Throwable actualCause = exception.getCause();
		if (actualCause == null) {
			return exception.getResponse();
		}
		logger.error("handling exception [TYPE:{}][MSG:{}] ", actualCause.getClass().getName(), actualCause.getMessage(), actualCause);

		Exception restException = new Exception(actualCause.getMessage(), actualCause);

		// just demo, should have an Enum to do mapping for errors and status
		ResponseBuilder builder = Response.status(500).entity(restException).type(MediaType.APPLICATION_JSON);
		return builder.build();
	}

}
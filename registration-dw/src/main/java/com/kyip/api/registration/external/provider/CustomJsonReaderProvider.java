package com.kyip.api.registration.external.provider;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyip.api.registration.external.model.response.BasicResponse;


@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class CustomJsonReaderProvider implements MessageBodyReader<Object> {

	private static final Logger logger = LoggerFactory.getLogger(CustomJsonReaderProvider.class);

	private static final ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setSerializationInclusion(Include.NON_NULL);
	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		try {
			return mapper.readValue(entityStream, type);
		} catch (IOException e) { // This block will also catch JsonParseException, JsonMappingException
			logger.error("failed to read json", e);

			final BasicResponse response = new BasicResponse();
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(response).build());
		}
	}

}

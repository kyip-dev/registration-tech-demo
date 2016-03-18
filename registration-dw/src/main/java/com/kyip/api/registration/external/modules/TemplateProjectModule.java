package com.kyip.api.registration.external.modules;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.kyip.api.registration.external.MainConfiguration;

public class TemplateProjectModule extends AbstractModule {
	private final MainConfiguration configuration;

	public TemplateProjectModule(MainConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	protected void configure() {
	}

	@Provides
	@Singleton
	public MainConfiguration getConfiguration() {
		return configuration;
	}

	@Provides
	@Singleton
	public ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper;
	}

}

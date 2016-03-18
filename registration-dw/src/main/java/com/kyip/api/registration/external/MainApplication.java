package com.kyip.api.registration.external;

import org.eclipse.jetty.server.session.SessionHandler;
import org.glassfish.jersey.filter.LoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kyip.api.registration.external.health.Ping;
import com.kyip.api.registration.external.modules.TemplateProjectModule;
import com.kyip.api.registration.external.provider.CustomJsonReaderProvider;
import com.kyip.api.registration.external.provider.CustomJsonWriterProvider;
import com.kyip.api.registration.external.provider.ServiceExceptionMapper;
import com.kyip.api.registration.external.resource.LandingResourceImpl;
import com.kyip.api.registration.external.resource.RegistrationResourceImpl;
import com.kyip.api.registration.external.websocket.MessageSender;

import be.tomcools.dropwizard.websocket.WebsocketBundle;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class MainApplication extends Application<MainConfiguration> {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

	private final WebsocketBundle websocket = new WebsocketBundle();

	@Override
	public String getName() {
		return "template-project";
	}

	public static void main(String[] args) throws Exception {
		new MainApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<MainConfiguration> bootstrap) {
		bootstrap.addBundle(new ViewBundle<MainConfiguration>() {
			@Override
			public ImmutableMap<String, ImmutableMap<String, String>> getViewConfiguration(MainConfiguration configuration) {
				return configuration.getViewRendererConfiguration();
			}
		});
		bootstrap.addBundle(new AssetsBundle("/assets"));
		bootstrap.addBundle(websocket);

		LOGGER.info("initialized");
	}

	@Override
	public void run(MainConfiguration configuration, Environment environment) throws Exception {
		// register health checks
		environment.healthChecks().register("ping", new Ping(configuration));

		// register resource binds
		Injector injector = Guice.createInjector(new TemplateProjectModule(configuration));

		environment.jersey().register(injector.getInstance(LandingResourceImpl.class));
		environment.jersey().register(injector.getInstance(RegistrationResourceImpl.class));

		// register providers
		environment.jersey().register(injector.getInstance(CustomJsonReaderProvider.class));
		environment.jersey().register(injector.getInstance(CustomJsonWriterProvider.class));
		environment.jersey().register(ServiceExceptionMapper.class);

		environment.jersey().register(new LoggingFilter(java.util.logging.Logger.getLogger(MainApplication.class.getName()), true));

		environment.servlets().setSessionHandler(new SessionHandler());
		websocket.addEndpoint(MessageSender.class);

		LOGGER.info("run completed");
	}

}

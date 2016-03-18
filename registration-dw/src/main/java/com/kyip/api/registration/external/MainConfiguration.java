package com.kyip.api.registration.external;

import io.dropwizard.Configuration;

import com.google.common.collect.ImmutableMap;

public class MainConfiguration extends Configuration {
	private ImmutableMap<String, ImmutableMap<String, String>> viewRendererConfiguration = ImmutableMap.of();

	/**
	 * @return the viewRendererConfiguration
	 */
	public ImmutableMap<String, ImmutableMap<String, String>> getViewRendererConfiguration() {
		return viewRendererConfiguration;
	}

	/**
	 * @param viewRendererConfiguration
	 *            the viewRendererConfiguration to set
	 */
	public void setViewRendererConfiguration(ImmutableMap<String, ImmutableMap<String, String>> viewRendererConfiguration) {
		this.viewRendererConfiguration = viewRendererConfiguration;
	}

}

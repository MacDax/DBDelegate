package com.spring.boot.framework.envconfig;

import java.util.Optional;

import javax.inject.Inject;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

public class EnvConfigUtil {

	//@Inject
	private static Config config = ConfigProvider.getConfig();
	
	public static String getAsString(String propertyName, String defaultValue) {
		config = ConfigProvider.getConfig();
		return (String)config.getOptionalValue(propertyName, String.class).orElse(defaultValue);
	}
	
	/*public static String getAsString(String propName, String defaultValue) {
		config = ConfigProvider.getConfig();
		return config.getValue(propName, String.class);
	}*/
}

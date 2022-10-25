package com.mercedesbenz.shortening.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:errorcode.properties")
@PropertySource("classpath:errormessage.properties")
public class GlobalConfiguration {

	@Autowired
	private Environment enviroment;

	public String getValueFromString(String key) {
		return enviroment.getProperty(key);
	}

}

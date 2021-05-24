package com.robee.shorturl.config;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class UrlDictionaryConfig extends WebMvcConfigurerAdapter {

	private static final String[] schemes = {"http","https"};
	
	@Bean
	public UrlValidator urlValidator() {
		return new UrlValidator(schemes);
	}
	
}

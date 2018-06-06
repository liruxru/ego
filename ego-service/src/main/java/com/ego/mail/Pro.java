package com.ego.mail;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class Pro extends PropertyPlaceholderConfigurer {

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if ("password".equals(propertyName)) {
			return "zxc12345";
		}
		return propertyValue;

	}

}

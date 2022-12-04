package com.empleos.empleosWeb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/logos/**").addResourceLocations("src//main//resorces//static/images/");
		registry.addResourceHandler("/logos/**").addResourceLocations("//src//main//resources//static/images");
	}
}

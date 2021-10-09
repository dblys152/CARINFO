package com.ys.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages ={ "com.ys" })
public class ConfigWeb implements WebMvcConfigurer {

	@Value("${file.updload.path}")
	private	String	uploadPath;

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
	    registry.jsp("/WEB-INF/views/", ".jsp");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		File f = new File(uploadPath);
		registry.addResourceHandler("/resources/**")
		.addResourceLocations("/resources/", f.toURI().toString());
	}

}

package iss.ad.project.spotify.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import iss.ad.project.spotify.interceptor.AuthenticationInterceptor;

@Component
public class WebAppConfig implements WebMvcConfigurer{

	@Autowired
	AuthenticationInterceptor authenticationInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/home");
	}
}

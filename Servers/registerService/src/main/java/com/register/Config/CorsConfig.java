package com.register.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * configurations for the CORS issues
 * Has WebMvcConfigurere and within that is addCorsMapping
 * @author Unknown
 *
 */
@Configuration
public class CorsConfig {
	
	@Value("${frontend.uri}")
	String frontEndUri;

	/**
	 * WebMvcConfigurer takes no parameters which overrides aaCorsMapping to take in
	 * a CorsRegistry as a parameter. The registry then added GET POST PUT and DELETE methods
	 * Allows all headers and allow origins from localhost:3000
	 * @author Unknown
	 *
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {

				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEADER").allowedHeaders("*")
						.allowedOrigins("http://ec2-3-229-134-85.compute-1.amazonaws.com:9015");
			}
		};
	}
}

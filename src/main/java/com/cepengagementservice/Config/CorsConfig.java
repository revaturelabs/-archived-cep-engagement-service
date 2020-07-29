package com.cepengagementservice.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfigurer takes no parameters which overrides aaCorsMapping to take in
 * a CorsRegistry as a parameter. The registry then added GET POST PUT and DELETE methods
 * Allows all headers and allow origins from localhost:3000
 * @author Unknown
 *
 */
@Configuration
public class CorsConfig {

	/**
	 * 
	 * @return
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {

				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*")
						.allowedOrigins("http://localhost:3000");
			}
		};
	}
}

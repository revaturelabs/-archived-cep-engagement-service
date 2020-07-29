package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.filter.PostFilter;
import com.example.filter.PreFilter;


@Configuration
public class MyConfig {

	@Bean
	public PreFilter FilterForPre() {
		return new PreFilter();
	}
	
	@Bean
	public PostFilter FilterForPost() {
		return new PostFilter();
	}
}

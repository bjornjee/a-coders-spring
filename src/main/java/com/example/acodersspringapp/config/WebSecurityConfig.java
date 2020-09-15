package com.example.acodersspringapp.config;

import java.util.Base64;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSecurityConfig {

	@Bean
	public Base64.Encoder passwordEncoder() {
		return Base64.getEncoder();
	}
}
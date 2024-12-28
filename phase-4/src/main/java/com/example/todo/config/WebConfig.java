package com.example.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
public class WebConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/api/**").permitAll() // Allow access to all API endpoints
						.anyRequest().authenticated() // Require authentication for other requests
				);
		return http.build();
	}
}
/*package com.example.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {

	// CORS Configuration
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("http://localhost:63342"); // Allow requests from this origin
		configuration.addAllowedMethod("*"); // Allow all HTTP methods
		configuration.addAllowedHeader("*"); // Allow all headers
		configuration.setAllowCredentials(true); // Allow credentials (cookies, authorization headers, etc.)

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/api/**", configuration); // Apply CORS settings to /api/**

		return source;
	}

	// Security Configuration
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable() // Disable CSRF protection for testing purposes
				.cors() // Enable CORS support using the defined CorsConfigurationSource
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/api/**").permitAll() // Allow access to all API endpoints without authentication
						.anyRequest().authenticated() // Require authentication for other requests
				);
		return http.build();
	}
}*/

package com.desertlamp.eureka.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class EurekaServerSecurityConfig {

	private static final String EUREKA_URLS = "/eureka/**";
	
	@Value("#{'${eureka.server.users}'.split(',')}") 
	private List<String> users;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(
						authorize -> authorize.requestMatchers(EUREKA_URLS).authenticated().anyRequest().permitAll())
				.httpBasic(Customizer.withDefaults())
				.build();
	}
	
	@Bean
 	public UserDetailsService userDetailsService() {
		
		List<UserDetails> userDetails = new ArrayList<>();
		
		for (String user : users) {
			List<String> userCreds = Arrays.asList(user.split("-"));
			UserDetails userDetail = User.builder()
		 			.username(userCreds.get(0))
		 			.password(passwordEncoder().encode(userCreds.get(1)))
		 			.build();
			
			userDetails.add(userDetail);
		}
		return new InMemoryUserDetailsManager(userDetails);
 	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package com.example.summarization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.warrenstrange.googleauth.GoogleAuthenticator;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

		@Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

		@Bean
    public GoogleAuthenticator googleAuthenticator() {
        return new GoogleAuthenticator();
    }

		@Bean
		SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http
				.formLogin(login -> login
						.loginPage("/login")
						.successHandler(customAuthenticationSuccessHandler())
						.permitAll())
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("/register", "/2fa/**").permitAll()
						.anyRequest().authenticated())
				.logout(logout -> logout
						.permitAll())
				.sessionManagement(session -> session
						.sessionFixation(sessionFixation -> sessionFixation
								.migrateSession())
						.maximumSessions(1)
						.maxSessionsPreventsLogin(true));
			return http.build();
		}

}

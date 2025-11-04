package com.securevault.security;  // Your package name

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;  // Still use this!
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // Tells Spring: "This is a config class"
@EnableWebSecurity  // Turns on web security (still needed!)
public class SecurityConfig {

    @Bean  // Like saying: "Spring, make this recipe for me"
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Build your security rules here (like a fence around your house)
        http
                .authorizeHttpRequests((authz) -> authz  // Who can access what?
                        .requestMatchers("/").permitAll()  // Anyone can see home page
                        .anyRequest().authenticated()  // Everything else needs login
                )
                .formLogin((form) -> form  // Use a login form (username/password)
                        .loginPage("/login")  // Your login page (make one later)
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());  // Allow logout

        return http.build();  // Finish building the fence!
    }
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}
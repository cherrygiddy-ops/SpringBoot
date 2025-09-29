package com.morrisco.net.store.onlineStoreSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //security Filter chain defines how HTTP Request are Secured
    @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /**
         * Three Steps To Follow
         * 1.Stateless Session -> Token Based Auth
         * 2.Disable CSRF (Cross Site Request Forgery) attack by tricking browser to send the request without client knowing it
         * 3.Authorize Http Request
         */
        http.sessionManagement(c->
                c.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(c->c
                .requestMatchers("/carts/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/users").permitAll()
                .anyRequest().authenticated());

        return http.build();
  }
}

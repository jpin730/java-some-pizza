package com.example.somepizza.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        authorize -> authorize.
                                requestMatchers(HttpMethod.GET, "/reset").permitAll().
                                requestMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMIN", "CUSTOMER").
                                requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN").
                                requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN").
                                requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")).
                csrf(
                        AbstractHttpConfigurer::disable
                ).cors(Customizer.withDefaults()).
                httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

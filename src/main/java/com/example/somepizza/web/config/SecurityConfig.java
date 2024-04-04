package com.example.somepizza.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        authorize -> authorize.
                                requestMatchers(HttpMethod.GET, "/reset").hasRole("ADMIN").
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
    public UserDetailsService memoryUsers() {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();
        UserDetails customer = User
                .withUsername("customer")
                .password(passwordEncoder().encode("password"))
                .roles("CUSTOMER")
                .build();
        return new InMemoryUserDetailsManager(admin, customer);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

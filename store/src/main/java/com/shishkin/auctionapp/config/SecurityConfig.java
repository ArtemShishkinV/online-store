package com.shishkin.auctionapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String API_PATH = "/api/store/**";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http = http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.DELETE, API_PATH).hasAuthority("SCOPE_delete")
                .antMatchers(HttpMethod.PATCH, API_PATH).hasAuthority("SCOPE_change")
                .antMatchers(HttpMethod.POST, API_PATH).hasAuthority("SCOPE_change")
                .antMatchers("/data-api/**").hasAuthority("SCOPE_data-api")
                .and().oauth2ResourceServer().jwt().and().and();
        return http.authorizeRequests()
                .antMatchers("/**/create").hasRole("ADMIN")
                .antMatchers( "/categories", "/products").hasAnyRole("ADMIN", "USER")
                .antMatchers("/", "/**").permitAll()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/", true)
                .and().logout().logoutSuccessUrl("/login").and().build();
    }

}

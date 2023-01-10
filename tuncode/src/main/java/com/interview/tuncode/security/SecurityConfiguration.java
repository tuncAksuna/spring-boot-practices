package com.interview.tuncode.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfiguration {

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public SecurityConfiguration(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, UserDetailService userDetailService) throws Exception {

        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(encoder)
                .and()
                .build();
    }


    @Bean
    public SecurityFilterChain localSecurity(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeRequests()
                .antMatchers("/h2-console/**")
                .anonymous()
                .antMatchers("/api/**")
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable()
                .cors()
                .disable()
                .build();
    }
}

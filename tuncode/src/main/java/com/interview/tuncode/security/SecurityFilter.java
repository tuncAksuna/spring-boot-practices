//package com.interview.tuncode.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityFilter {
//
//
//    /*
//    private final JwtAuthConverter jwtAuthConverter;
//
//    public SecurityFilter(JwtAuthConverter jwtAuthConverter) {
//        this.jwtAuthConverter = jwtAuthConverter;
//    }
//
//    @Bean
//    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((authorizeRequests) ->
//                authorizeRequests.requestMatchers(HttpMethod.GET).permitAll()
//                        .requestMatchers("/api/v1/student/**").authenticated()
//                        .requestMatchers("/api/v1/address/**").permitAll()
//                        .requestMatchers("/api/v1/student/comment/**").authenticated()
//                        .anyRequest().authenticated());
//
//        /*http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth ->
//                        auth.anyRequest().authenticated());*/
//
//       /* http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        http.oauth2ResourceServer(oAuth2 -> oAuth2
//                .jwt(jwtConfigurer -> jwtConfigurer
//                        .jwtAuthenticationConverter(jwtAuthConverter)));
//
//
//        return http.build();
//    }
//*/
//}

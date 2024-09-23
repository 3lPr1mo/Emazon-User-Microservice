package com.bootcamp.pragma.usermicroservice.configuration.security;

import com.bootcamp.pragma.usermicroservice.domain.util.RoleContants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigFilter {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public RequestMatcher whitelistRequestMatcher() {
        List<String> whitelist = List.of(
                "/api-doc/**",
                "/api-doc/swagger-config",
                "/swagger-ui/**",
                "/auth/register/admin",
                "/auth/register/client",
                "/auth/login"
        );

        return request -> whitelist.stream().anyMatch(request.getServletPath()::equals);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(whitelistRequestMatcher()).permitAll()
                        .requestMatchers("/api-doc/**", "/api-doc/swagger-config", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register/aux").hasAnyAuthority(RoleContants.ROLE_ADMIN)
                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}

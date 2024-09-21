package com.bootcamp.pragma.usermicroservice.configuration.security;

import com.bootcamp.pragma.usermicroservice.domain.model.Role;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.adapter.JwtAdapter;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.entity.RoleEntity;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.entity.UserEntity;
import com.bootcamp.pragma.usermicroservice.infrastructure.driven.jpa.mysql.mapper.IRoleEntityMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAdapter jwtService;
    private final IRoleEntityMapper roleEntityMapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authorizationHeader.substring(7);
        final String email = jwtService.getEmailFromToken(token);
        final Role role = jwtService.getRoleFromToken(token);

        if (email == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }
        final RoleEntity roleEntity = roleEntityMapper.toEntityFromModel(role);
        UserDetails userDetails = UserEntity.builder().email(email).role(roleEntity).build();

        if (!jwtService.validateToken(token, userDetails)) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }
}

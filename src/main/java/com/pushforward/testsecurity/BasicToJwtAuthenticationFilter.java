package com.pushforward.testsecurity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public class BasicToJwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy()
                .getContext()
                .getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String jwtValueHeader = request.getHeader("X-TOKEN");

        Jwt jwt = Jwt.withTokenValue(jwtValueHeader)
                .header("typ", "JWT")
                .claims(c -> c.put("scope", authorities.stream().map(GrantedAuthority::getAuthority).toList()))
                .subject(authentication.getName())
                .build();

        JwtAuthenticationToken token = new JwtAuthenticationToken(jwt, authorities);

        SecurityContext newContext = SecurityContextHolder.createEmptyContext();
        newContext.setAuthentication(token);
        SecurityContextHolder.setContext(newContext);

        filterChain.doFilter(request, response);
    }
}

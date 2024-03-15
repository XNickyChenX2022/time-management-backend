package com.example.timemanagementapp.security;

import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.timemanagementapp.dto.transfer.UserDetailsTransferDTO;
import com.example.timemanagementapp.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserService UserService;
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    public AuthTokenFilter(JwtUtil jwtUtil, com.example.timemanagementapp.services.UserService userService) {
        this.jwtUtil = jwtUtil;
        UserService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String tokenHeader = parseToken(request);
        if (tokenHeader != null && jwtUtil.validateJwtToken(tokenHeader)) {
            String username = jwtUtil.extractUsername(tokenHeader);
            UserDetails userDetails = UserService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    username,
                    null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authenticationToken);

            SecurityContextHolder.setContext(context);
        }
        filterChain.doFilter(request, response);
    }

    private String parseToken(HttpServletRequest request) {
        String tokenHeader = request.getHeader("Authorization");

        if (StringUtils.hasText(tokenHeader) && tokenHeader.startsWith("Bearer ")) {
            return tokenHeader.substring(7);
        }

        return null;
    }
}

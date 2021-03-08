package com.example.processworkflowpostgres.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ContextSetterFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceConfiguration userDetailsServiceConfiguration;

    @Autowired
    SecurityUtils securityUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (request.getServletPath().contains("/user") && request.getMethod().contains("POST")) {
            chain.doFilter(request, response);
        } else {
            securityUtils.logInAs(request.getParameter("username"));
            UserDetails userDetails = userDetailsServiceConfiguration.loadUserByUsername(request.getParameter("username"));
            request.setAttribute("userDetails", userDetails);
            chain.doFilter(request, response);
        }
    }
}

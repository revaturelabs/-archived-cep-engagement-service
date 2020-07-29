package com.cepengagementservice.security;

//This file is used to make sure the JWT token is only authorized for one user. @author Nicholas Larkin
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtTokenAuthorizationOncePerRequestFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JwtInMemoryUserDetailsService jwtInMemoryUserDetailsService;  //changing first parameter from UserDetailsService
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Value("${jwt.http.request.header}") //pulling header information from app.properties (Authorization: uses Bearer schema)
    private String tokenHeader;
    

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        logger.debug("Authentication Request For '{}'", request.getRequestURL());
        
        final String requestTokenHeader = request.getHeader(this.tokenHeader); // If request header doesn't match this.tokenHeader it returns null

        String email = null; //changed from username
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) { // checks to make sure header is Authorization header that look like: Bearer <token>
            jwtToken = requestTokenHeader.substring(7); //7 to pass bearer and space, to start reading Token when it starts. Sets token.
            try {
                email = jwtTokenUtil.getEmailFromToken(jwtToken); //changed username to email, sets email from received token.
            } catch (IllegalArgumentException e) {
                logger.error("JWT_TOKEN_UNABLE_TO_GET_EMAIL", e);
            } catch (ExpiredJwtException e) {
                logger.warn("JWT_TOKEN_EXPIRED", e);
            }
        } else {
            logger.warn("JWT_TOKEN_DOES_NOT_START_WITH_BEARER_STRING");
        }

        logger.debug("JWT_TOKEN_EMAIL_VALUE '{}'", email);
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) { //makes sure the authentication token and email are both non-null

            JwtUserDetails jwtUserDetails = this.jwtInMemoryUserDetailsService.loadUserByEmail(email); //loads in user as a JwtUserDetails object

            if (jwtTokenUtil.validateToken(jwtToken, jwtUserDetails)) {
                UsernamePasswordAuthenticationToken emailPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(jwtUserDetails, null, jwtUserDetails.getAuthorities());
                emailPasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(emailPasswordAuthenticationToken);
            }
        }

        chain.doFilter(request, response);
    }
}
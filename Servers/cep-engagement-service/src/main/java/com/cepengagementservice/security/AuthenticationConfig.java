package com.cepengagementservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class will setup the configuration for authenticating a user
 * @author Unknown
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //allows for Spring Security prePostAnnotations
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService; //UserDetailsService

    @Autowired
    private JwtTokenAuthorizationOncePerRequestFilter jwtAuthenticationTokenFilter;
    
    @Value("${jwt.get.token.uri}") //gets authentication path from app.properties
    private String authenticationPath;

    @Value("${jwt.post.user.uri}") //Allow user creation for BCrypt.
    private String createUserPath;

    /**
     * This will configure an Authentication Manager based on the a password encoder
     * and jwtInMemoryUserDetailService
     * @param auth the AuthenticationManagerBuilder object
     * @throws Exception if creating an Authentication Manager fails
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(jwtInMemoryUserDetailsService)
            .passwordEncoder(passwordEncoderBean());
    }

    /**
     * 
     * @return a new BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    /**
     * returns the parent classes Authentication Manager
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * This will configure the the level of access a user will have. 
     * Every user has access to web based utilities such as swagger, 
     * 		h2 console, webjars, configuration, and api-docs.
     * If the user is of type ADMIN they will be able to use the 
     * 		get /interventions route and the /users/all route.
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(jwtUnAuthorizedResponseAuthenticationEntryPoint).and() //handles any exceptions with custom handler
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() //ensures that expired sessions are cleaned up
            .authorizeRequests()//Allows restricting access based upon the HttpServletRequest using RequestMatcher implementations
            .antMatchers("/v3/api-docs/**", "/configuration/**", "/swagger*/**", "/webjars/**", "/", "/console/**").permitAll() // permits all users to access Swagger V3 URLS
            .antMatchers("/users/all", "/interventions").hasAnyRole("ADMIN")  // Restricts getting all users and intervention requests to ADMIN users.
            .anyRequest().authenticated();// If you are authorized you will be able to access any routes that aren't specified to the ADMIN role.
        
       httpSecurity
            .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        
        httpSecurity
            .headers() //adds security headers to response
            .frameOptions().sameOrigin()  //H2 Console Needs this setting
            .cacheControl(); //disable caching
    }

    /**
     * This will allow the people to do a post request for createUserPath and
     * 		authenticationPath without needing a token.
     * It also allows request for the OPTIONS Http Method and any type of 
     * 		Http request for V3 and Swagger
     */
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
             .ignoring()
            .antMatchers(
                HttpMethod.POST,
                authenticationPath //allows Post Request to authentication path through security without Token.
            )
            .antMatchers(
                HttpMethod.POST, //allows Post Request to Add User Endpoint without Token
                createUserPath
            )
            .antMatchers(HttpMethod.OPTIONS, "/**") // Permits Options requests to any URL to see your options
            .antMatchers("/v3/**"); // Permits any type of http request to the URL starting with V3 for Swagger.
        
    }
}
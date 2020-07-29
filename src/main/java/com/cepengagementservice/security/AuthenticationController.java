
package com.cepengagementservice.security;

//Exposes all the URLs related to JWT Authentication. @author Nicholas Larkin

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Unknown
 * Controller that handles the end-point that deal with JWT Authentication
 */
@RestController
@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200", "http://localhost:8081", "http://ec2-18-232-171-89.compute-1.amazonaws.com:8081"}) //CORS will be changed to EC2 servers
public class AuthenticationController {

  @Value("${jwt.http.request.header}") //grabs header from src/main/resources/app.properties 
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager; //for UsernamePasswordAuthenticationToken

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private JwtInMemoryUserDetailsService userServices;
  
  /**
   * This method will create a JWT Token
   * @param authenticationRequest is the JWTTokenRequest object that holds the users email and password
   * @return A JWT Token
   * @see JwtTokenResponse
   * @throws AuthenticationException if credentials are invalid or a token exception
   * @see AuthenticationException
   */
  @RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)  //Gets Login URI from app.properties and Generates Token if valid
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
      throws AuthenticationException { // in case the request is not valid

    authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword()); //authenticates user based on requestbody

    
    final JwtUserDetails jwtUserDetails =new JwtUserDetails( userServices.getUserByEmail(authenticationRequest.getEmail())); //loads user object

    final String token = jwtTokenUtil.generateToken(jwtUserDetails); //generates token including user object 

    return ResponseEntity.ok(new JwtTokenResponse(token));
  }
  
  /**
   * This method will refresh a token when it expires (needs to be fixed)
   * @param request holds the jwt token that needs to get refreshed
   * @return either a refreshed jwt token or an error.
   */
  @RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)  //Gets refresh URI from app.properties and refreshes token if it is expired, needs fix.
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
   // String email = jwtTokenUtil.getEmailFromToken(token); //only need token to check if it is refreshed.
   // JwtUserDetails user = (JwtUserDetails) userServices.getUserByEmail(email); //user is not used for current functionality.

    if (jwtTokenUtil.canTokenBeRefreshed(token)) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

  /**
   * This method will be called whenever an exception in this class is thrown
   * @param e is an AuthenticationException
   * @see AuthenticationException
   * @return An Unauthorized exception
   */
  @ExceptionHandler({ AuthenticationException.class })
  public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  /**
   * This method will try and validate a user's login information
   * @param email is the user's email
   * @param password is the user's password
   * @throws AuthenticationException throws an AuthenticationException if a user is 
   * disabled or invalid credentials
   */
  private void authenticate(String email, String password) {
    Objects.requireNonNull(email);
    Objects.requireNonNull(password);

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    } catch (DisabledException e) {
      throw new AuthenticationException("USER_DISABLED", e); //will return error and a message correlated to why.
    } catch (BadCredentialsException e) {
      throw new AuthenticationException("INVALID_CREDENTIALS", e);
    }
  }
}



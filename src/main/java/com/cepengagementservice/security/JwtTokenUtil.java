package com.cepengagementservice.security;

//Provides JWT Functionality for the encryption and decryption of JWT tokens. @author Nicholas Larkin

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;

@Component
public class JwtTokenUtil implements Serializable {

  static final String CLAIM_KEY_EMAIL = "sub"; //subject claim
  static final String CLAIM_KEY_CREATED = "iat"; //native iat property holds time Token was created at.
  private static final long serialVersionUID = 56197841984L;
  private Clock clock = DefaultClock.INSTANCE;

  @Value("${jwt.signing.key.secret}")  //grabs secret key from app.properties
  private String secret;

  @Value("${jwt.token.expiration.in.seconds}") //grabs # of seconds to expire from app.properties
  private Long expiration;

  public String getEmailFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  public Date getIssuedAtDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getIssuedAt);
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) { //<T> for type agnostic, and gets all claims to then give only the one specified (information about user)
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) { //gets all claims (information about user) from JWT token.
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) { //checks to see if token is expired.
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(clock.now());
  }

//  private Boolean ignoreTokenExpiration(String token) {
//    // here you specify tokens that should not expire, which do not exist in this application yet.
//    return false;
//  }

  public String generateToken(JwtUserDetails jwtUserDetails) { //generates token
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, jwtUserDetails.getEmail());
  }

  private String doGenerateToken(Map<String, Object> claims, String subject) {
    final Date createdDate = clock.now();
    final Date expirationDate = calculateExpirationDate(createdDate);

    return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate) //Builds the JWT
        .setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
  }

  public Boolean canTokenBeRefreshed(String token) {
    return (!isTokenExpired(token));// || ignoreTokenExpiration(token)); (if you want to implement non expiring users.)
  }

  public String refreshToken(String token) {
    final Date createdDate = clock.now();
    final Date expirationDate = calculateExpirationDate(createdDate);

    final Claims claims = getAllClaimsFromToken(token);
    claims.setIssuedAt(createdDate);
    claims.setExpiration(expirationDate);

    return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
  }

  public Boolean validateToken(String token, JwtUserDetails jwtUserDetails) {
    JwtUserDetails user = (JwtUserDetails) jwtUserDetails; //configures user
    final String email = getEmailFromToken(token);
    return (email.equals(user.getEmail()) && !isTokenExpired(token)); //returns if it is the right user and their token is not expired.
  }

  private Date calculateExpirationDate(Date createdDate) { //calculates when the Token will expire
    return new Date(createdDate.getTime() + expiration * 1000);
  }
}

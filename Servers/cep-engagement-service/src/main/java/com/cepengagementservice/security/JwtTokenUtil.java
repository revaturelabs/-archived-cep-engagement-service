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

/**
 * This class is responsible for holding the JWT functionalities such as:
 * Generating, Validating, Refreshing, and Getting the information from the
 * token.
 * 
 * @author kaleb
 *
 */
@Component
public class JwtTokenUtil implements Serializable {
	
	// The subject attribute of the the Token
	static final String CLAIM_KEY_EMAIL = "sub";
	
	// The iat property that holds the time the Token was created
	static final String CLAIM_KEY_CREATED = "iat";
	
	private Clock clock = DefaultClock.INSTANCE;
	
	private static final long serialVersionUID = 56197841984L;
	
	// Grabs secret key from app.properties
	@Value("${jwt.signing.key.secret}") 
	private String secret;

	// Grabs # of seconds to expire from app.properties
	@Value("${jwt.token.expiration.in.seconds}") 
	private Long expiration;

	// Gets email from the token
	public String getEmailFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// Gets the issued date from the token
	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	// Gets the expiration date from the token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	// <T> for type agnostic, and gets all claims to then give only the one specified (information about user)
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) { 
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// Gets all the claims from the token
	private Claims getAllClaimsFromToken(String token) { // gets all claims (information about user) from JWT token.
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	// Checks if the token is expired
	private Boolean isTokenExpired(String token) { // checks to see if token is expired.
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(clock.now());
	}

//  private Boolean ignoreTokenExpiration(String token) {
//    // here you specify tokens that should not expire, which do not exist in this application yet.
//    return false;
//  }

	// Sets up the claims for generating the token
	public String generateToken(JwtUserDetails jwtUserDetails) { // generates token
		Map<String, Object> claims = new HashMap<>();
		claims.put("role yo", jwtUserDetails.getRole());
		return doGenerateToken(claims, jwtUserDetails.getEmail());
	}

	// Actually generates the token
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		final Date createdDate = clock.now();
		final Date expirationDate = calculateExpirationDate(createdDate);

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate) // Builds the JWT
				.setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// Says the token can be refreshed if it isn't expired
	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token));// || ignoreTokenExpiration(token)); (if you want to implement non expiring
										// users.)
	}

	// not finished
	public String refreshToken(String token) {
		final Date createdDate = clock.now();
		final Date expirationDate = calculateExpirationDate(createdDate);

		final Claims claims = getAllClaimsFromToken(token);
		claims.setIssuedAt(createdDate);
		claims.setExpiration(expirationDate);

		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// Checks if the email in the token matches the user email about the current user
	public Boolean validateToken(String token, JwtUserDetails jwtUserDetails) {
		// configures user
		JwtUserDetails user = (JwtUserDetails) jwtUserDetails; 
		final String email = getEmailFromToken(token);
		
		// returns if it is the right user and their token is not expired.
		return (email.equals(user.getEmail()) && !isTokenExpired(token)); 
	}

	// calculates the token's expiration date
	private Date calculateExpirationDate(Date createdDate) { // calculates when the Token will expire
		return new Date(createdDate.getTime() + expiration * 1000);
	}
}

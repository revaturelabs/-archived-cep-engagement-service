package com.cepengagementservice.security;

//Custom Implementation of UserDetails interface providing user details of logged in client @author Nicholas Larkin

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class JwtUserDetails implements UserDetails {

  private static final long serialVersionUID = 6519609781961L; //initialized to random long value

  private final Long userId;
  private final String email;
  private final String password;
  private final String company;
  private final String role;
  private final String phone;
  private final Collection<? extends GrantedAuthority> authorities; // collection of authorities granted to the user native to UserDetails interface

  public JwtUserDetails(Long userId, String email, String password, String company, String role, String phone) { //how to make company an Enum.
    this.userId = userId;
    this.email = email;
    this.password = password;
    this.company = company;
    this.role = role;
    this.phone = phone;

    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(role));

    this.authorities = authorities;
  }

//Start of unique methods to JwtUserDetails
  @JsonIgnore
  public Long getId() {
    return userId;
  }
  
  public String getEmail() { // Made outside Interface to get Email used to authenticate the user.
    return email;
  }
  
  public String getCompany() { 
	    return company;
	  }
  
  public String getRole() { 
	    return role;
	  }
  
  public String getPhone() { 
	    return phone;
	  }
  
 // Back to UserDetails Interface methods.

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() { //Indicates whether the user's account has expired. An expired account cannot be authenticated.
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() { //Indicates whether the user is locked or unlocked. A locked user cannot be authenticated.
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {  //Indicates whether the user's credentials (password) has expired. Expired credentials prevent authentication.
    return true;
  }

  @JsonIgnore
  @Override
  public String getPassword() {  //Returns the password used to authenticate the user.
    return password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() { //Returns the authorities granted to the user. Cannot return null.
    return authorities;
  }

  @Override
  public boolean isEnabled() { //Indicates whether the user is enabled or disabled. A disabled user cannot be authenticated.
    return true;
  }

@Override
public String getUsername() { //just to finish the implementation this is the function to grab the username used to authenticate, but shouldn't need it
	return email;  
}

}

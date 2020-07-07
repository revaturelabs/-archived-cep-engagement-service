package com.cepengagementservice.security;

//Custom Implementation of UserDetails interface providing user details of logged in client @author Nicholas Larkin

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cepengagementservice.Models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class JwtUserDetails extends User implements UserDetails{
	
/**
	 * 
	 */
	private static final long serialVersionUID = -1816829060449575418L;
	
private final Collection<? extends GrantedAuthority> authorities; // collection of authorities granted to the user native to UserDetails interface


 public JwtUserDetails(User user) {
	 this.setUserId(user.getUserId());
	 this.setCompany(user.getCompany());
	 this.setFirstName(user.getFirstName());
	 this.setLastName(user.getLastName());
	 this.setPassword(user.getPassword());
	 this.setRole(user.getRole());
	 this.setPhone(user.getPhone());
	 this.setEmail(user.getEmail());
	 
	    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
	    authorities.add(new SimpleGrantedAuthority(getRole()));
	    this.authorities = authorities;
 }
  public JwtUserDetails() { //how to make company an Enum.
	  super();
 
    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(getRole()));
    this.authorities = authorities;
  }

  @JsonIgnore
  public boolean isAccountNonExpired() { //Indicates whether the user's account has expired. An expired account cannot be authenticated.
    return true;
  }

  @JsonIgnore
  public boolean isAccountNonLocked() { //Indicates whether the user is locked or unlocked. A locked user cannot be authenticated.
    return true;
  }

  @JsonIgnore
  public boolean isCredentialsNonExpired() {  //Indicates whether the user's credentials (password) has expired. Expired credentials prevent authentication.
    return true;
  }

  public Collection<? extends GrantedAuthority> getAuthorities() { //Returns the authorities granted to the user. Cannot return null.
    return authorities;
  }

  public boolean isEnabled() { //Indicates whether the user is enabled or disabled. A disabled user cannot be authenticated.
    return true;
  }

@Override
public String getUsername() {
	
	return super.getEmail();
}

}

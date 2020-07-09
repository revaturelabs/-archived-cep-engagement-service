package com.cepengagementservice.security;

//Provides an in memory implementation of UserDetailsService storing the user credentials. @author Nicholas Larkin

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cepengagementservice.Services.UserServices;

@Service
public class JwtInMemoryUserDetailsService extends UserServices implements UserDetailsService {

  public JwtUserDetails loadUserByEmail(String email) throws UsernameNotFoundException { // Loads user in JwtUserDetails Format.

    return  new JwtUserDetails( getUserByEmail(email));
  }

@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // can return the user without encrypted for future functionality.

	 return (UserDetails) loadUserByEmail(email);
}
   

}
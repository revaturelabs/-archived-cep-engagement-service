package com.cepengagementservice.security;

//Provides an in memory implementation of UserDetailsService storing the user credentials. @author Nicholas Larkin

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
    inMemoryUserList.add(new JwtUserDetails(1L, "basic@email.com",
        "defaultpassword", "companyname", "basicuser", "0000000")); //(Long userId, String email, String password, String company, String role, String phone)
  }

 // @Override
  public JwtUserDetails loadUserByEmail(String email) throws UsernameNotFoundException { //might change this to EmailNotFoundException
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getEmail().equals(email)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", email));
    }

    return findFirst.get();
  }

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // to implement UserDetailService
	return null;
}

}
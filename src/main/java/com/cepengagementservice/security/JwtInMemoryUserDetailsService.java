package com.cepengagementservice.security;

//Provides an in memory implementation of UserDetailsService storing the user credentials. @author Nicholas Larkin

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cepengagementservice.Services.UserServices;

//Change from UserServices to JwtUserDetails

@Service
public class JwtInMemoryUserDetailsService extends UserServices implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  


  public JwtUserDetails loadUserByEmail(String email) throws UsernameNotFoundException { //might change this to EmailNotFoundException
	   
  /*  Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getEmail().equals(email)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", email));
    }*/

    return  new JwtUserDetails( getUserByEmail(email));
  }





//@Override
//public  UserDetails  loadUserByUsername(String email) throws UsernameNotFoundException {
//	// TODO Auto-generated method stub
//	    return  new JwtUserDetails(getUserByEmail(email));
//}

//@Bean
//public UserDetailsService userDetailsService(){
//	return userDetailsService;
//}

@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	 return (UserDetails) loadUserByEmail(email);
}
  
  

}
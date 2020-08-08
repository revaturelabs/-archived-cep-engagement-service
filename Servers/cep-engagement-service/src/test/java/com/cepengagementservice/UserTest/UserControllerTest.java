package com.cepengagementservice.UserTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cepengagementservice.Controllers.UsersControllers;
import com.cepengagementservice.Models.Request;
import com.cepengagementservice.Models.User;
import com.cepengagementservice.Models.UserProfile;
import com.cepengagementservice.Services.UserServices;



@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Mock
    private UserServices userServices = mock(UserServices.class);
    
    @Mock
    private BCryptPasswordEncoder BCPEncoder = mock (BCryptPasswordEncoder.class);
    @InjectMocks
    private UsersControllers usersControllers;


    @Test
    public void testUserAdd(){
        User user = new User(1,"first", "last","p","pass", "comp","role", "888", true, null, new ArrayList<Request>());
        
        
        when(userServices.addUser(any(User.class))).thenReturn(true);
        when(BCPEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
    
        assertEquals(new ResponseEntity<>(HttpStatus.OK), usersControllers.add(user), "Response should be OK when inserting new user.");
    }
 

    @Test
    public void testUserAddSameEmail(){
        User user = new User(1,"first", "last","p","pass", "comp","role", "888", true, null, new ArrayList<Request>());
        when(BCPEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
        when(userServices.addUser(any(User.class))).thenReturn(false);
        assertEquals(new ResponseEntity<String>("Email already in use",HttpStatus.CONFLICT ), usersControllers.add(user), "Response should be CONFLICT when insert a user with same email.");
 
    }


    @Test
    public void testUserGetByEmail(){
        User user = new User(1,"first", "last","p","pass", "comp","role", "888", true, null, new ArrayList<Request>());
        when(userServices.getUserByEmail(user.getEmail())).thenReturn(user);
        assertEquals(new ResponseEntity<User>(user, HttpStatus.OK), usersControllers.getByEmail(user.getEmail()), "User should be returned if email is passed.");
    }


    @Test
    public void testUserGetByEmailNotFound(){
        User user = new User(1,"first", "last","p","pass", "comp","role", "888", true, null, new ArrayList<Request>());
        User user2 = null;
        when(userServices.getUserByEmail(user.getEmail())).thenReturn(null);
        assertEquals(new ResponseEntity<User>(user2, HttpStatus.NO_CONTENT), usersControllers.getByEmail(user.getEmail()), "User should not be returned if user doesn't exist is passed.");
    }

    @Test
    public void testUserGetById(){
        User user = new User(1,"first", "last","p","pass", "comp","role", "888", true, null, new ArrayList<Request>());
        when(userServices.getUserById(user.getUserId())).thenReturn(user);
        assertEquals(new ResponseEntity<User>(user, HttpStatus.OK), usersControllers.getById(user.getUserId()), "User should be returned if id is passed.");
    }

    public void testUserGetByIdNotFound(){
        User user = new User(1,"first", "last","p","pass", "comp","role", "888", true, null, new ArrayList<Request>());
        when(userServices.getUserById(user.getUserId())).thenReturn(null);
        User user2 =null;
        assertEquals(new ResponseEntity<User>(user2, HttpStatus.NOT_FOUND), usersControllers.getById(user.getUserId()), "User should not be returned if id is not found.");
    }
    
}
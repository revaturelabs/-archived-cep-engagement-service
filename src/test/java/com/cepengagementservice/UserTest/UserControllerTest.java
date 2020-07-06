package com.cepengagementservice.UserTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.cepengagementservice.Controllers.UsersControllers;
import com.cepengagementservice.Models.User;
import com.cepengagementservice.Services.UserServices;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Mock
    private UserServices userServices = mock(UserServices.class);
    
    @InjectMocks
    private UsersControllers usersControllers;


    @Test
    public void testUserAdd(){
        User user = new User(1,"first", "last","p","pass", "comp","role", "888");
        
        
        when(userServices.addUser(any(User.class))).thenReturn(true);
    
        assertEquals(new ResponseEntity<>(HttpStatus.OK), usersControllers.add(user), "Response should be OK when inserting new user.");
    }
 

    @Test
    public void testUserAddSameEmail(){
        User user = new User(1,"first", "last","p","pass", "comp","role", "888");
        
        when(userServices.addUser(any(User.class))).thenReturn(false);
        assertEquals(new ResponseEntity<String>("Email already in use",HttpStatus.CONFLICT ), usersControllers.add(user), "Response should be CONFLICT when insert a user with same email.");
 
    }


    @Test
    public void testUserGetByEmail(){
        User user = new User(1,"first", "last","p","pass", "comp","role", "888");
        when(userServices.getUserByEmail(user.getEmail())).thenReturn(user);
        assertEquals(new ResponseEntity<User>(user, HttpStatus.OK), usersControllers.getByEmail(user.getEmail()), "User should be returned if email is passed.");
    }


    @Test
    public void testUserGetByEmailNotFound(){
        User user = new User(1,"first", "last","p","pass", "comp","role", "888");
        User user2 = null;
        when(userServices.getUserByEmail(user.getEmail())).thenReturn(null);
        assertEquals(new ResponseEntity<User>(user2, HttpStatus.NO_CONTENT), usersControllers.getByEmail(user.getEmail()), "User should not be returned if user doesn't exist is passed.");
    }

    @Test
    public void testUserGetById(){
        User user = new User(1,"first", "last","p","pass", "comp","role", "888");
        when(userServices.getUserById(user.getUserId())).thenReturn(user);
        assertEquals(new ResponseEntity<User>(user, HttpStatus.OK), usersControllers.getById(user.getUserId()), "User should be returned if id is passed.");
    }

    public void testUserGetByIdNotFound(){
        User user = new User(1,"first", "last","p","pass", "comp","role", "888");
        when(userServices.getUserById(user.getUserId())).thenReturn(null);
        User user2 =null;
        assertEquals(new ResponseEntity<User>(user2, HttpStatus.NOT_FOUND), usersControllers.getById(user.getUserId()), "User should not be returned if id is not found.");
    }
    
}
package com.cepengagementservice.UserTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.cepengagementservice.Models.Request;
import com.cepengagementservice.Models.User;
import com.cepengagementservice.Repositories.UserRepository;
import com.cepengagementservice.Services.UserServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//Using Mockito 
/* MockitoJUnitRunner <J4
    MockitoExtension.class
However, it cannot be used if another, i.e. SpringRunners.
*/

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    
    @Mock
    UserRepository USMock = mock(UserRepository.class);

    @InjectMocks
    UserServices userService;
    
    @Test
    public void testAddUserIfEmailNotOccupied(){
        User user = new User(1,"first", "last","p","pass", "comp","role", "888",new ArrayList<Request>());
        
        when(USMock.findByEmail(user.getEmail())).thenReturn(null);

        assertTrue(userService.addUser(user),"User should be created if email not in database.");

    }

    @Test
    public void  testAddUserIfEmailOccupied(){
        User user = new User(1,"first", "last","p","pass", "comp","role", "888", new ArrayList<Request>());
        User userTwo = new User(2,"second", "last","p","pass", "comp","role", "888", new ArrayList<Request>());
        when(USMock.findByEmail(userTwo.getEmail())).thenReturn(user);

        assertFalse(userService.addUser(userTwo), "Users with same email shouldn't be added.");
    }


    @Test
    public void testGetAllUsers(){
        User user = new User(1,"first", "last","pa@stubmail.com","pass", "comp","role", "888", new ArrayList<Request>());
        User userTwo = new User(2,"second", "last","pb@stubmail.com","pass", "comp","role", "888", new ArrayList<Request>());
       
        List<User> users = new ArrayList<User>();
        users.add(user);
        users.add(userTwo);

        when(USMock.findAll()).thenReturn(users);
  
        assertEquals( users,userService.getAllUsers(), "getAll should return a list of Users");
    }

    @Test
    public void testGetAllUsersShouldHaveSavedUsers(){
        User user = new User(1,"first", "last","pa@stubmail.com","pass", "comp","role", "888",new ArrayList<Request>());
        User userTwo = new User(2,"second", "last","pb@stubmail.com","pass", "comp","role", "888",new ArrayList<Request>());
        User userThree = new User(3,"third", "last","pc@stubmail.com","pass", "comp","role", "888",new ArrayList<Request>());
      
        List<User> users = new ArrayList<User>();
        List<User> usersTwo = new ArrayList<User>();
        users.add(user);
        users.add(userTwo);
        usersTwo.add(user);
        usersTwo.add(userTwo);
        usersTwo.add(userThree);
        when(USMock.findAll()).thenReturn(users);
 
        assertNotEquals( usersTwo,userService.getAllUsers(), "getAll should not return users not saved");
    }
}
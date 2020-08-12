package com.cepengagementservice.UserTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import java.util.Date;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.cepengagementservice.Models.UserProfile;
import com.cepengagementservice.Controllers.UsersControllers;
import com.cepengagementservice.Models.Request;
import com.cepengagementservice.Models.ResetPassword;
import com.cepengagementservice.Models.Request.RequestType;
import com.cepengagementservice.Models.Request.Status;
import com.cepengagementservice.Models.User;
import com.cepengagementservice.Services.RequestService;
import com.cepengagementservice.Services.UserBatchService;
import com.cepengagementservice.Services.UserServices;



@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Mock
    private UserServices userServices = mock(UserServices.class);
    
    @Mock
    private BCrypt mockBCrypt = mock (BCrypt.class); 
    @Mock
    private BCryptPasswordEncoder BCPEncoder = mock (BCryptPasswordEncoder.class);
    @Mock
    private RequestService requestService = mock (RequestService.class);
    @InjectMocks
    private UsersControllers usersControllers;


    @Test
    public void testUserAdd(){
        User user = new User("first", "last","p","pass", "comp","role", "888");
        
        
        when(userServices.addUser(any(User.class))).thenReturn(true);
        when(BCPEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
    
        assertEquals(new ResponseEntity<>(HttpStatus.OK), usersControllers.add(user), "Response should be OK when inserting new user.");
    }
 

    @Test
    public void testUserAddSameEmail(){
        User user = new User("first", "last","p","pass", "comp","role", "888");
        when(BCPEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
        when(userServices.addUser(any(User.class))).thenReturn(false);
        assertEquals(new ResponseEntity<String>("Email already in use",HttpStatus.CONFLICT ), usersControllers.add(user), "Response should be CONFLICT when insert a user with same email.");
 
    }


    @Test
    public void testUserGetByEmail(){
        User user = new User("first", "last","p","pass", "comp","role", "888");
        when(userServices.getUserByEmail(user.getEmail())).thenReturn(user);
        assertEquals(new ResponseEntity<User>(user, HttpStatus.OK), usersControllers.getByEmail(user.getEmail()), "User should be returned if email is passed.");
    }


    @Test
    public void testUserGetByEmailNotFound(){
        User user = new User("first", "last","p","pass", "comp","role", "888");
        User user2 = null;
        when(userServices.getUserByEmail(user.getEmail())).thenReturn(null);
        assertEquals(new ResponseEntity<User>(user2, HttpStatus.NO_CONTENT), usersControllers.getByEmail(user.getEmail()), "User should not be returned if user doesn't exist is passed.");
    }

    @Test
    public void testUserGetById(){
        User user = new User("first", "last","p","pass", "comp","role", "888");
        when(userServices.getUserById(user.getUserId())).thenReturn(user);
        assertEquals(new ResponseEntity<User>(user, HttpStatus.OK), usersControllers.getById(user.getUserId()), "User should be returned if id is passed.");
    }

    @Test
    public void testUserGetByIdNotFound(){
        User user = new User("first", "last","p","pass", "comp","role", "888");
        when(userServices.getUserById(user.getUserId())).thenReturn(null);
        User user2 =null;
        assertEquals(new ResponseEntity<User>(user2, HttpStatus.NO_CONTENT), usersControllers.getById(user.getUserId()), "User should not be returned if id is not found.");
    }
    
//    @Test
//    public void testResetPassword(){
//        User user = new User(1,"first", "last","p","pass", "comp","role", "888", true, new ArrayList<Request>());
//        user.setPassword(BCPEncoder.encode("pass"));
//        when(userServices.getUserByEmail(user.getEmail())).thenReturn(user);
//        when(mockBCrypt.checkpw("pass", BCPEncoder.encode("pass"))).thenReturn(true);
//        ResetPassword reset = new ResetPassword(BCPEncoder.encode("pass"), "test", "test", user.getEmail());
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        assertEquals(new ResponseEntity<String>("Success", HttpStatus.OK), usersControllers.resetPassword(reset, request), "Status should be success if password is reset.");
//    }

    @Test
    public void testUpdateRequest(){
        Date startTime = new Date();
        Date endTime = new Date();
        Request request = new Request("1", 1, startTime, endTime, false, Status.Pending, RequestType.Talent, "Test");
        request.setRequestId(1);
        when(requestService.findByRequestId(request.getRequestId())).thenReturn(request);
        assertEquals(new ResponseEntity<Request>(request, HttpStatus.OK), usersControllers.updateRequest(request.getRequestId(), request), "Status should equal if it updates correctly.");
    }
    
    @Test
    public void testDeleteRequest(){
        Date startTime = new Date();
        Date endTime = new Date();
        Request request = new Request("1", 1, startTime, endTime, false, Status.Pending, RequestType.Talent, "Test");
        request.setRequestId(1);
        when(requestService.findByRequestId(request.getRequestId())).thenReturn(request);
        assertEquals(new ResponseEntity<Request>(HttpStatus.NO_CONTENT), usersControllers.deleteRequest(request.getRequestId()), "Status should be no content if deleted.");
    }

    @Test
    public void testDeleteRequestNotFound(){
        assertEquals(new ResponseEntity<String>("Unable to Delete. Request ID: 1 not found.", HttpStatus.NOT_FOUND), usersControllers.deleteRequest(1), "Status should be not found.");
    }
}
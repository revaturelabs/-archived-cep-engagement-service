package com.cepengagementservice.UserTest;
/*
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.cepengagementservice.Controllers.UsersControllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
*/
/*
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@WebMvcTest()
public class UserIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private UsersControllers usersControllers;

 
    @Test
    public void testUserAdd(){
      try{
        mockMvc.perform(MockMvcRequestBuilders.post("users/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content( "{'userId': 1,'firstName': 'first','lastName': 'last','email': 'pastubmail.com', 'password': 'pass','company': 'comp','role': 'role', 'phone': '888'}")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    catch(Exception e){}
    }

    @Test
    public void testUserAddSameEmail(){
        
    try{
   
        mockMvc.perform(MockMvcRequestBuilders.post("users/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content( "{'userId': 1,'firstName': 'first','lastName': 'last','email': 'pa@stubmail.com', 'password': 'pass','company': 'comp','role': 'role', 'phone': '888'}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    catch(Exception e){}
    }
    
}*/
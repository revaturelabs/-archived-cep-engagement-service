package com.register.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.register.model.PendingUser;
import com.register.repo.PendingUserRepo;

@Transactional
@Rollback(false)
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class PendingUserServiceImplTest {

	
	@Mock
    PendingUserRepo repo = mock(PendingUserRepo.class);

    @InjectMocks
    private PendingUserServiceImpl userService;
    
    private List<PendingUser> users;
    
    
    @Before
    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    	this.userService = new PendingUserServiceImpl();
    }
    
    @Test
    void testAllPendingsUsers() {
//    	users.add((new PendingUser(1, "test", "test", "test", "test", "test", "test")));
//    	System.out.println(userService.allPendingUsers());
//    	userService.addUser(new PendingUser(1, "test", "test", "test", "test", "test", "test"));
    	
    	assertTrue(userService.allPendingUsers().isEmpty());
    }
    
//    @Test
//    void testAddUser() {
////    	System.out.println(0);
////    	System.out.println(this.users.get(0));
//    	System.out.println(userService.allPendingUsers());
////    	given(userService.allPendingUsers()).willReturn(users);
//    	PendingUser temp = new PendingUser("test", "test", "adduser", "test", "test", "test");
////    	users.add(temp);
//    	repo.save(temp);
//    	userService.addUser(temp);
////    	
//    	System.out.println(userService.allPendingUsers());
////    	when(repo.findAll()).thenReturn(users);
////    	
////    	assertEquals(users, userService.allPendingUsers());
//    	
//    	//    	assertEquals(userService.findById(1), users.get(0));
//    }
    
//    @Test
//    void testFindByEmail() {
//    	PendingUser temp = new PendingUser("test", "test", "adduser", "test", "test", "test");
////    	System.out.println(userService.findByEmail(email));
//    	when(repo.save(Mockito.any(PendingUser.class))).thenReturn(temp);
//    	userService.addUser(temp);
//    	System.out.println(userService.allPendingUsers());
////    	System.out.println(userService.findByEmail(temp.getEmail()).getEmail());
//    	assertEquals(userService.findByEmail(temp.getEmail()).getEmail(), temp.getEmail());
//    }
}

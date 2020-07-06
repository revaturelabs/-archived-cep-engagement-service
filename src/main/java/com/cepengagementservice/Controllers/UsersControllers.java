package com.cepengagementservice.Controllers;

import java.util.List;

import com.cepengagementservice.Models.User;
import com.cepengagementservice.Services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UsersControllers {

    @Autowired
    private UserServices userService;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<?> getAll() {
        List<User> users = userService.getAllUsers();
        if (users != null) {
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.NO_CONTENT);
    }

    // Future, maybe return user.
    // Change logic in service.
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    ResponseEntity<?> add(@RequestBody User user) {
        if (userService.addUser(user)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<String>("Email already in use", HttpStatus.CONFLICT);
    }

    // DO SOME ERROR CHECKING.
    // @RequestMapping(method = RequestMethod.POST, value = "/add/batch")
    // ResponseEntity<?> add(@RequestBody Batch batch, @RequestParam Integer id) {
    // User usr = userService.getUserById(id);
    // if (usr != null) {
    // usr.addBatch(batch);
    // usr = userService.updateUser(usr);
    // return new ResponseEntity<User>(usr, HttpStatus.ACCEPTED);

    // }
    // return new ResponseEntity<User>(usr, HttpStatus.NOT_FOUND);

    // }

    @RequestMapping(method = RequestMethod.GET, value = "/email/")
    public ResponseEntity<?> getByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/")
    public ResponseEntity<?> getById(@RequestParam Integer id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
    }

    // @RequestMapping(method = RequestMethod.GET, value = "/batch/")
    // public ResponseEntity<?> getByBatchId(@RequestParam String batchId) {
    // List<User> user = userService.getByBatchId(batchId);
    // if (user != null) {
    // return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    // }
    // return new ResponseEntity<List<User>>(user, HttpStatus.NO_CONTENT);
    // }
}
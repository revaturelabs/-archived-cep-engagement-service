package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

@RestController
@RequestMapping("/api")
public class UserController {
	@GetMapping("/getUsers")
	public List<User> allUser(){
			List<User> uList = new ArrayList<>();
		
			uList.add(new User("Feng", "Lin"));
			uList.add(new User("Talon", "Mid"));
			uList.add(new User("Master", "Yi"));
			uList.add(new User("Nasus", "God"));
			uList.add(new User("Ashe", "Adc"));
		
		System.out.println("getting all users"); //
		return uList;
	}
}

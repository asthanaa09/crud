package com.crud.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.models.User;
import com.crud.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("")
	public ResponseEntity<?> createOrUpdate(@RequestBody User user,
			@RequestParam(value = "id", required = false) Long id) {
		
		if(id != null)
			user.setId(id);   
		
		user = userService.createOrUpdate(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping(value="/query")
	public ResponseEntity<?> getUser(@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "surName", required = false) String surName,
			@RequestParam(value = "pincode", required = false) String pincode) {
		
		List<User> users = userService.searchUsers(firstName, surName, pincode);
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam("id") Long userID,
			@RequestParam(value = "deleteFromDb", required = false) Boolean deleteFromDb) {
		User user = userService.delete(userID, deleteFromDb);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<List<User>>(userService.findAllUsers(), HttpStatus.OK);
	}
}

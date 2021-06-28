package com.crud.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.models.User;
import com.crud.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public User createOrUpdate(User updated) {
		
		if(updated.getId() == null) {
			// Create
			logger.debug("Creating new User");
			updated = userRepository.save(updated);
			
		} else {
			// Update
			User original = userRepository.findById(updated.getId()).orElseThrow(() -> new RuntimeException("Invalid user id"));
			logger.debug("Updating user...");
			original.setFirstName(updated.getFirstName());
			original.setSurName(updated.getSurName());
			original.setDob(updated.getDob());
			original.setEmail(updated.getEmail());
			original.setMobile(updated.getMobile());
			original.setPincode(updated.getPincode());
			original.setPassword(updated.getPassword());
			updated = userRepository.save(original);
		}
		
		return updated;
	}
	
	/**
	 * Delete user from records
	 * 
	 * @param userID 
	 * @param forever - if true delete record permanently from DB, otherwise marked as deleted
	 * 
	 * @return - deleted user Instance
	 */
	public User delete(Long userID, Boolean forever) {
		User user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("Invalid User id"));
		
		if(forever != null && forever) 
			userRepository.delete(user);
		else {
			user.setDeleted(true);
			user = userRepository.save(user);
		}
		
		return user;
	}
	
	/**
	 * Find user based on their first name, surname or pincode
	 * 
	 * @param firstName
	 * @param surName
	 * @param pincode
	 * @return
	 */
	public List<User> searchUsers(String firstName, String surName, String pincode) {
		if((firstName == null || firstName.isBlank())
				&& (surName == null || surName.isBlank())
				&& (pincode == null || pincode.isBlank()))
			throw new RuntimeException("Missing search parameters");
		logger.debug("Find Customer by FirstName {}, Sur Name {} Or Pincode {} ", firstName, surName, pincode);
		return userRepository.findByNameAndPincode(firstName, surName, pincode);
	}
	
	/**
	 * Find all user records sorted by DOB and CreationTime (Joining time)
	 * 
	 * @return 
	 */
	public List<User> findAllUsers() {
		List<User> users = userRepository.findAllOrderByDobDesc();
		
		if(users == null || users.size() == 0)
			return users;
		
		// Set all users password as null for security
		if(users != null && !users.isEmpty()) 
			users.forEach((user) -> user.setPassword(null));
		
		// Sor users by their creation/joining time
		Collections.sort(users, new Comparator<User>() {
			
			@Override
			public int compare(User user1, User user2) {
				return (user1.getCreationTime().before(user2.getCreationTime()) ? -1 : 1);
			};
		});
		
		return users;
	}
}

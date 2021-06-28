package com.crud.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.crud.models.User;
import com.crud.repository.UserRepository;
import com.crud.utils.Utils;

/**
 * Ref: https://medium.com/@gustavo.ponce.ch/spring-boot-restful-junit-mockito-hamcrest-eclemma-5add7f725d4e
 * 
 * @author Anurag Asthana
 *
 */
@SpringBootTest
// @Ignore
@RunWith(SpringRunner.class)
class UserServiceTest {

	@Autowired
	private UserService userService;
	
	/**
	 * Don't hit database for testing instead use Cashing.
	 */
	@MockBean
	private UserRepository userRepository;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void checkCreateUser() {
		logger.debug("Started Test to User create API");
		User user = buildTestUser();
		
		// when calling Repository
		user.setId(null);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		User result = userService.createOrUpdate(user);
		user.setId(2l);
		
		// Check case is fail or not
		assertThat(result.getId()).isEqualTo(user.getId());
		logger.debug("Completed Test to User create API Passed"); 
	}
	
	@Test
	public void checkUpdateUser() {
		logger.debug("Started Test to User Update Service ");
		User user = buildTestUser();
		
		// When below query is run then get specified user instance
		Mockito.<Optional<User>>when(userRepository.findById(2l)).thenReturn(Optional.of(user));
		
		// Update user details
		user.setSurName("Kumar");
		Mockito.when(userRepository.save(user)).thenReturn(user);
		
		User result = userService.createOrUpdate(user);
		
		assertEquals(result.getId(), user.getId());
		assertThat(result.getSurName()).isNotEqualTo(buildTestUser().getSurName());
		logger.debug("Completed Test to User Update Service Passed");
	}

	@Test
	public void checkDeleteUser() {
		logger.debug("Started Testing User delete Service ");
		User user = buildTestUser();
		
		// When below query is run then get specified user instance
		Mockito.<Optional<User>>when(userRepository.findById(2l)).thenReturn(Optional.of(user));
		user.setDeleted(true);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		
		// Soft delete test
		User result = userService.delete(2l, false);
		assertThat(result.isDeleted()).isNotEqualTo(buildTestUser().isDeleted());
		
		// Hard Delete User 
		user = buildTestUser();
		Mockito.<Optional<User>>when(userRepository.findById(2l)).thenReturn(Optional.of(user));
		
		userService.delete(2l, true);
		verify(userRepository, times(1)).delete(user);
		logger.debug("Completed Testing User delete Service Passed");
	}
	
	@Test
	public void checkFindAllUser() {
		logger.debug("Testing Find all Users list ");
		
		List<User> testUsres = Stream.of(
				buildTestUser("Anurag", "Asthana", null, "0111134422", null, "454567", "user1", null, Utils.dayBeforeFromNow(2)),
				buildTestUser("Arun", "Kumar", null, "0141134422", null, "454557", "user2", null, Utils.dayBeforeFromNow(4)),
				buildTestUser("Abhishek", "Singh", null, "1134422", null, "454567", "user3", null, Utils.dayBeforeFromNow(6))).collect(Collectors.toList());
		
		// When
		Mockito.when(userRepository.findAllByDobOrderByDobDesc()).thenReturn(testUsres);
		List<User> users = userService.findAllUsers();
		assertEquals(testUsres.size(), users.size());
		logger.debug("Testing Find all Users list Passed");
	}
	
	/**
	 * Get User instance with default values
	 * 
	 * @return - User
	 */
	private User buildTestUser() {
		return buildTestUser("Anurag", "Asgthana", "1234567890", "09/01/1996", "1234567890", "123456", "anu123", "12345678", Utils.dayBeforeFromNow(2));
	}
	
	/**
	 * Building User instance for testing purpose
	 * 
	 * @return - User
	 */
	private User buildTestUser(String firstName, String surName, String dob, String mobile, String email, String pincode, 
			String userName, String password, Date creationTime) {
		User user = new User(firstName, 
				surName, userName, 
				password, email, 
				mobile, Utils.stringToDate(dob, Utils.CustomDateFromat.FORMAT_CODE_01), 
				null, pincode, 
				null, false);
		
		user.setCreationTime(creationTime);
		return user;
	}
}

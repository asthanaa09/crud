package com.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query(value = "SELECT * FROM USER "
			+ "WHERE DELETED = 0 "
			+ "AND (IFNULL(FIRST_NAME, '') LIKE %:?1%) "
			+ "AND (IFNULL(SUR_NAME, '') LIKE %:?2%) "
			+ "AND (IFNULL(PINCODE, '') LIKE %:?3%)", nativeQuery = true)
	public List<User> findByNameAndPincode(String firstName, String lastName, String pincode);
	
	@Query(value="SELECT u FROM User u ORDER BY u.dob DESC")
	public List<User> findAllOrderByDobDesc();
} 

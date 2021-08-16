package com.springbootjpa.session.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springbootjpa.session.model.User;

public interface UserRepository extends JpaRepository<User, Integer>
{
	
	User findByIdAndNameContainingIgnoreCase(int userId,String userName);
	
	List<User>  findAllByOrderByNameDesc();

	List<User> findByNameStartingWith(String name);
	
	@Query("Select u from  User u where u.name LIKE  %?1%")
	List<User> findByNameLike(String name);
	
	@Query("Select u from  User u where u.name NOT LIKE  %?1%")
	List<User> findByNameNotLike(String name);
	
	List<User> findByNameNot(String name);
	
	
	List<User> findByCreatedOnBetween(Date fromDate,Date toDate);
	
	List<User> findByAge(int age);
	
	
}

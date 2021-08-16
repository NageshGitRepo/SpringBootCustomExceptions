package com.springbootjpa.session.controller;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootjpa.session.model.User;
import com.springbootjpa.session.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController 
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/addUser")
	public ResponseEntity<?> saveUserDetails(@Valid @RequestBody User user) 
	{
		return new ResponseEntity<User>(userService.saveUserDetails(user),HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserDetailsById(@PathVariable("userId") int userId )
	{
		return userService.getUserDetailsById(userId);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUserById(@PathVariable("userId") int userId ) {
		return new ResponseEntity<String>(userService.deleteUserById(userId),HttpStatus.OK);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<User> deleteUserById(@PathVariable("userId") int userId,@RequestBody User user )
	{
		return new ResponseEntity<User>(userService.updateUser(userId,user),HttpStatus.OK);
	}
	
	@GetMapping("/allUsers")
	public ResponseEntity<List<User>> getAllUserDetailsOrderByName()
	{
		return new ResponseEntity<List<User>>(userService.getUserDetailsOrderByName(),HttpStatus.OK);
	}
	
	@GetMapping("/byAge/{age}")
	public ResponseEntity<List<User>> getAllUserByAge(@PathVariable("age") int age)
	{
		return new ResponseEntity<List<User>>(userService.getUserDetailsByAge(age),HttpStatus.OK);
	}
	
	@GetMapping("/startwith/{name}") 
	public ResponseEntity<List<User>> getUsersByNameStartWith(@PathVariable("name") String name) 
	{
		return new ResponseEntity<List<User>>(userService.getUsersByNameStartWith(name),HttpStatus.OK);
	}
	
	@GetMapping("/nameLike/{name}")
	public ResponseEntity<List<User>> getNameLike(@PathVariable("name") String name)
	{
		return new ResponseEntity<List<User>>(userService.getNameStartLike(name),HttpStatus.OK);
	}
	
	@GetMapping("/nameNotLike/{name}")
	public ResponseEntity<List<User>> getNameNotLike(@PathVariable("name") String name)
	{
		return new ResponseEntity<List<User>>(userService.getNameStartNotLike(name),HttpStatus.OK);
	}
	
	@GetMapping("/nameNot/{name}")
	public ResponseEntity<List<User>> getNameNot(@PathVariable("name") String name)
	{
		return new ResponseEntity<List<User>>(userService.getNameNot(name),HttpStatus.OK);
	}
	@GetMapping("/createdOn/{fromDate}/{toDate}")
	public ResponseEntity<List<User>> getCreatedOn(@PathVariable("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@PathVariable("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate)
	{
			return new ResponseEntity<List<User>>(userService.getCreatedBetween(fromDate, toDate),HttpStatus.OK);
	}
}

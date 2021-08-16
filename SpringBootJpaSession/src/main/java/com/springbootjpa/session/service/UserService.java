package com.springbootjpa.session.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springbootjpa.session.exception.DateValidationException;
import com.springbootjpa.session.exception.ResourceNotFoundException;
import com.springbootjpa.session.exception.SearchResultsNotFoundException;
import com.springbootjpa.session.model.User;
import com.springbootjpa.session.repository.UserRepository;

@Service
public class UserService
{
	@Autowired
    UserRepository userRepo;
	
	public User saveUserDetails(User user)
	{ 
	  return userRepo.save(user);	
	}
	
	public ResponseEntity<User> getUserDetailsById(int userId)  
	{
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));		
	    return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	public List<User> getUserDetailsOrderByName()
	{
		List<User> userList = userRepo.findAllByOrderByNameDesc();
		
		if(userList.isEmpty())
	          throw new SearchResultsNotFoundException("Search Results not Found");
			return userList;
		
	}
	public String deleteUserById(int userId) throws ResourceNotFoundException
	{
		 User usr = getUserDetailsById(userId).getBody();
		 userRepo.deleteById(usr.getId());
		 return "User with userId: "+ userId+" deleted successfully";
	}
	public User updateUser(int userId,User user) 
	{
		User usr = getUserDetailsById(userId).getBody();
		
		usr.setCreatedBy(user.getCreatedBy());
		usr.setCreatedOn(user.getCreatedOn());
		usr.setEmailAddress(user.getEmailAddress());
		usr.setPassword(user.getPassword());
		usr.setName(user.getName());
		
		userRepo.save(usr);
		return userRepo.getById(usr.getId());
	}
	
	public List<User> getUsersByNameStartWith(String name)
	{
		
	List<User> userList = userRepo.findByNameStartingWith(name);
		if(userList.isEmpty())
          throw new SearchResultsNotFoundException("No Search Results Found with::: "+name);
		return userList;
	}
	public List<User> getNameStartLike(String name)
	{
		List<User> userList = userRepo.findByNameLike(name);
		if(userList.isEmpty())
	          throw new SearchResultsNotFoundException("No Search Results Found with::: "+name);
			return userList;
	}
	public List<User> getNameStartNotLike(String name)
	{
		List<User> userList = userRepo.findByNameNotLike(name);
		
		if(userList.isEmpty())
	          throw new SearchResultsNotFoundException("No Search Results Found with::: "+name);
			return userList;
	}
	public List<User> getNameNot(String name)
	{
		List<User> userList = userRepo.findByNameNot(name);
		
		if(userList.isEmpty())
	          throw new SearchResultsNotFoundException("No Search Results Found with::: "+name);
			return userList;
	}
	public List<User> getCreatedBetween(Date fromDate,Date toDate)
	{
		if(fromDate.after(toDate))
			throw new DateValidationException("From Date :: "+ new SimpleDateFormat("yyyy-MM-dd").format(fromDate)+ " Should be less than To Date :: "+new SimpleDateFormat("yyyy-MM-dd").format(toDate));
		return userRepo.findByCreatedOnBetween(fromDate,toDate);
	}
	
	public List<User> getUserDetailsByAge(int age)
	{
		return userRepo.findByAge(age);
	}
	
}

package com.springbootjpa.session.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springbootjpa.session.exception.DateValidationException;
import com.springbootjpa.session.exception.ResourceNotFoundException;
import com.springbootjpa.session.exception.SearchResultsNotFoundException;

@ControllerAdvice
@RestController
public class ControllerExceptionHandler  extends ResponseEntityExceptionHandler 
{
	 @Autowired
	 ErrorUtil errorUtil;
	 
	//This exception will be raised when a handler method argument annotated with @Valid failed validation 
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, 
            HttpStatus status, 
            WebRequest request) 
	{
		List<String> details = new ArrayList<String>();
		   details = ex.getFieldErrors().stream().map(e1->e1.getField()+" : "+e1.getDefaultMessage()).collect(Collectors.toList());
		
		   ErrorMessage message = new ErrorMessage(
		            new Date(),
		            "Validation Errors",
		            request.getDescription(false),details);
		   
		      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	
	//This exception is thrown when there are no records found"
	@ExceptionHandler(SearchResultsNotFoundException.class)
	public ResponseEntity<ErrorMessage> searchResultsNotFoundException(SearchResultsNotFoundException ex, WebRequest request) 
	{
		List<String> details = new ArrayList<String>();
        details.add(ex.getLocalizedMessage());
	    ErrorMessage message = new ErrorMessage(
	        new Date(),
	        "No Records Found",
	        request.getDescription(false),details);
	    return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	  }
	
		@ExceptionHandler(DateValidationException.class)
		public ResponseEntity<ErrorMessage> dateValidationException(DateValidationException ex, WebRequest request) 
		{
			List<String> details = new ArrayList<String>();
	        details.add(ex.getLocalizedMessage());
	        
		    ErrorMessage message = new ErrorMessage(
		        new Date(),
		        "Date Validation",
		        request.getDescription(false),details);
		    return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
		  }
		
	
	//This exception is thrown when a method parameter has the wrong type!
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
        MethodArgumentTypeMismatchException ex,
        WebRequest request) 
	{
		
		List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
		
		ErrorMessage message = new ErrorMessage(
	            new Date(),
	            "Invalid Arguments",
	            request.getDescription(false),details);
	   
	      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
	
	//This exception reports the result of Id not found
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request)
	{
	    ErrorMessage message = new ErrorMessage(
	            new Date(),
	            ex.getLocalizedMessage(),
	            request.getDescription(false),null);
	      return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	 }
	
	//This exception reports the result of all other exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) 
	{
		List<String> details = new ArrayList<String>();
        details.add(ex.getLocalizedMessage());
	    ErrorMessage message = new ErrorMessage(
	        new Date(),
	        "Error occurred",
	        request.getDescription(false),details);
	    return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	

}

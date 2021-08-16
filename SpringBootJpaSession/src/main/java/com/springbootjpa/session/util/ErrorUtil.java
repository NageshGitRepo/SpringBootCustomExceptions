package com.springbootjpa.session.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ErrorUtil 
{
   public ResponseEntity<Object>  getErrorMessages(BindingResult result)
   {
	   List<String> details = new ArrayList<String>();
	   details = result.getFieldErrors().stream().map(e1->e1.getField()+" : "+e1.getDefaultMessage()).collect(Collectors.toList());
	   return  new ResponseEntity<Object>(details, HttpStatus.BAD_GATEWAY);
   }
   
   public ResponseEntity<Object>  getMessage(String message)
   {
	   return  new ResponseEntity<Object>(message, HttpStatus.BAD_GATEWAY);
   }
   
}   

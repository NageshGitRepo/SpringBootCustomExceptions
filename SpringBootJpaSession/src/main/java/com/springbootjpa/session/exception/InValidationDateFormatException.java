package com.springbootjpa.session.exception;

public class InValidationDateFormatException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	
	public InValidationDateFormatException() {
		super();

	}
	
	public InValidationDateFormatException(String message) {
		super(message);

	}

	public InValidationDateFormatException(String message, Throwable t) {
		super(message, t);

	}
}	
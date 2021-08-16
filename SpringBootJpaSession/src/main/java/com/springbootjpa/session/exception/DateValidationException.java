package com.springbootjpa.session.exception;

public class DateValidationException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	
	public DateValidationException() {
		super();

	}
	
	public DateValidationException(String message) {
		super(message);

	}

	public DateValidationException(String message, Throwable t) {
		super(message, t);

	}
}	
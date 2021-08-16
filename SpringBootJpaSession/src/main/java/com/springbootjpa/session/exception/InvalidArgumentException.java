package com.springbootjpa.session.exception;

public class InvalidArgumentException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public InvalidArgumentException(String message) {
		super(message);

	}

	public InvalidArgumentException(String message, Throwable t) {
		super(message, t);

	}

}

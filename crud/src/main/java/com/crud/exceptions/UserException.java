package com.crud.exceptions;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public UserException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public UserException() {
		super();
	}
}

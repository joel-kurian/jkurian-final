package com.capgemini.exceptions;

public class AddressExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1990753557136917507L;
	
	public AddressExistsException(String message) {
		super(message);
	}
}

package com.capgemini.exceptions;

public class EmployeeExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4331545669655225813L;

	public EmployeeExistsException(String message) {
		super(message);
	}

}

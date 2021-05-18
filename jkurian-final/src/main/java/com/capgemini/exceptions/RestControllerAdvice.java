package com.capgemini.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {
	
	@ExceptionHandler(ProjectNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String projectNotFoundException(ProjectNotFoundException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(ProjectExistsException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String projectExistsException(ProjectExistsException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(DepartmentNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String departmentNotFoundException(DepartmentNotFoundException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(DepartmentExistsException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String departmentExistsException(DepartmentExistsException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(AddressNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String addressNotFoundException(AddressNotFoundException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(AddressExistsException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String addressExistsException(AddressExistsException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String employeeNotFoundException(EmployeeNotFoundException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(EmployeeExistsException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String employeeExistsException(EmployeeExistsException ex) {
		return ex.getMessage();
	}
}

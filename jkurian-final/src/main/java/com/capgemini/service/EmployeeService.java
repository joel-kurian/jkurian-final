package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.Employee;
import com.capgemini.exceptions.EmployeeExistsException;
import com.capgemini.exceptions.EmployeeNotFoundException;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(int id) throws EmployeeNotFoundException;
	
	Employee addEmployee(Employee e) throws EmployeeExistsException;
	
	void deleteEmployee(Employee e) throws EmployeeNotFoundException;
	
	Employee updateEmployee(Employee e) throws EmployeeNotFoundException;
}

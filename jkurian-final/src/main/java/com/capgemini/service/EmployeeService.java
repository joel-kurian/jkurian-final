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
	
	Employee updateEmployeeDetails(Employee e) throws EmployeeNotFoundException;
	
	Employee updateEmployeeAddress(Employee e) throws EmployeeNotFoundException;
	
	Employee updateEmployeeDepartment(Employee e) throws EmployeeNotFoundException;
	
	Employee updateEmployeeProject(Employee e) throws EmployeeNotFoundException;
}

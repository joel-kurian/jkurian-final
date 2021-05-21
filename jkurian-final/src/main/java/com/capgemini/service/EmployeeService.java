package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.Employee;
import com.capgemini.exceptions.AddressNotFoundException;
import com.capgemini.exceptions.DepartmentNotFoundException;
import com.capgemini.exceptions.EmployeeExistsException;
import com.capgemini.exceptions.EmployeeNotFoundException;
import com.capgemini.exceptions.ProjectNotFoundException;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(int id) throws EmployeeNotFoundException;
	
	Employee addEmployee(Employee e) throws EmployeeExistsException, DepartmentNotFoundException, ProjectNotFoundException, AddressNotFoundException;
	
	void deleteEmployee(Employee e) throws EmployeeNotFoundException;
	
	Employee updateEmployeeDetails(Employee e) throws EmployeeNotFoundException;
	
	Employee updateEmployeeAddress(Employee e) throws EmployeeNotFoundException, AddressNotFoundException;
	
	Employee updateEmployeeDepartment(Employee e) throws EmployeeNotFoundException, DepartmentNotFoundException;
	
	Employee updateEmployeeProject(Employee e) throws EmployeeNotFoundException, ProjectNotFoundException;
}

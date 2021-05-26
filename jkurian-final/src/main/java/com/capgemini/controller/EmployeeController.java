package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capgemini.entity.Employee;
import com.capgemini.exceptions.AddressNotFoundException;
import com.capgemini.exceptions.DepartmentNotFoundException;
import com.capgemini.exceptions.EmployeeExistsException;
import com.capgemini.exceptions.EmployeeNotFoundException;
import com.capgemini.exceptions.ProjectNotFoundException;
import com.capgemini.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService es;
	
	/*@GetMapping("/test")
	public ResponseEntity<Employee> consumeMessage() {
		RestTemplate rt = new RestTemplate();
		Employee m = rt.getForObject("http://localhost:8080/employee/id/1", Employee.class);
		return new ResponseEntity<Employee>(m, HttpStatus.OK);
	}*/
	
	@GetMapping("/all")
	public List<Employee> getAllEmployees() {
		return es.getAllEmployees();
	}
	
	@GetMapping("/id/{id}")
	public Employee getEmployeeById(@PathVariable int id) 
			throws EmployeeNotFoundException {
		return es.getEmployeeById(id);
	}
	
	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee e) 
			throws EmployeeExistsException, DepartmentNotFoundException, ProjectNotFoundException, AddressNotFoundException {
		return es.addEmployee(e);
	}
	
	@DeleteMapping("/delete")
	public void deleteEmployee(@RequestBody Employee e) 
			throws EmployeeNotFoundException {
		es.deleteEmployee(e);
	}
	
	@PutMapping("/updateDetails")
	public Employee updateEmployeeDetails(@RequestBody Employee e) 
			throws EmployeeNotFoundException {
		return es.updateEmployeeDetails(e);
	}
	
	@PutMapping("/updateProject")
	public Employee updateEmployeeProject(@RequestBody Employee e) 
			throws EmployeeNotFoundException, ProjectNotFoundException {
		return es.updateEmployeeProject(e);
	}
	
	@PutMapping("/updateAddress")
	public Employee updateEmployeeAddress(@RequestBody Employee e) 
			throws EmployeeNotFoundException, AddressNotFoundException {
		return es.updateEmployeeAddress(e);
	}
	
	@PutMapping("/updateDepartment")
	public Employee updateEmployeeDepartment(@RequestBody Employee e) 
			throws EmployeeNotFoundException, DepartmentNotFoundException {
		return es.updateEmployeeDepartment(e);
	}
}

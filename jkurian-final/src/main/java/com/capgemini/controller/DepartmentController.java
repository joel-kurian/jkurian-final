package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entity.Department;
import com.capgemini.exceptions.DepartmentExistsException;
import com.capgemini.exceptions.DepartmentNotFoundException;
import com.capgemini.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	DepartmentService ds;
	
	@GetMapping("/all")
	public List<Department> getAllDepartments() {
		return ds.getAllDepartments();
	}
	
	@GetMapping("/id/{id}")
	public Department getDepartmentById(@PathVariable int id) 
			throws DepartmentNotFoundException {
		return ds.getDepartmentById(id);
	}
	
	@PostMapping("/add")
	public Department addDepartment(@RequestBody Department d) 
			throws DepartmentExistsException {
		return ds.addDepartment(d);
	}
	
	@DeleteMapping("/delete")
	public void deleteDepartment(@RequestBody Department d) 
			throws DepartmentNotFoundException {
		ds.deleteDepartment(d);
	}
	
	@PutMapping("/update")
	public Department updateDepartment(@RequestBody Department d) 
			throws DepartmentNotFoundException {
		return ds.updateDepartment(d);
	}
}

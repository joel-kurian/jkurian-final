package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.Department;
import com.capgemini.exceptions.DepartmentExistsException;
import com.capgemini.exceptions.DepartmentNotFoundException;

public interface DepartmentService {
	
	List<Department> getAllDepartments();
	
	Department getDepartmentById(int id) throws DepartmentNotFoundException;
	
	Department addDepartment(Department d) throws DepartmentExistsException;
	
	void deleteDepartment(Department d) throws DepartmentNotFoundException;
	
	Department updateDepartment(Department d) throws DepartmentNotFoundException;
}

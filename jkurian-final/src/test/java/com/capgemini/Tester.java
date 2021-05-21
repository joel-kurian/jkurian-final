package com.capgemini;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.entity.Department;
import com.capgemini.entity.Employee;
import com.capgemini.exceptions.DepartmentExistsException;
import com.capgemini.exceptions.DepartmentNotFoundException;
import com.capgemini.service.AddressService;
import com.capgemini.service.DepartmentService;
import com.capgemini.service.EmployeeService;
import com.capgemini.service.ProjectService;

@SpringBootTest
public class Tester {

	@Autowired
	EmployeeService es;
	
	@Autowired
	DepartmentService ds;
	
	@Autowired
	ProjectService ps;
	
	@Autowired
	AddressService as;
	
	@Test
	public void test() throws Exception {
		Department d = ds.getDepartmentById(14);
		ds.deleteDepartment(d);
	}
}

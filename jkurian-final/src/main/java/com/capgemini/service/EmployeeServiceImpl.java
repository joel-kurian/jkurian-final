package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entity.Employee;
import com.capgemini.exceptions.EmployeeExistsException;
import com.capgemini.exceptions.EmployeeNotFoundException;
import com.capgemini.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepo er;

	@Override
	public List<Employee> getAllEmployees() {
		return er.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
		return er.findById(id).orElseThrow(
				() -> new EmployeeNotFoundException("Employee not found"));
	}

	@Override
	public Employee addEmployee(Employee e) throws EmployeeExistsException {
		Optional<Employee> emp = er.findById(e.getEmpId());
		if (emp.isPresent())
			throw new EmployeeExistsException("Employee exists");
		return er.save(e);
	}

	@Override
	public void deleteEmployee(Employee e) throws EmployeeNotFoundException {
		er.findById(e.getEmpId()).orElseThrow(
				() -> new EmployeeNotFoundException("Employee not found"));
		er.delete(e);
	}

	@Override
	public Employee updateEmployeeDetails(Employee e) throws EmployeeNotFoundException {
		Employee emp = er.findById(e.getEmpId()).orElseThrow(
				() -> new EmployeeNotFoundException("Employee not found"));
		emp.setName(e.getName());
		emp.setSalary(e.getSalary());
		emp.setTitle(e.getTitle());
		return er.save(emp);
	}

	@Override
	public Employee updateEmployeeAddress(Employee e) throws EmployeeNotFoundException {
		Employee emp = er.findById(e.getEmpId()).orElseThrow(
				() -> new EmployeeNotFoundException("Employee not found"));
		emp.setAddr(e.getAddr());
		return er.save(emp);
	}

	@Override
	public Employee updateEmployeeDepartment(Employee e) throws EmployeeNotFoundException {
		Employee emp = er.findById(e.getEmpId()).orElseThrow(
				() -> new EmployeeNotFoundException("Employee not found"));
		emp.setDept(e.getDept());
		return er.save(emp);
	}

	@Override
	public Employee updateEmployeeProject(Employee e) throws EmployeeNotFoundException {
		Employee emp = er.findById(e.getEmpId()).orElseThrow(
				() -> new EmployeeNotFoundException("Employee not found"));
		emp.setProj(e.getProj());
		return er.save(emp);
	}

}

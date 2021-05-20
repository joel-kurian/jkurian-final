package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entity.Address;
import com.capgemini.entity.Department;
import com.capgemini.entity.Employee;
import com.capgemini.entity.Project;
import com.capgemini.exceptions.AddressNotFoundException;
import com.capgemini.exceptions.DepartmentNotFoundException;
import com.capgemini.exceptions.EmployeeExistsException;
import com.capgemini.exceptions.EmployeeNotFoundException;
import com.capgemini.exceptions.ProjectNotFoundException;
import com.capgemini.repo.AddressRepo;
import com.capgemini.repo.DepartmentRepo;
import com.capgemini.repo.EmployeeRepo;
import com.capgemini.repo.ProjectRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepo er;
	
	@Autowired
	ProjectRepo pr;
	
	@Autowired
	AddressRepo ar;
	
	@Autowired
	DepartmentRepo dr;

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
	public Employee updateEmployeeAddress(Employee e) throws EmployeeNotFoundException, AddressNotFoundException {
		Employee emp = er.findById(e.getEmpId()).orElseThrow(
				() -> new EmployeeNotFoundException("Employee not found"));
		if (e.getAddr() == null)
			throw new AddressNotFoundException("Address not found");
		Address adr = ar.findById(e.getAddr().getPin()).orElseThrow(
				() -> new AddressNotFoundException("Address not found"));
		emp.setAddr(adr);
		return er.save(emp);
	}

	@Override
	public Employee updateEmployeeDepartment(Employee e) throws EmployeeNotFoundException, DepartmentNotFoundException {
		Employee emp = er.findById(e.getEmpId()).orElseThrow(
				() -> new EmployeeNotFoundException("Employee not found"));
		if (e.getDept() == null)
			throw new DepartmentNotFoundException("Department not found");
		Department dep = dr.findById(e.getDept().getDepId()).orElseThrow(
				() -> new DepartmentNotFoundException("Department not found"));
		
		if (emp.getDept() != null) {
			emp.getDept().getEmpList().remove(emp);
			dr.save(emp.getDept());
		}
		emp.setDept(dep);
		dep.getEmpList().add(emp);
		
		dr.save(dep);
		return er.save(emp);
	}

	@Override
	public Employee updateEmployeeProject(Employee e) throws EmployeeNotFoundException, ProjectNotFoundException {
		Employee emp = er.findById(e.getEmpId()).orElseThrow(
				() -> new EmployeeNotFoundException("Employee not found"));
		if (e.getProj() == null)
			throw new ProjectNotFoundException("Project not found");
		Project proj = pr.findById(e.getProj().getProjId()).orElseThrow(
				() -> new ProjectNotFoundException("Project not found"));
		
		if (emp.getProj() != null) {
			emp.getProj().getEmpList().remove(emp);
			pr.save(emp.getProj());
		}
		emp.setProj(proj);
		proj.getEmpList().add(emp);
		pr.save(proj);
		return er.save(emp);
	}

}

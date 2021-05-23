package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.entity.Department;
import com.capgemini.entity.Employee;
import com.capgemini.exceptions.DepartmentExistsException;
import com.capgemini.exceptions.DepartmentNotFoundException;
import com.capgemini.repo.DepartmentRepo;
import com.capgemini.repo.EmployeeRepo;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	DepartmentRepo dr;
	
	@Autowired
	EmployeeRepo er;

	@Override
	public List<Department> getAllDepartments() {
		return dr.findAll();
	}

	@Override
	public Department getDepartmentById(int id) throws DepartmentNotFoundException {
		return dr.findById(id).orElseThrow(
				() -> new DepartmentNotFoundException("Department not found"));
	}

	@Override
	public Department addDepartment(Department d) throws DepartmentExistsException {
		Optional<Department> dep = dr.findById(d.getDepId());
		if (dep.isPresent())
			throw new DepartmentExistsException("Department already exists");
		return dr.save(d);
	}

	@Override
	public void deleteDepartment(Department d) throws DepartmentNotFoundException {
		Department dep = dr.findById(d.getDepId()).orElseThrow(
				() -> new DepartmentNotFoundException("Department not found"));
		for (Employee e : dep.getEmpList()) {
			e.setDept(null);
		}
		er.saveAll(dep.getEmpList());
		dr.delete(d);
	}

	@Override
	public Department updateDepartment(Department d) throws DepartmentNotFoundException {
		Department dep = dr.findById(d.getDepId()).orElseThrow(
				() -> new DepartmentNotFoundException("Department not found"));
		dep.setDepDesc(d.getDepDesc());
		dep.setDepName(d.getDepName());
		
		for(Employee e : dep.getEmpList()) {
			e.setDept(null);
		}
		er.saveAll(dep.getEmpList());
		
		dep.getEmpList().clear();
		if (d.getEmpList() != null) {
			dep.getEmpList().addAll(d.getEmpList());
		
			for (Employee e : dep.getEmpList()) {
				e.setDept(dep);
			}
			er.saveAll(dep.getEmpList());
		}
		
		return dr.save(dep);
	}

}

package com.capgemini.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}

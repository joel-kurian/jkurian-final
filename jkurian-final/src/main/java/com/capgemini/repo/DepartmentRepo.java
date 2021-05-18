package com.capgemini.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

}

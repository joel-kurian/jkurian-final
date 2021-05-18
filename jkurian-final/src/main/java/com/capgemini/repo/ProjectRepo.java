package com.capgemini.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Integer> {

}

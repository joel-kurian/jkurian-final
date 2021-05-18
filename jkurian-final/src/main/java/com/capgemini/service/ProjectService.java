package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.Project;
import com.capgemini.exceptions.ProjectExistsException;
import com.capgemini.exceptions.ProjectNotFoundException;

public interface ProjectService {
	
	List<Project> getAllProjects();
	
	Project getProjectById(int id) throws ProjectNotFoundException;
	
	Project addProject(Project p) throws ProjectExistsException;
	
	void deleteProject(Project p) throws ProjectNotFoundException;
	
	Project updateProject(Project p) throws ProjectNotFoundException;
}

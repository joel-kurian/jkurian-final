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

import com.capgemini.entity.Project;
import com.capgemini.exceptions.ProjectExistsException;
import com.capgemini.exceptions.ProjectNotFoundException;
import com.capgemini.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	ProjectService ps;
	
	@GetMapping("/all")
	public List<Project> getAllProjects() {
		return ps.getAllProjects();
	}
	
	@GetMapping("/id/{id}")
	public Project getProjectById(@PathVariable int id) 
			throws ProjectNotFoundException {
		return ps.getProjectById(id);
	}
	
	@PostMapping("/add")
	public Project addProject(@RequestBody Project p) 
			throws ProjectExistsException {
		return ps.addProject(p);
	}
	
	@DeleteMapping("/delete")
	public void deleteProject(@RequestBody Project p) 
			throws ProjectNotFoundException {
		ps.deleteProject(p);
	}
	
	@PutMapping("/update")
	public Project updateProject(@RequestBody Project p) 
			throws ProjectNotFoundException {
		return ps.updateProject(p);
	}
}

package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entity.Project;
import com.capgemini.exceptions.ProjectExistsException;
import com.capgemini.exceptions.ProjectNotFoundException;
import com.capgemini.repo.ProjectRepo;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	ProjectRepo pr;

	@Override
	public List<Project> getAllProjects() {
		return pr.findAll();
	}

	@Override
	public Project getProjectById(int id) throws ProjectNotFoundException {
		return pr.findById(id).orElseThrow(
				() -> new ProjectNotFoundException("Project not found"));
	}

	@Override
	public Project addProject(Project p) throws ProjectExistsException {
		Optional<Project> proj = pr.findById(p.getProjId());
		if (proj.isPresent())
			throw new ProjectExistsException("Project already exists");
		return pr.save(p);
	}

	@Override
	public void deleteProject(Project p) throws ProjectNotFoundException {
		pr.findById(p.getProjId()).orElseThrow(
				() -> new ProjectNotFoundException("Project not found"));
		pr.delete(p);
	}

	@Override
	public Project updateProject(Project p) throws ProjectNotFoundException {
		Project proj = pr.findById(p.getProjId()).orElseThrow(
				() -> new ProjectNotFoundException("Project not found"));
		proj.setEmpList(p.getEmpList());
		proj.setProjDesc(p.getProjDesc());
		proj.setProjName(p.getProjName());
		return pr.save(proj);
	}

}

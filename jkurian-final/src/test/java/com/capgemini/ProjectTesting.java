package com.capgemini;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.entity.Project;
import com.capgemini.exceptions.ProjectExistsException;
import com.capgemini.exceptions.ProjectNotFoundException;
import com.capgemini.repo.ProjectRepo;
import com.capgemini.service.ProjectService;

@SpringBootTest
public class ProjectTesting {

		@Autowired
		ProjectService ps;
		
		@Autowired
		ProjectRepo pr;
		
		@BeforeEach
		public void setup() {
			pr.deleteAll();
		}
		
		@Test
		@DisplayName("Test getAllProjects")
		public void testGetAllProjects() throws ProjectExistsException{
			List<Project> list1 = new ArrayList<Project>();
			
			for (int i = 0; i < 5; i++) {
				Project proj = new Project();
				proj.setProjName(""+i);
				proj.setProjDesc(""+i);
				list1.add(ps.addProject(proj));
			}
			
			List<Project> list2 = ps.getAllProjects();
			for (int i = 0; i < 5; i++) {
				assertThat(list1.get(i)).usingRecursiveComparison().isEqualTo(list2.get(i));
			}
		}

		@Test
		@DisplayName("Test addProject success")
		public void testAddProjectSuccess() throws ProjectExistsException {
			Project proj = ps.addProject(new Project());
			assertThat(proj).isNotNull();
		}

		@Test
		@DisplayName("Test addProject failure")
		public void testAddProjectFailure() {
			Project proj1 = new Project();
			Project proj2 = new Project();
			ProjectExistsException ex = null;
			
			try {
				proj1 = ps.addProject(proj1);
				proj2.setProjId(proj1.getProjId());
				ps.addProject(proj2);
			} catch (ProjectExistsException e) {
				ex = e;
			}
			
			assertThat(ex).isInstanceOf(ProjectExistsException.class);
		}
		
		@Test
		@DisplayName("Test getProjectById success")
		public void testGetProjectByIdSuccess() throws ProjectNotFoundException, ProjectExistsException{
			Project proj = ps.addProject(new Project());
			assertThat(proj).usingRecursiveComparison()
				.isEqualTo(ps.getProjectById(proj.getProjId()));
		}
		
		@Test
		@DisplayName("test getProjectById failure")
		public void testGetProjectByIdFailure() throws ProjectExistsException{
			Project proj = new Project();
			proj = ps.addProject(proj);
			ProjectNotFoundException ex = null;
			try {
				ps.getProjectById(proj.getProjId()+1);
			} catch (ProjectNotFoundException e) {
				ex = e;
			}
			assertThat(ex).isInstanceOf(ProjectNotFoundException.class);
		}
		
		@Test
		@DisplayName("test deleteProject success")
		public void testDeleteProjectSuccess() throws ProjectExistsException, ProjectNotFoundException {
			Project proj = ps.addProject(new Project());
			assertThat(ps.getAllProjects().isEmpty()).isFalse();
			
			ps.deleteProject(proj);
			assertThat(ps.getAllProjects().isEmpty()).isTrue();
		}
		
		@Test
		@DisplayName("test deleteProject failure")
		public void testDeleteProjectFailure() {
			ProjectNotFoundException ex = null;
			try {
				ps.deleteProject(new Project());
			} catch (ProjectNotFoundException e) {
				ex = e;
			}
			assertThat(ex).isInstanceOf(ProjectNotFoundException.class);
		}
		
		@Test
		@DisplayName("test updateProject success")
		public void testUpdateProjectSuccess() throws ProjectExistsException, ProjectNotFoundException {
			Project proj = ps.addProject(new Project());
			int beginId = proj.getProjId();
			String s1 = proj.getProjDesc();
			
			proj.setProjDesc("newDesc");
			proj = ps.updateProject(proj);
			int endId = proj.getProjId();
			String s2 = proj.getProjDesc();
			
			assertThat(beginId).isEqualTo(endId);
			assertThat(s1).isNotEqualTo(s2);
		}
		
		@Test
		@DisplayName("test updateProject failure")
		public void testUpdateProjectFailure() {
			ProjectNotFoundException ex = null;
			try {
				ps.updateProject(new Project());
			} catch (ProjectNotFoundException e) {
				ex = e;
			}
			assertThat(ex).isInstanceOf(ProjectNotFoundException.class);
		}
}

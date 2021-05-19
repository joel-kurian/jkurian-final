package com.capgemini;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.entity.Department;
import com.capgemini.exceptions.DepartmentExistsException;
import com.capgemini.exceptions.DepartmentNotFoundException;
import com.capgemini.repo.DepartmentRepo;
import com.capgemini.service.DepartmentService;

@SpringBootTest
public class DepartmentTesting {
	
	@Autowired
	DepartmentService ds;
	
	@Autowired
	DepartmentRepo dr;
	
	@BeforeEach
	public void setup() {
		dr.deleteAll();
	}
	
	@Test
	@DisplayName("Test getAllDepartments")
	public void testGetAllDepartments() throws DepartmentExistsException{
		List<Department> list1 = new ArrayList<Department>();
		
		for (int i = 0; i < 5; i++) {
			Department dep = new Department();
			dep.setDepName(""+i);
			dep.setDepDesc(""+i);
			list1.add(ds.addDepartment(dep));
		}
		
		List<Department> list2 = ds.getAllDepartments();
		for (int i = 0; i < 5; i++) {
			assertThat(list1.get(i)).usingRecursiveComparison().isEqualTo(list2.get(i));
		}
	}

	@Test
	@DisplayName("Test addDepartment success")
	public void testAddDepartmentSuccess() throws DepartmentExistsException {
		
		Department dep = ds.addDepartment(new Department());
		
		assertThat(dep).isNotNull();
	}

	@Test
	@DisplayName("Test addDepartment failure")
	public void testAddDepartmentFailure() {
		Department dep1 = new Department();
		Department dep2 = new Department();
		DepartmentExistsException ex = null;
		
		try {
			dep1 = ds.addDepartment(dep1);
			dep2.setDepId(dep1.getDepId());
			ds.addDepartment(dep2);
		} catch (DepartmentExistsException e) {
			ex = e;
		}
		
		assertThat(ex).isInstanceOf(DepartmentExistsException.class);
	}
	
	@Test
	@DisplayName("Test getDepartmentById success")
	public void testGetDepartmentByIdSuccess() throws DepartmentExistsException, DepartmentNotFoundException {
		Department dep = ds.addDepartment(new Department());
		assertThat(dep).usingRecursiveComparison()
			.isEqualTo(ds.getDepartmentById(dep.getDepId()));
	}
	
	@Test
	@DisplayName("test getDepartmentById failure")
	public void testGetDepartmentByIdFailure() throws DepartmentExistsException {
		Department dep = new Department();
		dep = ds.addDepartment(dep);
		DepartmentNotFoundException ex = null;
		try {
			ds.getDepartmentById(dep.getDepId()+1);
		} catch (DepartmentNotFoundException e) {
			ex = e;
		}
		assertThat(ex).isInstanceOf(DepartmentNotFoundException.class);
	}
	
	@Test
	@DisplayName("test deleteDepartment success")
	public void testDeleteDepartmentSuccess() throws DepartmentExistsException, DepartmentNotFoundException {
		Department dep = ds.addDepartment(new Department());
		assertThat(ds.getAllDepartments().isEmpty()).isFalse();
		
		ds.deleteDepartment(dep);
		assertThat(ds.getAllDepartments().isEmpty()).isTrue();
	}
	
	@Test
	@DisplayName("test deleteDepartment failure")
	public void testDeleteDepartmentFailure() {
		DepartmentNotFoundException ex = null;
		try {
			ds.deleteDepartment(new Department());
		} catch (DepartmentNotFoundException e) {
			ex = e;
		}
		assertThat(ex).isInstanceOf(DepartmentNotFoundException.class);
	}
	
	@Test
	@DisplayName("test updateDepartment success")
	public void testUpdateDepartmentSuccess() throws DepartmentNotFoundException, DepartmentExistsException {
		Department dep = ds.addDepartment(new Department());
		int beginId = dep.getDepId();
		String s1 = dep.getDepDesc();
		
		dep.setDepDesc("newDesc");
		dep = ds.updateDepartment(dep);
		int endId = dep.getDepId();
		String s2 = dep.getDepDesc();
		
		assertThat(beginId).isEqualTo(endId);
		assertThat(s1).isNotEqualTo(s2);
	}
	
	@Test
	@DisplayName("test updateDepartment failure")
	public void testUpdateDepartmentFailure() {
		DepartmentNotFoundException ex = null;
		try {
			ds.updateDepartment(new Department());
		} catch (DepartmentNotFoundException e) {
			ex = e;
		}
		assertThat(ex).isInstanceOf(DepartmentNotFoundException.class);
	}
}

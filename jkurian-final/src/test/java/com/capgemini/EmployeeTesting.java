package com.capgemini;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
import com.capgemini.service.EmployeeService;

@SpringBootTest
public class EmployeeTesting {
	
	@Autowired
	EmployeeService es;
	
	@Autowired
	EmployeeRepo er;
	
	@Autowired
	DepartmentRepo dr;
	
	@Autowired
	AddressRepo ar;
	
	@Autowired
	ProjectRepo pr;
	
	@BeforeEach
	@AfterEach
	public void setup() {
		er.deleteAll();
		dr.deleteAll();
		ar.deleteAll();
		pr.deleteAll();
	}
	
	@Test
	@DisplayName("Test getAllEmployees")
	public void testGetAllEmployees() throws EmployeeExistsException, DepartmentNotFoundException, ProjectNotFoundException, AddressNotFoundException{
		List<Employee> list1 = new ArrayList<Employee>();
		
		for (int i = 0; i < 5; i++) {
			Employee emp = new Employee();
			emp.setName(""+i);
			emp.setTitle(""+i);
			list1.add(es.addEmployee(emp));
		}
		
		List<Employee> list2 = es.getAllEmployees();
		for (int i = 0; i < 5; i++) {
			assertThat(list1.get(i)).usingRecursiveComparison().isEqualTo(list2.get(i));
		}
	}

	@Test
	@DisplayName("Test addEmployee success")
	public void testAddEmployeeSuccess() throws EmployeeExistsException, DepartmentNotFoundException, ProjectNotFoundException, AddressNotFoundException {
		Employee emp = es.addEmployee(new Employee());
		assertThat(emp).isNotNull();
	}

	@Test
	@DisplayName("Test addEmployee failure")
	public void testAddEmployeeFailure() throws DepartmentNotFoundException, ProjectNotFoundException, AddressNotFoundException {
		Employee emp1 = new Employee();
		Employee emp2 = new Employee();
		EmployeeExistsException ex = null;
		
		try {
			emp1 = es.addEmployee(emp1);
			emp2.setEmpId(emp1.getEmpId());
			es.addEmployee(emp2);
		} catch (EmployeeExistsException e) {
			ex = e;
		}
		
		assertThat(ex).isInstanceOf(EmployeeExistsException.class);
	}
	
	@Test
	@DisplayName("Test getEmployeeById success")
	public void testGetEmployeeByIdSuccess() throws EmployeeNotFoundException, EmployeeExistsException, DepartmentNotFoundException, ProjectNotFoundException, AddressNotFoundException{
		Employee emp = es.addEmployee(new Employee());
		assertThat(emp).usingRecursiveComparison()
			.isEqualTo(es.getEmployeeById(emp.getEmpId()));
	}
	
	@Test
	@DisplayName("test getEmployeeById failure")
	public void testGetEmployeeByIdFailure() throws EmployeeExistsException, DepartmentNotFoundException, ProjectNotFoundException, AddressNotFoundException{
		Employee emp = new Employee();
		emp = es.addEmployee(emp);
		EmployeeNotFoundException ex = null;
		try {
			es.getEmployeeById(emp.getEmpId()+1);
		} catch (EmployeeNotFoundException e) {
			ex = e;
		}
		assertThat(ex).isInstanceOf(EmployeeNotFoundException.class);
	}
	
	@Test
	@DisplayName("test deleteEmployee success")
	public void testDeleteEmployeeSuccess() throws EmployeeExistsException, EmployeeNotFoundException, DepartmentNotFoundException, ProjectNotFoundException, AddressNotFoundException {
		Employee emp = es.addEmployee(new Employee());
		assertThat(es.getAllEmployees().isEmpty()).isFalse();
		
		es.deleteEmployee(emp);
		assertThat(es.getAllEmployees().isEmpty()).isTrue();
	}
	
	@Test
	@DisplayName("test deleteEmployee failure")
	public void testDeleteEmployeeFailure() {
		EmployeeNotFoundException ex = null;
		try {
			es.deleteEmployee(new Employee());
		} catch (EmployeeNotFoundException e) {
			ex = e;
		}
		assertThat(ex).isInstanceOf(EmployeeNotFoundException.class);
	}
	
	@Test
	@DisplayName("test updateEmployee success")
	public void testUpdateEmployeeSuccess() throws EmployeeExistsException, EmployeeNotFoundException, DepartmentNotFoundException, ProjectNotFoundException, AddressNotFoundException {
		Employee emp = es.addEmployee(new Employee());
		int beginId = emp.getEmpId();
		String s1 = emp.getName();
		
		emp.setName("newName");
		emp = es.updateEmployeeDetails(emp);
		int endId = emp.getEmpId();
		String s2 = emp.getName();
		
		assertThat(beginId).isEqualTo(endId);
		assertThat(s1).isNotEqualTo(s2);
	}
	
	@Test
	@DisplayName("test updateEmployee failure")
	public void testUpdateEmployeeFailure() {
		EmployeeNotFoundException ex = null;
		try {
			es.updateEmployeeDetails(new Employee());
		} catch (EmployeeNotFoundException e) {
			ex = e;
		}
		assertThat(ex).isInstanceOf(EmployeeNotFoundException.class);
	}
	
	@Test
	@DisplayName("test updateEmployeeProject success")
	public void testUpdateEmployeeProjectSuccess() throws EmployeeExistsException, EmployeeNotFoundException, ProjectNotFoundException, DepartmentNotFoundException, AddressNotFoundException {
		Project p1 = pr.save(new Project());
		Employee emp = es.addEmployee(new Employee());
		
		assertThat(emp.getProj()).isNull();
		
		emp.setProj(p1);
		emp = es.updateEmployeeProject(emp);
		assertThat(emp.getProj().getProjId()).isEqualTo(p1.getProjId());
	}
	
	@Test
	@DisplayName("test updateEmployeeProject failure")
	public void testUpdateEmployeeProjectFailure() throws EmployeeExistsException, DepartmentNotFoundException, ProjectNotFoundException, AddressNotFoundException {
		Employee emp = es.addEmployee(new Employee());
		Project proj = new Project();
		proj.setProjId(-1);
		emp.setProj(proj);
		
		ProjectNotFoundException ex = null;
		try {
			emp = es.updateEmployeeProject(emp);
		} catch (EmployeeNotFoundException | ProjectNotFoundException e) {
			ex = (ProjectNotFoundException) e;
		}
		assertThat(ex).isInstanceOf(ProjectNotFoundException.class);
	}
	
	@Test
	@DisplayName("test updateEmployeeDepartment success")
	public void testUpdateEmployeeDepartmentSuccess() throws EmployeeNotFoundException, DepartmentNotFoundException, EmployeeExistsException, ProjectNotFoundException, AddressNotFoundException {
		Employee emp = es.addEmployee(new Employee());
		assertThat(emp.getDept()).isNull();
		
		Department dep = new Department();
		dep = dr.save(dep);
		
		emp.setDept(dep);
		emp = es.updateEmployeeDepartment(emp);
		assertThat(emp.getDept().getDepId()).isEqualTo(dep.getDepId());
	}
	
	@Test
	@DisplayName("test updateEmployeeDepartment failure")
	public void testUpdateEmployeeDepartmentFailure() throws EmployeeExistsException, DepartmentNotFoundException, ProjectNotFoundException, AddressNotFoundException {
		Employee emp = es.addEmployee(new Employee());
		Department dep = new Department();
		dep.setDepId(-1);
		emp.setDept(dep);
		
		DepartmentNotFoundException ex = null;
		try {
			emp = es.updateEmployeeDepartment(emp);
		} catch (EmployeeNotFoundException | DepartmentNotFoundException e) {
			ex = (DepartmentNotFoundException) e;
		}
		
		assertThat(ex).isInstanceOf(DepartmentNotFoundException.class);
	}
	
	@Test
	@DisplayName("test updateEmployeeAddress success")
	public void testUpdateEmployeeAddressSuccess() throws EmployeeNotFoundException, AddressNotFoundException, EmployeeExistsException, DepartmentNotFoundException, ProjectNotFoundException {
		Employee emp = es.addEmployee(new Employee());
		Address adr = ar.save(new Address());
		
		emp.setAddr(adr);
		emp = es.updateEmployeeAddress(emp);
		assertThat(emp.getAddr().getPin()).isEqualTo(adr.getPin());
	}
	
	@Test
	@DisplayName("test updateEmployeeAddress failure")
	public void testUpdateEmployeeAddressFailure() throws EmployeeExistsException, DepartmentNotFoundException, ProjectNotFoundException, AddressNotFoundException {
		Employee emp = es.addEmployee(new Employee());
		Address adr = new Address();
		adr.setPin(-1);
		emp.setAddr(adr);
		AddressNotFoundException ex = null;
		try {
			emp = es.updateEmployeeAddress(emp);
		} catch (EmployeeNotFoundException | AddressNotFoundException e) {
			ex = (AddressNotFoundException) e;
		}
		assertThat(ex).isInstanceOf(AddressNotFoundException.class);
	}
}

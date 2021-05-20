package com.capgemini.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int depId;
	
	@Column(name = "Dep_Name")
	private String depName;
	
	@Column(name = "Dep_Desc")
	private String depDesc;
	
	@OneToMany(targetEntity=Employee.class, orphanRemoval = true)
	@JoinTable(name = "department_employee",
		joinColumns = @JoinColumn(name = "depId"),
		inverseJoinColumns = @JoinColumn(name = "empId"))
	private List<Employee> empList = new ArrayList<Employee>();
	
	public int getDepId() {
		return depId;
	}
	public void setDepId(int depId) {
		this.depId = depId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDepDesc() {
		return depDesc;
	}
	public void setDepDesc(String depDesc) {
		this.depDesc = depDesc;
	}
	public List<Employee> getEmpList() {
		return empList;
	}
}

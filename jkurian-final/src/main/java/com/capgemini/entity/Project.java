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
@Table(name = "project")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int projId;
	
	@Column(name = "Proj_Name")
	private String projName;
	
	@Column(name = "Proj_Desc")
	private String projDesc;
	
	@OneToMany(targetEntity = Employee.class, orphanRemoval = true)
	@JoinTable(name = "project_employee",
			joinColumns = @JoinColumn(name = "projId"),
			inverseJoinColumns = @JoinColumn(name = "empId"))
	private List<Employee> empList = new ArrayList<Employee>();

	public int getProjId() {
		return projId;
	}

	public void setProjId(int projId) {
		this.projId = projId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjDesc() {
		return projDesc;
	}

	public void setProjDesc(String projDesc) {
		this.projDesc = projDesc;
	}

	public List<Employee> getEmpList() {
		return empList;
	}
}

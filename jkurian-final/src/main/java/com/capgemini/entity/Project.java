package com.capgemini.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "project")
public class Project {
	
	@Id
	@Column(name = "projId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int projId;
	
	@Column(name = "Proj_Name")
	private String projName;
	
	@Column(name = "Proj_Desc")
	private String projDesc;
	
	@OneToMany(mappedBy = "proj", cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<Employee> empList;

	public Project() {
		empList = new HashSet<Employee>();
	}

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

	public Set<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(Set<Employee> empList) {
		this.empList = empList;
	}
}

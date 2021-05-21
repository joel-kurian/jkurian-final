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
@Table(name = "department")
public class Department {
	
	@Id
	@Column(name = "dep_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int dep_id;
	
	@Column(name = "Dep_Name")
	private String depName;
	
	@Column(name = "Dep_Desc")
	private String depDesc;
	
	@OneToMany(mappedBy = "dept", cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<Employee> empList = new HashSet<Employee>();
	
	public int getDepId() {
		return dep_id;
	}
	public void setDepId(int depId) {
		this.dep_id = depId;
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
	public Set<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(Set<Employee> empList) {
		this.empList = empList;
	}
}

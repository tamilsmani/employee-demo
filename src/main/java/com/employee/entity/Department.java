package com.employee.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "DEPARTMENT")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(Include.NON_NULL)
public class Department extends AbstractAuditable<String> {
	
	private static final long serialVersionUID = -604368296015950111L;
	
	@Column(name = "NAME", nullable = false)
	@NotNull(message ="Dept name cann't be empty")
	private String name;

	@Column(name = "CODE", nullable = false)
	@NotNull(message ="Dept Code cann't be empty")
	private String code;
	
	@OneToMany(mappedBy="department", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Employee> employees;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
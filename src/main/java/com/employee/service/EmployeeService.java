package com.employee.service;

import java.util.List;

import com.employee.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> loadAll();
	public void save(Employee emp);
	
}
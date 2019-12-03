package com.employee.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.controller.ResourceNotFoundException;
import com.employee.entity.Department;
import com.employee.entity.Employee;
import com.employee.repository.DepartmentRepository;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	public EmployeeRepository employeeRepository;
	
	@Autowired
	public DepartmentRepository departmentRepository;
	
	public List<Employee> loadAll() {
		LOGGER.info("Loading All employees");
		return employeeRepository.findAllByOrderByFirstName();
	}
	
	@Transactional
	public void save(Employee emp) {
		Department department = departmentRepository.findByCode(emp.getDepartment().getCode()).orElseThrow(
				() -> new ResourceNotFoundException("Department", "Code", emp.getDepartment().getCode()));
		emp.setDepartment(department);
		LOGGER.info("creating new employees [{}]", emp.getFirstName());
		employeeRepository.save(emp);
		LOGGER.info("created new employees  [{}]", emp.getFirstName());
	}
	
}
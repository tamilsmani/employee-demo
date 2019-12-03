package com.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/create")
	@ApiOperation(value = "Create new employee")
	@ApiResponses(value = {  
			@ApiResponse(code = 200, message = "Successfully retrieved list", response = String.class),
			@ApiResponse(code = 401, message = "You are not authorized this resource"),
	    })
	public Employee createEmployee(@Valid @RequestBody Employee employee/*, UriComponentsBuilder builder*/) {
		LOGGER.info("Creating employee");
		employeeService.save(employee);
		return employee;
	}

	@GetMapping("/loadAll")
	@ApiOperation(value = "View all employees")
	@ApiResponses(value = {  
			@ApiResponse(code = 200, message = "Successfully retrieved list", responseContainer = "List", response = Employee.class),
			@ApiResponse(code = 401, message = "You are not authorized this resource"),
	    })
	public List<Employee> loadAllEmployee() {
		LOGGER.info("Loading employees");
		return employeeService.loadAll();
	}
}
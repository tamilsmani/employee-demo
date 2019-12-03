package com.example;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;

import com.employee.controller.EmployeeController;
import com.employee.controller.WebConfig;
import com.employee.entity.Department;
import com.employee.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebMvcTest(EmployeeController.class)
@EnableTransactionManagement
public class EmployeeControllerTest {

	@Autowired
    private WebApplicationContext webAppContext;
	
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext)
        		.apply(springSecurity())
        		.build();
    }
    
    @Test
    @Transactional
	public void testCreateEmployee() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
					.post("/api/v1/employee/create")
					.header("Authorization","Basic YWRtaW46YWRtaW4=")
					.content(asJsonString(createEmployee()))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Tamil"));
	}
    
    @Test
	public void testGetAllEmployee() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
					.get("/api/v1/employee/loadAll")
					.header("Authorization","Basic YWRtaW46YWRtaW4=")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[*].firstName").exists());
	}
	
    @Test
   	public void testUnauthorized() throws Exception {
   		mockMvc.perform(MockMvcRequestBuilders
   					.get("/api/v1/employee/loadAll")
   					.header("Authorization","Basic YWRtaW46YWRtaW41=")
   					.accept(MediaType.APPLICATION_JSON))
   				.andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
   		
   	}
    
	private Employee createEmployee() {
		Employee emp = new Employee();
		emp.setFirstName("Tamil");
		emp.setLastName("Selvan");
		emp.setDateOfBirth(new Date());
		emp.setGender(Employee.Gender.MALE);
		
		Department dept = new Department();
		dept.setCode("DPHY");
		dept.setName("Physics");
		emp.setDepartment(dept);
		
		return emp;
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}

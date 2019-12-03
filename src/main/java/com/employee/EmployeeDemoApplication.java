package com.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.employee.security.SpringSecurityAuditorAware;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@ImportResource( {
		"classpath:META-INF/employee-jpa-context.xml"
})
public class EmployeeDemoApplication {	

	@Bean
    public AuditorAware<String> auditorAware() {
        return new SpringSecurityAuditorAware();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeDemoApplication.class, args);
	}

}

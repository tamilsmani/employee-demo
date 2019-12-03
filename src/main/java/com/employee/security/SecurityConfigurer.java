package com.employee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	@Autowired
	private RestAuthenticationEntryPoint authenticationEntryPoint;

	@Value("${employee.demo.username}")
	private String username;

	@Value("${employee.demo.password}")
	private String password;

	@Bean
	public UserDetailsService userDetailsService() {

		User.UserBuilder users = User.withDefaultPasswordEncoder();
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(users.username(username).password(password).roles("USER").build());
		return manager;

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
						"/configuration/security", "/swagger-ui.html**", "/webjars/**").
				permitAll().
				antMatchers("/api/*").hasRole("USER").
				and().csrf().disable().httpBasic().
				authenticationEntryPoint(authenticationEntryPoint).and().logout();
	}
}
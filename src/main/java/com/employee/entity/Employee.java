package com.employee.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/** 
 * 
 * 
@Query(name="customFindAll")
List<WacInfo> getAll();

@NamedNativeQuery(name = "customFindAll", query = "select * from EMPLOYEE ", resultSetMapping="customResultSetMapping")
@SqlResultSetMapping(
			 name = "customResultSetMapping",
			 entities = {
					 // We have to load all the fields. For the custom fields use [ ConstructorResult & columns ] 
				        @EntityResult(
				           entityClass = Employee.class
				        )
				    }
			 
//			classes={
//					@ConstructorResult(targetClass = EmployeeVO.class,
//					columns = {
//							@ColumnResult(name="id", 			type=String.class),
//							@ColumnResult(name="countryCode", 	type=String.class)
//					})
//		    }
			 
)

**/


@Entity
@Table(name = "EMPLOYEE")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(Include.NON_NULL)
public class Employee extends AbstractAuditable<String> {

	private static final long serialVersionUID = 5202659008148271992L;

	@Column(name = "FIRST_NAME", nullable = false)
	@NotNull(message ="Firstname cann't be empty")
	private String firstName;
	
	
	@Column(name = "LAST_NAME", nullable = false)
	@NotNull(message ="Lastname cann't be empty")
	private String lastName;
	
	@Column(name = "GENDER", nullable = false)
	@NotNull(message ="Gener cann't be empty")
	private Gender gender;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "DATE_OF_BIRTH", nullable = false)
	@NotNull(message ="DOB cann't be empty")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="DEPARTMENT_ID")
	@NotNull(message ="Deparment cann't be empty")
	private Department department;

	public enum Gender {
		MALE, FEMALE
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
}

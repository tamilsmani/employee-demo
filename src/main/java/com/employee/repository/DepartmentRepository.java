package com.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

	public Optional<Department> findByCode(String code);
}
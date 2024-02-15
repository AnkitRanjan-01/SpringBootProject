package com.example.employeeapi.EmployeeRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employeeapi.module.Employee;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

} 

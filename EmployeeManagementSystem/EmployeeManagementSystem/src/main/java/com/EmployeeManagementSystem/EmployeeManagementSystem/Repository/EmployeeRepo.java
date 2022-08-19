package com.EmployeeManagementSystem.EmployeeManagementSystem.Repository;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employees, Integer> {
    Optional<Employees> findByEmail(String email);
}
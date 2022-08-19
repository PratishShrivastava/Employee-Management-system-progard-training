package com.EmployeeManagementSystem.EmployeeManagementSystem.Repository;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Organizations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepo extends JpaRepository<Organizations, Integer> {
    Optional<Organizations> findById(int organizationRegistrationNumber);
}

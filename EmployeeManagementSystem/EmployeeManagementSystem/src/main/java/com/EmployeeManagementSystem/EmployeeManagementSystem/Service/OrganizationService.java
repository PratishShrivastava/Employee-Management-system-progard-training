package com.EmployeeManagementSystem.EmployeeManagementSystem.Service;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Organizations;

import java.util.List;

public interface OrganizationService {
    Organizations saveOrganization(Organizations organizations);
    List<Organizations> getAllOrganization();
    Organizations getOrganizationById(int organizationRegistrationNumber);
    Organizations updateOrganization(Organizations organizations, int organizationRegistrationNumber);
    boolean deleteOrganizations(int organizationRegistrationNumber);
}
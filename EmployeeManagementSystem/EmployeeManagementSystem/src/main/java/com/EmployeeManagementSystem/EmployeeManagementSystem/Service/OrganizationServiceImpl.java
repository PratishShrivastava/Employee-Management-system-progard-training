package com.EmployeeManagementSystem.EmployeeManagementSystem.Service;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Organizations;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Repository.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrganizationServiceImpl implements OrganizationService{
    @Autowired
    private OrganizationRepo organizationRepo;

    public OrganizationServiceImpl(OrganizationRepo organizationRepo){
        this.organizationRepo = organizationRepo;
    }

    @Override
    public Organizations saveOrganization(Organizations organizations)
    {
        return organizationRepo.save(organizations);
    }
    @Override
    public List<Organizations> getAllOrganization()
    {
        return organizationRepo.findAll();
    }
    @Override
    public Organizations getOrganizationById(int organizationRegistrationNumber)
    {
        return organizationRepo.findById(organizationRegistrationNumber).orElseThrow();
    }
    @Override
    public Organizations updateOrganization(Organizations organizations,int organizationRegistrationNumber)
    {
        Organizations user = organizationRepo.findById(organizationRegistrationNumber).orElseThrow();
        user.setOrganizationName(organizations.getOrganizationName());
        user.setOrganizationAddress(organizations.getOrganizationAddress());
        user.setOrganizationPhoneNumber(organizations.getOrganizationPhoneNumber());
        user.setOrganizationEmail(organizations.getOrganizationEmail());
        organizationRepo.save(user);
        return user;
    }
    @Override
    public boolean deleteOrganizations(int organizationRegistrationNumber)
    {
        try {
            organizationRepo.findById(organizationRegistrationNumber).orElseThrow();
            organizationRepo.deleteById(organizationRegistrationNumber);
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }
}



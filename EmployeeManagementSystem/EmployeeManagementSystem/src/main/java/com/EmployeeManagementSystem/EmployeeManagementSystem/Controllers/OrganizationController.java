package com.EmployeeManagementSystem.EmployeeManagementSystem.Controllers;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Assets;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Employees;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Organizations;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService){
        this.organizationService = organizationService;
    }

//    @PostMapping
//    public ResponseEntity<Organizations> saveOrganization(@RequestBody Organizations organizations)
//    {
//        return new ResponseEntity<Organizations>(organizationService.saveOrganization(organizations), HttpStatus.CREATED);
//    }
@PostMapping
public ResponseEntity<String> saveOrganization(@RequestBody Organizations organizations)
{
    if (organizations.getOrganizationName().length()>0&&String.valueOf(organizations.getOrganizationPhoneNumber()).length()>0&&String.valueOf(organizations.getOrganizationAddress()).length()>0&&organizations.getOrganizationEmail().length()>0) {
        Organizations org = organizationService.saveOrganization(organizations);
        if (org == null) {
            return new ResponseEntity<>("Organisation Already Exist", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<String>("Organisation added Successfully", HttpStatus.CREATED);
        }
    }else {
        return new ResponseEntity<>("Invalid Data",HttpStatus.BAD_REQUEST);
    }

}


    @GetMapping
    public ResponseEntity<List<Organizations>> getAllOrganizations()
    {
        List<Organizations> organizations = organizationService.getAllOrganization();
        if(organizations.size() > 0){
            return new ResponseEntity<>(organizations, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/{organizationRegistrationNumber}")
    public ResponseEntity<Organizations> getOrganizationsById(@PathVariable("organizationRegistrationNumber")int organizationRegistrationNumber)
    {
        try{
            return new ResponseEntity<Organizations>(organizationService.getOrganizationById(organizationRegistrationNumber), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Organizations>(HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("{organizationRegistrationNumber}")
    public ResponseEntity<String> updateOrganizations(@PathVariable("organizationRegistrationNumber")int organizationRegistrationNumber,@RequestBody Organizations organizations)
    {
        try {
            organizationService.updateOrganization(organizations, organizationRegistrationNumber);
            return new ResponseEntity<String>("Updated Organization", HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<String>("Organisation's details not found", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{organizationRegistrationNumber}")
    public ResponseEntity<String> deleteBooks(@PathVariable("organizationRegistrationNumber")int organizationRegistrationNumber)
    {
            if(organizationService.deleteOrganizations(organizationRegistrationNumber))
                return new ResponseEntity<String>("Organizations deleted successfully",HttpStatus.OK);
            else
                return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);

    }
}


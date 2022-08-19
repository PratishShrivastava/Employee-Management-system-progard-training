package com.EmployeeManagementSystem.EmployeeManagementSystem.Security;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Employees;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDetails implements UserDetailsService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employees detail = this.employeeRepo.findByEmail(username).orElseThrow();
        return detail;
    }
}
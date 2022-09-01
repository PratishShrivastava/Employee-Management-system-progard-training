package com.EmployeeManagementSystem.EmployeeManagementSystem.Service;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Employees;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.employeeRepo = employeeRepo;
    }

    @Override
    public boolean saveEmployee(Employees employees) {
        List<Employees> emp = employeeRepo.findAll();
        boolean isFound = false;
        for (Employees e : emp) {
            if (e.getEmail().equals(employees.getEmail())) {
                isFound = true;
                break;
            }
        }

        if (isFound) {
            return false;
        } else {
            String encodepass = this.passwordEncoder.encode(employees.getPassword());
            employees.setPassword(encodepass);
            employeeRepo.save(employees);
            return true;
        }

    }

    @Override
    public List<Employees> getAllEmployees() {
        List<Employees> emp = employeeRepo.findAll();
        for(Employees e:emp) {
            e.setPassword("THIS INFORMATION CANNOT BE LEAKED");
        }
        return emp;
    }


    @Override
    public Employees getEmployeesById(int employeeId)
    {
        Employees employees =  employeeRepo.findById(employeeId).orElseThrow();
        employees.setPassword("hidden");
        return employees;
    }
    @Override
    public Employees updateEmployees(Employees employees,int employeeId)
    {
        Employees user = employeeRepo.findById(employeeId).orElseThrow();
        user.setEmployeeName(employees.getEmployeeName());
        user.setEmployeeAddress(employees.getEmployeeAddress());
        user.setEmployeePhoneNumber(employees.getEmployeePhoneNumber());
        user.setEmail(employees.getEmail());
        user.setEmployeeSalary(employees.getEmployeeSalary());
        employeeRepo.save(user);
        return user;
    }
    @Override
    public boolean deleteEmployees(int employeeId)
    {
        try {
            employeeRepo.findById(employeeId).orElseThrow();
            employeeRepo.deleteById(employeeId);
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }
}


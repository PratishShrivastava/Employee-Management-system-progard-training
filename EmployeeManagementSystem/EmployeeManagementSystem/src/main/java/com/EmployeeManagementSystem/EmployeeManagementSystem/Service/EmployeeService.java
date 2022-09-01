package com.EmployeeManagementSystem.EmployeeManagementSystem.Service;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Employees;

import java.util.List;

public interface EmployeeService {
    public boolean saveEmployee(Employees employees);
    List<Employees> getAllEmployees();
    Employees getEmployeesById(int employeeId);
    Employees updateEmployees(Employees employees, int employeeId);
    boolean deleteEmployees(int employeeId);
}

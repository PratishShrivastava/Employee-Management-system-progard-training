package com.EmployeeManagementSystem.EmployeeManagementSystem.Controllers;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Employees;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<String> saveEmployee(@RequestBody @Valid Employees employees) {
        if(employeeService.saveEmployee(employees))
            return new ResponseEntity<String>("Created", HttpStatus.CREATED);
        else
            return new ResponseEntity<String>("email Exists", HttpStatus.BAD_REQUEST);
    }
    @GetMapping
    public List<Employees> getAllEmployees()
    {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/login/{employeeId}")
    public ResponseEntity<Employees> getEmployeesById(@PathVariable("employeeId")int employeeId)
    {
        return new ResponseEntity<Employees>(employeeService.getEmployeesById(employeeId), HttpStatus.OK);
    }
    @PutMapping("{employeeId}")
    public ResponseEntity<String> updateEmployees(@PathVariable("employeeId")int employeeId,@RequestBody @Valid Employees employees)
    {
        employeeService.updateEmployees(employees, employeeId);
        return new ResponseEntity<String>("Updated Employee", HttpStatus.OK);
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<String> deleteAssets(@PathVariable("employeeId")int employeeId)
    {
        if(employeeService.deleteEmployees(employeeId))
            return new ResponseEntity<String>("Employees deleted successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
    }
}

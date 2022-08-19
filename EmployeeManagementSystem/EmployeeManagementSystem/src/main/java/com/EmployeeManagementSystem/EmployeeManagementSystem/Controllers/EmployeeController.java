package com.EmployeeManagementSystem.EmployeeManagementSystem.Controllers;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Models.Employees;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> saveEmployee(@RequestBody Employees employees)
    {
        if(employees.getPassword().length()>0&&employees.getPassword().length()>0&&employees.getEmployeeName().length()>0&&employees.getPassword().length()>0&&employees.getEmployeeAddress().length()>0&&employees.getEmail().length()>0&&String.valueOf(employees.getEmployeePhoneNumber()).length()>0)
        {
            if(String.valueOf(employees.getEmployeePhoneNumber()).length()==10){
                if(!employees.getEmployeeSalary().contains("-")){
                    if(employees.getPassword().length()<=8){
                        if(employees.getEmail().contains("@")&&employees.getEmail().contains(".com")) {
                            Employees emp = employeeService.saveEmployee(employees);
                            if (emp == null) {
                                return new ResponseEntity<>("Employee Already Exist", HttpStatus.BAD_REQUEST);
                            } else {
                                return new ResponseEntity<String>("Employee added Successfully", HttpStatus.CREATED);
                            }
                        }else {
                            return new ResponseEntity<>("Invalid email.",HttpStatus.BAD_REQUEST);
                        }
                    }else {
                        return new ResponseEntity<>("Invalid password should be greater than 8.",HttpStatus.BAD_REQUEST);
                    }
                }else {
                    return new ResponseEntity<>("Invalid salary can not be negative.",HttpStatus.BAD_REQUEST);
                }
            }else {
                return new ResponseEntity<>("Invalid phone number should be 10.",HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<>("Invalid Data",HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<String> updateEmployees(@PathVariable("employeeId")int employeeId,@RequestBody Employees employees)
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

package com.test.apiTest.controller;

import com.test.apiTest.model.CategoryCount;
import com.test.apiTest.model.Employee;
import com.test.apiTest.model.EmployeeRole;
import com.test.apiTest.response.CategoryCountResponse;
import com.test.apiTest.service.EmployeeRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class EmployeeRoleController {
    @Autowired
    private EmployeeRoleService employeeRoleService;



    @GetMapping("/all-employee-details")
    public ResponseEntity<?> getAllemployeeDetails(){
        List<Employee> response = this.employeeRoleService.getAllEmployeeRole();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Custom-Header-Value");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}

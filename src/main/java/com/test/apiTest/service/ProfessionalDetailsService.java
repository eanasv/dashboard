package com.test.apiTest.service;

import com.test.apiTest.model.Employee;
import com.test.apiTest.model.MainCat;
import com.test.apiTest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProfessionalDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public  List<Employee> getAllEmployeeProfessionalDetails(){
        //return employeeRepository.getAllEmployeeProfDetails();
        return employeeRepository.findAll();


    }


}

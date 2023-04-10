package com.test.apiTest.service;

import com.test.apiTest.model.Employee;
import com.test.apiTest.model.Skill;
import com.test.apiTest.repository.EmployeeRepository;
import com.test.apiTest.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public void addSkillToEmployee(Long employeeId, Skill skill) {
        Employee employee = employeeRepository.findById(Math.toIntExact(employeeId))
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id : " + employeeId));

        skill.setEmployee(employee);
        skillRepository.save(skill);
    }
}

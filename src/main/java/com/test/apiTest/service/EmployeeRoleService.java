package com.test.apiTest.service;

import com.test.apiTest.model.Employee;
import com.test.apiTest.model.EmployeeRole;
import com.test.apiTest.repository.EmployeeRepository;
import com.test.apiTest.repository.EmployeeRoleRepository;
import com.test.apiTest.model.CategoryCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeRoleService {
    @Autowired
    private EmployeeRoleRepository employeeRoleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployeeRole() {
        return employeeRepository.findAll();
    }

    public CategoryCount getNumberOfCategories() {
        List<Employee> employeeRoles = employeeRepository.findAll();

        int ictCount = 0;
        int cyberSecurityCount = 0;
        int dataAnalyticsCount = 0;

        for (Employee employeeRole : employeeRoles) {
            switch (employeeRole.getCategory().toLowerCase()) {
                case "ict":
                    ictCount++;
                    break;
                case "cyber security":
                    cyberSecurityCount++;
                    break;
                case "data analytics":
                    dataAnalyticsCount++;
                    break;
                default:
                    break;
            }
        }

        CategoryCount categoryCount = new CategoryCount();
        categoryCount.setIctCount(ictCount);
        categoryCount.setCyberSecurityCount(cyberSecurityCount);
        categoryCount.setDataAnalyticsCount(dataAnalyticsCount);
        categoryCount.setTotalCount(employeeRoles.size());

        return categoryCount;
    }

}

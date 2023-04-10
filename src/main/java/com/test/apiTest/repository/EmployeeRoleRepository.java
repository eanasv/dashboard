package com.test.apiTest.repository;

import com.test.apiTest.model.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Integer> {
    @Query(value = "SELECT category, count(*) FROM employee_role GROUP BY category", nativeQuery = true)
    List<Object[]> countByCategory();
}

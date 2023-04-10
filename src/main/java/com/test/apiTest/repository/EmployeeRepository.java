package com.test.apiTest.repository;

import com.test.apiTest.model.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAll();

    Employee findByEmployeeNumber(Integer employeeNumber);

    //@Query(value ="SELECT e.employee_number, e.entity, e.job, e.category, e.sub_category, e.role,(SELECT JSON_ARRAYAGG(JSON_OBJECT('name', s.name,'score', s.score,'achievedStatus', s.evaluation_result))FROM MyStore.skill s WHERE s.employee_number = e.employee_number) AS technical_skills FROM MyStore.employee e LEFT JOIN MyStore.skill s ON s.employee_number = e.employee_number GROUP BY e.employee_number, e.entity, e.job, e.category, e.sub_category, e.role", nativeQuery = true)
    @Query(value = "SELECT e.employee_number, e.entity, e.job, e.category, e.sub_category, e.role,(SELECT JSON_ARRAYAGG(JSON_OBJECT('name', s.name,'score', s.score,'achievedStatus', s.evaluation_result))FROM MyStore.skill s WHERE s.employee_number = e.employee_number) AS technical_skills FROM MyStore.employee e", nativeQuery = true)
    List<Employee> getAllEmployeeProfDetails();


    List<Employee> findByEntity(String entityName);

    @Query("SELECT e FROM Employee e")
    @EntityGraph(value = "employee.skills")
    List<Employee> findAllWithSkills();

    @Query(value="SELECT e.category, COUNT(e.category) as count FROM Employee e  GROUP BY e.category", nativeQuery = true)
    List<Object[]>  findNumberOfCategoryInAll();

    @Query(value="SELECT e.category, COUNT(e.category) as count FROM Employee e WHERE e.entity = ?1 GROUP BY e.category", nativeQuery = true)
    List<Object[]>  findNumberOfCategoryInEntity(String entityName);


    @Query(value="SELECT e.category, COUNT(e.category) as count, SUM(s.score) as score " +
            "FROM Employee e " +
            "JOIN Skill s ON e.employee_number = s.employee_number " +
            "WHERE e.entity = ?1 " +
            "GROUP BY e.category", nativeQuery = true)
    List<Object[]> getCountNScoreInEntity(String entity);

    //SELECT e.category, SUM(s.score) / (COUNT(s.score)*3) * 100 AS percentage FROM MyStore.Employee e JOIN MyStore.skill s WHERE e.entity = "dewa"  AND e.employee_number=s.employee_number GROUP BY e.category;
    @Query(value="SELECT e.category, SUM(s.score) as sum, COUNT(s.score) as count FROM MyStore.Employee e JOIN MyStore.skill s WHERE e.entity = ?1  AND e.employee_number=s.employee_number GROUP BY e.category\n", nativeQuery = true)
    List<Map<String, Object>> getCategoryScoreCounts(String entity);



    @Query("SELECT DISTINCT e.entity FROM Employee e")
    List<String> findDistinctEntity();
}

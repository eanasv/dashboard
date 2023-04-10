package com.test.apiTest.service;


import com.test.apiTest.dto.EmployeeDto;
import com.test.apiTest.dto.SkillDto;
import com.test.apiTest.model.*;
import com.test.apiTest.dto.CategoryWiseScore;
import com.test.apiTest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeICTRoleRepo employeeICTRoleRepo;
//    @Autowired
//    private CourseRepo courseRepo;
    @Autowired
    private EmployeeEvaluationRepo employeeEvaluationRepo;
    @Autowired
    private EmployeeRoleRepository employeeRoleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SkillRepository skillRepository;

    public List<EmployeeICTRole> getAllEmployeeICT(){
        return employeeICTRoleRepo.findAll();
    }

//    public List<Course> getAllCourse(){
//        return courseRepo.findAll();
//    }

    public List<EmployeeEvaluation> getAllEmployeeEvaluatio(){
        return employeeEvaluationRepo.findAll();
    }



    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }



    public List<Map<String, Object>> getNumberOfCategoriesInAll() {
        List<Object[]>  categoryCount = employeeRepository.findNumberOfCategoryInAll();
        List<Map<String, Object>> counts = new ArrayList<>();
        for (Object[] row : categoryCount) {
            String category = (String) row[0];
            Long count = (Long) row[1];
            Map<String, Object> countMap = new HashMap<>();
            countMap.put("category", category);
            countMap.put("count", count);
            counts.add(countMap);
        }
        return counts;
    }

    public List<Map<String, Object>> getNumberOfCategoriesInOneEntity(String entityName) {
        List<Object[]>  categoryCount = employeeRepository.findNumberOfCategoryInEntity(entityName);
        List<Map<String, Object>> counts = new ArrayList<>();
        for (Object[] row : categoryCount) {
            String category = (String) row[0];
            Long count = (Long) row[1];
            Map<String, Object> countMap = new HashMap<>();
            countMap.put("category", category);
            countMap.put("count", count);
            counts.add(countMap);
        }
        return counts;
    }



    public List<CategoryWiseScore> getScoresByCategoryAndCompany(String entity) {
        String sql = "SELECT e.category, SUM(s.score) AS total_score " +
                "FROM MyStore.employee e " +
                "JOIN MyStore.skill s ON e.employee_number = s.employee_number " +
                "WHERE e.entity = ? AND e.category IN ('ICT', 'Data Analytics', 'Cyber security') " +
                "GROUP BY e.category";
        List<CategoryWiseScore> categoryScores = jdbcTemplate.query(sql, new Object[]{entity},
                (rs, rowNum) -> new CategoryWiseScore(rs.getString("category"), rs.getInt("total_score")));
        System.out.println(categoryScores);
        return categoryScores;
    }

    public List<EmployeeDto> getAllEmployeesWithSkills() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setEmployeeNumber(employee.getEmployeeNumber());
            employeeDto.setCategory(employee.getCategory());
            employeeDto.setSubCategory(employee.getSubCategory());
            employeeDto.setJob(employee.getJob());

            employeeDto.setEntity(employee.getEntity());
            List<Skill> skills = skillRepository.findByEmployeeNumber(employee.getEmployeeNumber());
            List<SkillDto> skillDtos = new ArrayList<>();
            for (Skill skill : skills) {
                SkillDto skillDto = new SkillDto();
                skillDto.setName(skill.getName());
                skillDto.setScore(skill.getScore());
                skillDtos.add(skillDto);
            }
            employeeDto.setSkills(skillDtos);
            employeeDtos.add(employeeDto);
        }
        return employeeDtos;
    }

    public List<EmployeeDto> getEmployeesByEntity(String entityName) {//
        List<Employee> employees = employeeRepository.findByEntity(entityName);
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setEmployeeNumber(employee.getEmployeeNumber());
            employeeDto.setCategory(employee.getCategory());
            employeeDto.setSubCategory(employee.getSubCategory());
            employeeDto.setJob(employee.getJob());

            employeeDto.setEntity(employee.getEntity());
            List<Skill> skills = skillRepository.findByEmployeeNumber(employee.getEmployeeNumber());
            List<SkillDto> skillDtos = new ArrayList<>();
            for (Skill skill : skills) {
                SkillDto skillDto = new SkillDto();
                skillDto.setName(skill.getName());
                skillDto.setScore(skill.getScore());
                skillDtos.add(skillDto);
            }
            employeeDto.setSkills(skillDtos);
            employeeDtos.add(employeeDto);
        }
        return employeeDtos;
       // return employeeRepository.findByEntity(entityName);

    }


    public List<Map<String, Object>> getSKillByCatInEntity(String entity) {
        List<Map<String, Object>>result = employeeRepository.getCategoryScoreCounts( entity);
        List<Map<String, Object>> counts = new ArrayList<>();
       for (Map<String, Object> row : result) {
            String category = (String) row.get("category");
           BigDecimal totalScore = (BigDecimal) row.get("sum");
           Long scoreCount = (Long) row.get("count");
           BigDecimal averageScore = new BigDecimal(String.valueOf(totalScore)).divide(new BigDecimal(scoreCount*3), 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
           ;

           System.out.println(totalScore+"   :  "+scoreCount+"  = "+averageScore);
           Map<String, Object> countMap = new HashMap<>();
           countMap.put("category", category);
            countMap.put("count", averageScore);
            counts.add(countMap);
        }

        return counts;
    }


}

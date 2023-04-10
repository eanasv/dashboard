package com.test.apiTest.service;

import com.test.apiTest.dto.*;
import com.test.apiTest.model.Employee;
import com.test.apiTest.model.Entities;
import com.test.apiTest.model.Skill;
import com.test.apiTest.repository.EmployeeRepository;
import com.test.apiTest.repository.EntityRepository;
import com.test.apiTest.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntityService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    EntityRepository entityRepository;

    public List<CategorySummaryWithScoreOfEntityDto> categorySummaryWithScoreOfEntityDtoList(String entityName) {
        String sql = "SELECT e.category, COUNT( s.name) AS num_skills, SUM(s.score) AS total_score FROM employee e INNER JOIN skill s ON e.employee_number = s.employee_number WHERE e.entity = ? GROUP BY e.category";

        List<CategorySummaryWithScoreOfEntityDto> result = jdbcTemplate.query(sql, new Object[]{entityName}, rs -> {
            List<CategorySummaryWithScoreOfEntityDto> categorySummaryList = new ArrayList<>();
            while (rs.next()) {
                CategorySummaryWithScoreOfEntityDto categorySummary = new CategorySummaryWithScoreOfEntityDto();
                categorySummary.setCategory(rs.getString("category"));
                categorySummary.setNumSkills(rs.getInt("num_skills"));
                categorySummary.setTotalScore(rs.getInt("total_score"));
                categorySummaryList.add(categorySummary);
            }
            return categorySummaryList;
        });

        return result;
    }

    public List<EmployeeDto> getAllEmployeesInAnEntity(String entityName) {
        List<Employee> employees = employeeRepository.findByEntity(entityName);
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        //List<CategorySummaryWithScoreOfEntityDto> categorySummaryWithScoreOfEntityDtos = new ArrayList<>();
        for (Employee employee : employees) {
            CategorySummaryWithScoreOfEntityDto categorySummaryWithScoreOfEntityDto = new CategorySummaryWithScoreOfEntityDto();
            categorySummaryWithScoreOfEntityDto.setCategory(employee.getCategory());

            List<Skill> skills = skillRepository.findByEmployeeNumber(employee.getEmployeeNumber());
            List<SkillDto> skillDtos = new ArrayList<>();
            Integer score = 0;
            Integer totalSkills =0;
            for (Skill skill : skills) {
                SkillDto skillDto = new SkillDto();
                skillDto.setName(skill.getName());
                score =skill.getScore()+score;
                totalSkills = totalSkills+1;
                skillDtos.add(skillDto);
            }
            categorySummaryWithScoreOfEntityDto.setTotalScore(score);
            categorySummaryWithScoreOfEntityDto.setNumSkills(totalSkills);
            //employeeDto.setSkills(skillDtos);
            //categorySummaryWithScoreOfEntityDtos.add(categorySummaryWithScoreOfEntityDto);
        }
        return employeeDtos;
    }

    public List<Entities> getAllEntities() {
        List<Entities> entities = entityRepository.findAllEntities();
        return entities;
    }

    public List<EntityTotalSkillDto> getgetEntityWthSkillPerce(){
        List<Entities> entities = entityRepository.findAll();
        List<EntityTotalSkillDto> entityScoreDTOs = new ArrayList<>();

        for (Entities entity : entities) {
            EntityTotalSkillDto scoreDto = new EntityTotalSkillDto();
            scoreDto.setLabel(entity.getLabel());
            scoreDto.setImage(entity.getImage());
            scoreDto.setValue(entity.getValue());

            List<Employee> employees = employeeRepository.findByEntity(entity.getLabel());
            int totalScore = 0;
            int totalSkills = 0;
            for (Employee employee : employees) {
                List<Skill> skills = skillRepository.findByEmployeeNumber(employee.getEmployeeNumber());
                for (Skill skill : skills) {
                    totalScore += skill.getScore();
                    totalSkills++;
                }
            }

            if (totalSkills > 0) {
                double scorePercentage = ((double) totalScore / (double) (totalSkills * 3)) * 100;
                scoreDto.setTotalSkillPercentage(scorePercentage);
            }

            entityScoreDTOs.add(scoreDto);
        }

        return entityScoreDTOs;
    }
}

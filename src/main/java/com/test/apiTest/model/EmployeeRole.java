package com.test.apiTest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class EmployeeRole {
    @Id
    private Integer employee_number;
    private String job;
    private String entity;
    @Column(name = "category")
    private String category;
    private String sub_category;
    private String role;
    private String technical_skills;
    private String evaluation_result;
    private String soft_skills;
    private Date evaluation_date;

    public EmployeeRole(Integer employee_number, String job, String entity, String category, String sub_category, String role, String technical_skills, String evaluation_result, String soft_skills, Date evaluation_date) {
        this.employee_number = employee_number;
        this.job = job;
        this.entity = entity;
        this.category = category;
        this.sub_category = sub_category;
        this.role = role;
        this.technical_skills = technical_skills;
        this.evaluation_result = evaluation_result;
        this.soft_skills = soft_skills;
        this.evaluation_date = evaluation_date;
    }

    public EmployeeRole() {
    }

    public Integer getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(Integer employee_number) {
        this.employee_number = employee_number;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTechnical_skills() {
        return technical_skills;
    }

    public void setTechnical_skills(String technical_skills) {
        this.technical_skills = technical_skills;
    }

    public String getEvaluation_result() {
        return evaluation_result;
    }

    public void setEvaluation_result(String evaluation_result) {
        this.evaluation_result = evaluation_result;
    }

    public String getSoft_skills() {
        return soft_skills;
    }

    public void setSoft_skills(String soft_skills) {
        this.soft_skills = soft_skills;
    }

    public Date getEvaluation_date() {
        return evaluation_date;
    }

    public void setEvaluation_date(Date evaluation_date) {
        this.evaluation_date = evaluation_date;
    }
}

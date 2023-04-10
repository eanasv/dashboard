package com.test.apiTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "employee_number")
    private Integer employeeNumber;

    private String job;

   private String entity;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "entity", referencedColumnName = "name")
//    private Entities entities;

    private String category;

    @Column(name = "sub_category")
    private String subCategory;

    //private String role;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
   private List<Skill> skills ;

    // getters and setters


//    public Employee(Integer employeeNumber, String job, String entity, String category, String subCategory, String role, List<Skill> skills) {
//        this.employeeNumber = employeeNumber;
//        this.job = job;
//        this.entity = entity;
//        this.category = category;
//        this.subCategory = subCategory;
//        this.role = role;
//        this.skills = skills;
//    }



    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

//    public Entities getEntities() {
//        return entities;
//    }
//
//    public void setEntities(Entities entities) {
//        this.entities = entities;
//    }

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

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }

    public void setSkills(List<Skill> skills) {
    }

    public Object getTechnicalSkillsJson() {
        return null;
    }

//    public List<Skill> getSkills() {
//        return skills;
//    }
//
//    public void setSkills(List<Skill> skills) {
//        this.skills = skills;
//    }
//    public void addSkill(Skill skill) {
//        if (skills == null) {
//            skills = new ArrayList<>();
//        }
//        skills.add(skill);
//        skill.setEmployee(this);
//    }
}

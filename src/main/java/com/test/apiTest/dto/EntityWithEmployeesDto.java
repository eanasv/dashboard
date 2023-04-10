package com.test.apiTest.dto;

import com.test.apiTest.model.Employee;

import java.util.List;

public class EntityWithEmployeesDto {
    private Long id;
    private String name;

    private byte[] image;

    private List<Employee> employees;

    public EntityWithEmployeesDto(Long id, String name, byte[] image, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.employees = employees;
    }



}

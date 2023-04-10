package com.test.apiTest.controller;


import com.test.apiTest.dto.EmployeeDto;
import com.test.apiTest.dto.SkillDto;
import com.test.apiTest.model.*;
import com.test.apiTest.dto.CategoryWiseScore;
import com.test.apiTest.response.ApiResponseInEntity;
import com.test.apiTest.service.EmployeeService;
import com.test.apiTest.service.ProfessionalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin("*")
//@RequestMapping("/api")

public class EmloyeeControl {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProfessionalDetailsService professionalDetailsService;


//    @PostMapping("/exceldata")
//    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file){
//        List responseFromExcel = this.employeeService.saveDataFromFile(file);
//        String responseMessage ="";
//        if(responseFromExcel.size()>0){
//            responseMessage = "Excel file uploaded and data successfully added/updated into the database !!";
//        }else{
//            responseMessage = "Please upload the appropriate Excel data file.";
//
//        }
//        return ResponseEntity.ok(Map.of("message",responseMessage));
//    }

    @GetMapping("/getAll/employeeRole")
    public  ResponseEntity<?> getAllEmployeeIctRole(){
        List<EmployeeICTRole> response = this.employeeService.getAllEmployeeICT();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Custom-Header-Value");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

//    @GetMapping("/getAll/course")
//    public  ResponseEntity<?> getAllCourses(){
//        List<Course> response = this.employeeService.getAllCourse();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Custom-Header", "Custom-Header-Value");
//        return new ResponseEntity<>(response, headers, HttpStatus.OK);
//    }

    @GetMapping("/getAll/employeeEvaluation")
    public  ResponseEntity<?> getAllEmployeeEvaluation(){
        List<EmployeeEvaluation> response = this.employeeService.getAllEmployeeEvaluatio();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Custom-Header-Value");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


    @GetMapping("/allEmployeesWithSkills")
    public List<EmployeeDto> getAllEmployeesWithSkills() {
        return employeeService.getAllEmployeesWithSkills();
    }




    @GetMapping("/employees/{entityName}")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByEntity(@PathVariable String entityName) {
        List<EmployeeDto> employees = employeeService.getEmployeesByEntity(entityName);
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }



    @GetMapping("/allDetails/{entityName}")
    public ApiResponseInEntity getAllDetailsByEntity(@PathVariable String entityName) {
        List<EmployeeDto> employeesInEntity = employeeService.getEmployeesByEntity(entityName);
        List<Map<String, Object>> categoryCountsInEntity  = employeeService.getNumberOfCategoriesInOneEntity(entityName);
        List<CategoryWiseScore> scores = employeeService.getScoresByCategoryAndCompany(entityName);
        return new ApiResponseInEntity(scores, categoryCountsInEntity,employeesInEntity);
    }


    @GetMapping("/allEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


//    @GetMapping("/employee-role/count-by-category")
//    public CategoryCountResponse getEmployeeRoleCountByCategory() {
//        CategoryCount categoryCount = employeeService.getNumberOfCategories();
//        CategoryCountResponse response = new CategoryCountResponse("success", "Category count retrieved successfully", categoryCount);
//        return response;
//    }

    @GetMapping("/count-by-category")
    public List<Map<String, Object>> getNumberOfCategoriesInAll() {
        List<Map<String, Object>> results  = employeeService.getNumberOfCategoriesInAll();
        return results;
    }

    @GetMapping("/entity/count-by-category/{entityName}")
    public List<Map<String, Object>> getNumberOfCategoriesInOneEntity(@PathVariable String entityName) {
        List<Map<String, Object>> results  = employeeService.getNumberOfCategoriesInOneEntity(entityName);
        return results;
    }


    @GetMapping("/scores")
    public List<CategoryWiseScore> getScores(@RequestParam("entity") String company) {
        List<CategoryWiseScore> scores = employeeService.getScoresByCategoryAndCompany(company);
        return scores;
    }

    @GetMapping("/skillByCatInEntity")
    public List<Map<String, Object>> getSKillByCatInEntity(@RequestParam("entity") String company) {
        List<Map<String, Object>> results = employeeService.getSKillByCatInEntity(company);
        return results;
    }


}

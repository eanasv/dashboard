package com.test.apiTest.controller;


import com.test.apiTest.dto.CategorySummaryWithScoreOfEntityDto;
import com.test.apiTest.dto.EntityListDto;
import com.test.apiTest.dto.EntityTotalSkillDto;
import com.test.apiTest.dto.EntityWithEmployeesDto;
import com.test.apiTest.model.Employee;
import com.test.apiTest.model.Entities;
import com.test.apiTest.model.Skill;
import com.test.apiTest.repository.EmployeeRepository;
import com.test.apiTest.repository.EntityRepository;
import com.test.apiTest.repository.SkillRepository;
import com.test.apiTest.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/entity")
public class EntityController {

    @Autowired
    private EntityService entityService;
    @Autowired
    private EntityRepository entityRepository;


    @GetMapping("/category-summary/{entityName}")
    public List<CategorySummaryWithScoreOfEntityDto> getCategorySummary( String entityName) {
        return entityService.categorySummaryWithScoreOfEntityDtoList(entityName);
    }

    @PostMapping("/addNew")
    public ResponseEntity<String> uploadEntity(@RequestParam("name") String label,
                                               @RequestParam("image") MultipartFile file) {
        String message = "";
        try {
            Entities entity = new Entities();
            entity.setLabel(label);
            byte[] imageBytes = file.getBytes();
            entity.setImage(imageBytes);
            entityRepository.save(entity);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!"+e;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }


    @GetMapping("/entities-with-employees")
    public List<EntityWithEmployeesDto> getAllEntitiesWithEmployees() {
        List<Entities> entities = entityRepository.findAllWithEmployees();
        List<EntityWithEmployeesDto> dtos = new ArrayList<>();
        for (Entities entity : entities) {
            dtos.add(new EntityWithEmployeesDto(entity.getId(), entity.getLabel(), entity.getImage(), entity.getEmployees()));
        }
        return dtos;
    }

    @GetMapping("/getAll")
    public List<EntityTotalSkillDto> getEntitiesWithScorePercentage() {
        List<EntityTotalSkillDto> entityScoreDTOs = entityService.getgetEntityWthSkillPerce();
        return entityScoreDTOs;
    }

}

package com.test.apiTest.repository;

import com.test.apiTest.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SkillRepository extends JpaRepository<Skill,Integer> {


    @Query(value="SELECT * from skill where employee_number=?1", nativeQuery = true)
    List<Skill> findByEmployeeNumber(Integer employeeNumber);

    @Query(value="SELECT * from skill where name=?1 and employee_number=?2 ", nativeQuery = true)
    Skill findByNameAndEmployeeNumber(String skillName,Integer employeeNumber);

//    @Query(value="SELECT * from skill where employee_number=?1", nativeQuery = true)
//    Skill findByEmployeeNumber(Integer reservedEmployeeNumber);

    //Skill findAllByEmployeeNumber(Integer reservedEmployeeNumber);
}

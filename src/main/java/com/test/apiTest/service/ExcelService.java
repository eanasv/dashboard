package com.test.apiTest.service;

import com.test.apiTest.model.Employee;
import com.test.apiTest.model.EmployeeICTRole;
import com.test.apiTest.model.Entities;
import com.test.apiTest.model.Skill;
import com.test.apiTest.repository.EmployeeRepository;
import com.test.apiTest.repository.SkillRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExcelService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SkillRepository skillRepository;



        public Boolean saveExcelData(MultipartFile file) throws IOException {
            Boolean response;
            try{
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            List list = new ArrayList<>();

            Integer reservedEmployeeNumber=0;
            System.out.println("************"+sheet.getLastRowNum());

                int rowNumber =1;
                List<Skill> skills=new ArrayList<>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {


                Row row = sheet.getRow(i);

                Integer employeeNumber = (int) row.getCell(0).getNumericCellValue();


                if(employeeNumber!=0){
                    reservedEmployeeNumber=employeeNumber;
                    rowNumber=i;
                    skills=new ArrayList<>();
                   //firstRow =sheet.getRow(rowNumber);
                }
                Employee employeeDetails = employeeRepository.findByEmployeeNumber(reservedEmployeeNumber);
                Employee employee = new Employee();

                //new record, If the data object does not exist, save it as a new record
                if (employeeDetails ==null && reservedEmployeeNumber!=0) {
                    employee.setEmployeeNumber((int) row.getCell(0).getNumericCellValue());
                    employee.setCategory(row.getCell(1).getStringCellValue());
                    employee.setSubCategory(row.getCell(2).getStringCellValue());
                    employee.setEntity(row.getCell(3).getStringCellValue());
                    employee.setJob(row.getCell(4).getStringCellValue());
                    //employee.setRole(row.getCell(5).getStringCellValue());
                    employee.setEmployeeNumber(reservedEmployeeNumber);
                    list.add(employee);
                    employeeRepository.save(employee);

            } else if(employeeDetails!=null) {
                // already existing record, updating the value
                    Row firstRow = sheet.getRow(rowNumber);
                    employeeDetails.setEmployeeNumber(reservedEmployeeNumber);
                    employeeDetails.setJob(firstRow.getCell(4).getStringCellValue());
                    employeeDetails.setEntity(firstRow.getCell(3).getStringCellValue());// eanas
                    employeeDetails.setCategory(firstRow.getCell(1).getStringCellValue());
                    employeeDetails.setSubCategory(firstRow.getCell(2).getStringCellValue());
                    //employeeDetails.setRole(employeeDetails.getRole());
                    //list.add(employeeDetails);

                    //eanas new code
                    Entities entity = new Entities();
                    employeeRepository.save(employeeDetails);


            }

              //  if (employeeDetails != null) {
                    String skillName = row.getCell(5).getStringCellValue();
                    Skill skillSet = skillRepository.findByNameAndEmployeeNumber(skillName,reservedEmployeeNumber);

                    Skill skill = new Skill();
                   System.out.println("eeeaannass"+skillSet);
                    if(skillSet!=null){
                        //skillSet.setName(row.getCell(5).getStringCellValue());
                        skillSet.setScore((int) row.getCell(7).getNumericCellValue());
                        skillSet.setEvaluationResult(row.getCell(6).getStringCellValue());
                        skillSet.setEmployee(employeeDetails);
                        //skills.save(skillSet);
                        skillRepository.save(skillSet);

 //                       List<Skill> skillDetails = skillRepository.findByEmployeeNumber(reservedEmployeeNumber);
//                        for(Skill item:skillDetails){
//                            if(item.getName()==row.getCell(5).getStringCellValue()){
//                                item.setName(row.getCell(5).getStringCellValue());
//                                item.setScore((int) row.getCell(7).getNumericCellValue());
//                                item.setEvaluationResult(row.getCell(6).getStringCellValue());
//                                item.setEmployee(employeeDetails);
//                                skills.add(item);
//                            }else{
//                                item.setName(row.getCell(5).getStringCellValue());
//                                item.setScore((int) row.getCell(7).getNumericCellValue());
//                                item.setEvaluationResult(row.getCell(6).getStringCellValue());
//                                item.setEmployee(employeeDetails);
//                                skills.add(item);
//                            }
//
//
//                        }

                        //skillRepository.save(skillDetails);
                    }
                    else {
                        System.out.println("+++++++++++++"+row.getCell(5).getStringCellValue());
                        skill.setName(row.getCell(5).getStringCellValue());
                        skill.setScore((int) row.getCell(7).getNumericCellValue());
                        skill.setEvaluationResult(row.getCell(6).getStringCellValue());
                        skill.setEmployee(employeeDetails);
                        //skills.add(skill);
                        skillRepository.save(skill);
                    }
//                }
//                else {
//                    Skill skill = new Skill();
//                    skill.setName(row.getCell(5).getStringCellValue());
//                    skill.setScore((int) row.getCell(7).getNumericCellValue());
//                    skill.setEvaluationResult(row.getCell(6).getStringCellValue());
//                    skill.setEmployee(employee);
//                    skills.add(skill);
                    //skillRepository.save(skill);
             //   }
//                if(i<sheet.getLastRowNum()) {
//                    Row nextRow = sheet.getRow(row.getRowNum() + 1);
//                    Cell nextCell = nextRow.getCell(0);
//                    Integer nextCellValue = (int) nextCell.getNumericCellValue();
//                    if (nextCellValue != 0) {
//                        //skillRepository.saveAll(skills);
//                    }
//                }
            }
                //skillRepository.saveAll(skills);
                response= true;
        } catch (IOException e) {
        throw new RuntimeException(e);
    }
            return response;
        }


}

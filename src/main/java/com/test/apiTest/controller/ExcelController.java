package com.test.apiTest.controller;

import com.test.apiTest.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {
          Boolean response =  this.excelService.saveExcelData(file);
        String responseMessage = "";
          try {
              if (response) {
                  responseMessage = "Excel file uploaded and data successfully added/updated into the database !!";
              } else {
                  responseMessage = "Something went wrong please contact developer.";

              }
              responseMessage = "Uploaded the file successfully: " + file.getOriginalFilename();
              return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
              //return ResponseEntity.ok(Map.of("message", responseMessage).toString());
          }catch(Exception e){
              responseMessage = "Could not upload the file: " + file.getOriginalFilename() + "!";
              return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseMessage);
          }

    }
}

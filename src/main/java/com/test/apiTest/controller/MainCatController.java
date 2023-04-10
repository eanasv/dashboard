package com.test.apiTest.controller;

import com.test.apiTest.model.MainCat;
import com.test.apiTest.service.MainCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/main-categories")
public class MainCatController {
    @Autowired
    private MainCatService mainCatService;

    @GetMapping
    public List<MainCat> getAllMainCats() {
        return mainCatService.getAllMainCats();
    }
}

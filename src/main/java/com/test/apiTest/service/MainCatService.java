package com.test.apiTest.service;

import com.test.apiTest.model.MainCat;
import com.test.apiTest.repository.MainCatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainCatService {
    @Autowired
    private MainCatRepository mainCatRepository;

    public List<MainCat> getAllMainCats() {
        return mainCatRepository.findAll();
    }
}

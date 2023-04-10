package com.test.apiTest.repository;

import com.test.apiTest.model.MainCat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainCatRepository extends JpaRepository<MainCat, Integer> {
    List<MainCat> findAll();

}

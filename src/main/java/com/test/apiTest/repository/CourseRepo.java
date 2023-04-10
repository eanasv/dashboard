package com.test.apiTest.repository;

import com.test.apiTest.model.Course;
import com.test.apiTest.model.GovEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course,Integer> {
}

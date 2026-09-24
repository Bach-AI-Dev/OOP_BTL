package com.baitapnhom.courseweb.repository;

import com.baitapnhom.courseweb.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}

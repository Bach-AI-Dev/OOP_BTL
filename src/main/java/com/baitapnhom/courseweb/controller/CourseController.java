package com.baitapnhom.courseweb.controller;

import OOP_BTL_develop.demo.dto.request.CourseCreationRequest;
import OOP_BTL_develop.demo.entity.Course;
import OOP_BTL_develop.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // 1. Tạo khóa học mới (POST)
    @PostMapping
    public Course createCourse(@RequestBody CourseCreationRequest request) {
        return courseService.createRequest(request);
    }

    // 2. Lấy danh sách tất cả khóa học (GET)
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // 3. Lấy thông tin chi tiết 1 khóa học theo ID (GET)
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable String id) {
        return courseService.getCourseById(id);
    }

    // 4. Cập nhật thông tin khóa học (PUT)
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable String id, @RequestBody CourseCreationRequest request) {
        return courseService.updateCourse(id, request);
    }

    // 5. Xóa khóa học theo ID (DELETE)
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable String id) {
        return courseService.deleteCourse(id);
    }
}

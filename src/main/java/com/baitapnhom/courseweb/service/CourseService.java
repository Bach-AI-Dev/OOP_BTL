package com.baitapnhom.courseweb.service;

import com.baitapnhom.courseweb.dto.request.CourseRequest;
import com.baitapnhom.courseweb.entity.Category;
import com.baitapnhom.courseweb.entity.Course;
import com.baitapnhom.courseweb.repository.CategoryRepository;
import com.baitapnhom.courseweb.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Course createRequest(CourseRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("KHÔNG TÌM THẤY Category VỚI ID: " + request.getCategoryId()));

        Course course = new Course();
        course.setTeacherId(request.getTeacherId());
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setThumbnailUrl(request.getThumbnailUrl());
        course.setPrice(request.getPrice());
        course.setStatus(request.getStatus() != null ? request.getStatus() : "DRAFT");
        course.setCategory(category);

        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("KHÔNG TÌM THẤY Course với ID: " + id));
    }

    public String deleteCourse(String id) {
        courseRepository.deleteById(id);
        return "Course ĐÃ ĐƯỢC XOÁ THÀNH CÔNG!";
    }

    public Course updateCourse(String id, CourseRequest request) {
        Course course = getCourseById(id);
        if (request.getTitle() != null) {
            course.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            course.setDescription(request.getDescription());
        }
        if (request.getPrice() != null) {
            course.setPrice(request.getPrice());
        }
        if (request.getStatus() != null) {
            course.setStatus(request.getStatus());
        }
        if (request.getThumbnailUrl() != null) {
            course.setThumbnailUrl(request.getThumbnailUrl());
        }
        return courseRepository.save(course);
    }
}
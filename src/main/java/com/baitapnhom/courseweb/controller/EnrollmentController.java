package com.baitapnhom.courseweb.controller;

import com.baitapnhom.courseweb.service.EnrollmentService;
import com.baitapnhom.courseweb.dto.response.EnrollmentResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    // Constructor Injection
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/courses/{courseId}/enroll")
    public ResponseEntity<Map<String, String>> enrollCourse(
            @PathVariable String courseId, 
            @RequestParam String studentId) {
            
        enrollmentService.enrollCourse(studentId, courseId);
        
        // Trả về JSON với Status 201 Created
        Map<String, String> response = new HashMap<>();
        response.put("message", "Đăng ký khóa học thành công!..............................");
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/users/me/courses")
    public ResponseEntity<List<EnrollmentResponse>> getMyCourses(
            @RequestParam String studentId) {
            
        List<EnrollmentResponse> myCourses = enrollmentService.getMyCourses(studentId);
        return ResponseEntity.ok(myCourses);
    }  
    
    @GetMapping("/courses/{courseId}/enrollment-status")
    public ResponseEntity<Boolean> getEnrollmentStatus(
        @PathVariable String courseId,
        @RequestParam String studentId) {
        
        boolean isEnrolled = enrollmentService.checkEnrollmentStatus(studentId, courseId);
        return ResponseEntity.ok(isEnrolled);
    }
}

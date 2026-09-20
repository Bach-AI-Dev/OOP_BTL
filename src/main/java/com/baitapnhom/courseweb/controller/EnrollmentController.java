package com.baitapnhom.courseweb.controller;

import com.baitapnhom.courseweb.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.baitapnhom.courseweb.dto.response.EnrollmentResponse;
import java.util.List;
@RestController
@RequestMapping("/api")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // Giả định userId được lấy từ JWT Token (TV1 làm), ở đây tạm truyền qua biến
    @PostMapping("/courses/{courseId}/enroll")
    public ResponseEntity<String> enrollCourse(
            @PathVariable String courseId, 
            @RequestParam String studentId) {
            enrollmentService.enrollCourse(studentId, courseId);
            return ResponseEntity.ok("Đăng ký khóa học thành công!........................................");
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
        
        boolean isEnrolled = enrollmentService .checkEnrollmentStatus(studentId, courseId);
        return ResponseEntity.ok(isEnrolled);
    }
}

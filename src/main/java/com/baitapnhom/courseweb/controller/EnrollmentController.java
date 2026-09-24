package com.baitapnhom.courseweb.controller;

import com.baitapnhom.courseweb.service.EnrollmentService;
import com.baitapnhom.courseweb.dto.response.EnrollmentResponse;
import com.baitapnhom.courseweb.dto.response.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    // Constructor Injection
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/courses/{courseId}/enroll")
    public ResponseEntity<ApiResponse<EnrollmentResponse>> enrollCourse(
            @PathVariable String courseId, 
            @RequestParam String studentId) {
            
        // 1. Gọi Service và hứng kết quả (DTO)
        EnrollmentResponse enrollmentData = enrollmentService.enrollCourse(studentId, courseId);
        
        // 2. Bọc vào chuẩn ApiResponse của nhóm
        ApiResponse<EnrollmentResponse> response = new ApiResponse<>();
        response.setCode(1000); // 1000 hoặc mã code thành công quy ước của nhóm
        response.setMessage("Đăng ký khóa học thành công!");
        response.setResult(enrollmentData); // Trả kèm thông tin khóa học vừa đăng ký/cập nhật
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/users/me/courses")
    public ResponseEntity<ApiResponse<List<EnrollmentResponse>>> getMyCourses(
            @RequestParam String studentId) {
            
        List<EnrollmentResponse> myCourses = enrollmentService.getMyCourses(studentId);
        
        // Đồng bộ chuẩn ApiResponse cho API lấy danh sách
        ApiResponse<List<EnrollmentResponse>> response = new ApiResponse<>();
        response.setCode(1000);
        response.setMessage("Lấy danh sách thành công");
        response.setResult(myCourses);

        return ResponseEntity.ok(response);
    }  
    
    @GetMapping("/courses/{courseId}/enrollment-status")
    public ResponseEntity<ApiResponse<Boolean>> getEnrollmentStatus(
        @PathVariable String courseId,
        @RequestParam String studentId) {
        
        boolean isEnrolled = enrollmentService.checkEnrollmentStatus(studentId, courseId);
        
        // Đồng bộ chuẩn ApiResponse
        ApiResponse<Boolean> response = new ApiResponse<>();
        response.setCode(1000);
        response.setMessage("Kiểm tra trạng thái thành công");
        response.setResult(isEnrolled);

        return ResponseEntity.ok(response);
    }
}
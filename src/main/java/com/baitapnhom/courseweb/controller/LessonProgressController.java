package com.baitapnhom.courseweb.controller;

import com.baitapnhom.courseweb.dto.request.LessonProgressRequest;
import com.baitapnhom.courseweb.dto.response.LessonProgressResponse;
import com.baitapnhom.courseweb.service.ILessonProgressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/progress")
public class LessonProgressController {
    
    private final ILessonProgressService progressService;

    // Sử dụng Constructor Injection an toàn thay cho @RequiredArgsConstructor
    public LessonProgressController(ILessonProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping("/update")
    public ResponseEntity<LessonProgressResponse> updateProgress(@Valid @RequestBody LessonProgressRequest request) {
        LessonProgressResponse responseData = progressService.updateProgress(request);
        return ResponseEntity.ok(responseData);
    }
}
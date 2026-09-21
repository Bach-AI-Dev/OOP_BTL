package com.baitapnhom.courseweb.controller;


import com.baitapnhom.courseweb.dto.request.LessonProgressRequest;
import com.baitapnhom.courseweb.dto.response.LessonProgressResponse;
import com.baitapnhom.courseweb.service.ILessonProgressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/progress")
public class LessonProgressController{
    private final ILessonProgressService progressService;

    @PostMapping("/update")
    ResponseEntity <LessonProgressResponse> updateProgress(@Valid @RequestBody LessonProgressRequest request){
        LessonProgressResponse responsedata= progressService.updateProgress(request);
        return ResponseEntity.ok(responsedata);
    }
}

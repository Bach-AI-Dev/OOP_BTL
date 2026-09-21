package com.baitapnhom.courseweb.controller;

import com.baitapnhom.courseweb.dto.request.LessonRequest;
import com.baitapnhom.courseweb.dto.response.LessonResponse;
import com.baitapnhom.courseweb.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LessonController{
    @Autowired
    private LessonService lessonService;

    // 1. GET /api/courses/{courseId}/lessons (Lấy danh sách bài học của khóa học)
    @GetMapping("/courses/{courseId}/lessons")
    public ResponseEntity<List<LessonResponse>> getLessonsByCourse(@PathVariable String courseId) {
        System.out.println("Lấy danh sách bài học của khóa học có id: "+ courseId );
        return ResponseEntity.ok(lessonService.getLessonsByCourseId(courseId));
    }

    // 2. POST /api/courses/{courseId}/lessons (Thêm bài học mới vào khóa học)
    @PostMapping("/courses/{courseId}/lessons")
    public ResponseEntity<LessonResponse> createLesson(
            @PathVariable String courseId,
            @RequestBody LessonRequest request) {
        LessonResponse savedLesson = lessonService.createLesson(courseId, request);

        System.out.println("ID khóa học: " + savedLesson.getCourseId());
        System.out.println("ID bài học vừa tạo: " + savedLesson.getId());
        System.out.println("Tiêu đề: " + savedLesson.getName());

        return ResponseEntity.ok(savedLesson);
    }

    // 3. PUT /api/lessons/{id} (Cập nhật thông tin bài học)
    @PutMapping("/lessons/{id}")
    public ResponseEntity<LessonResponse> updateLesson(
            @PathVariable String id,
            @RequestBody LessonRequest request) {

        LessonResponse updatedLesson = lessonService.updateLesson(id, request);

        System.out.println("ID bài học vừa sửa: " + id);

        return ResponseEntity.ok(updatedLesson);
    }


    // 4. DELETE /api/lessons/{id} (Xóa bài học)
    @DeleteMapping("/lessons/{id}")
    public ResponseEntity<String> deleteLesson(@PathVariable String id) {

        System.out.println("Đang tiến hành xóa bài học có ID: " + id);

        // Gọi Service để thực hiện lệnh xóa trong Database
        lessonService.deleteLesson(id);

        System.out.println("Xóa thành công bài học ID: " + id);

        return ResponseEntity.ok("Deleted lesson successfully");
    }

    // 5. GET /api/lessons/{id} (Lấy chi tiết 1 bài học để hiển thị lên URL frontend)
    @GetMapping("/lessons/{id}")
    public ResponseEntity<LessonResponse> getLessonById(@PathVariable String id) {
        System.out.println("Lấy thông tin chi tiết bài học ID: " + id);
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }

}

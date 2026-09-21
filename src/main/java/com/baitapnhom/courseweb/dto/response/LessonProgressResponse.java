package com.baitapnhom.courseweb.dto.response;

import lombok.Data;

@Data
public class LessonProgressResponse {
    private String studentId;
    private String lessonId;
    private Integer watchedTimeSeconds;
    private Boolean isCompleted;
    private Integer completionPercentage;
}
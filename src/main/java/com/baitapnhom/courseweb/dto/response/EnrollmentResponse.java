package com.baitapnhom.courseweb.dto.response;

import java.time.LocalDateTime;

public class EnrollmentResponse {

    private String id;
    private String courseTitle;
    private String status;
    private LocalDateTime enrollDate;

    // Default Constructor (Bắt buộc phải có để Spring/Jackson có thể parse JSON)
    public EnrollmentResponse() {
    }

    // All-args Constructor
    public EnrollmentResponse(String id, String courseTitle, String status, LocalDateTime enrollDate) {
        this.id = id;
        this.courseTitle = courseTitle;
        this.status = status;
        this.enrollDate = enrollDate;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getEnrollDate() {
        return enrollDate;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEnrollDate(LocalDateTime enrollDate) {
        this.enrollDate = enrollDate;
    }
}
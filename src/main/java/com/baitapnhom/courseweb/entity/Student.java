package com.baitapnhom.courseweb.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "student_id",length = 36)
    private String studentId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // Báo cho Spring Boot biết: Hãy lấy ID của User làm ID của Student
    @JoinColumn(name = "student_id")
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Constructors
    public Student() {
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String id) {
        this.studentId = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
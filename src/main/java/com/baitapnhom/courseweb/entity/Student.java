package com.baitapnhom.courseweb.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @Column(name = "student_id")
    private String studentId;
    
    @OneToOne
    @JoinColumn(name = "student_id", nullable = false, insertable = false, updatable = false)
    private User user;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    // Constructor, Getter, Setter
    public Student() {}
    
    public String getStudentId() {return studentId;}
    public void setStudentId(String studentId) {this.studentId = studentId;}
    
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    
    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
}

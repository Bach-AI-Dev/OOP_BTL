package com.baitapnhom.courseweb.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    // Cột teacher_id
    @Column(name = "teacher_id", nullable = false)
    private String teacherId;

    // Cột category_id (Khóa ngoại liên kết với bảng categories)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Cột title
    @Column(name = "title", nullable = false)
    private String title;

    // Cột description
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // Cột thumbnail_url
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    // Cột price
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    // Cột status
    @Column(name = "status", length = 20)
    private String status;

    // Cột created_at
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Cột updated_at
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Course() {
    }

    // JPA tự động điền thời gian và giá trị mặc định khi bạn tạo mới (INSERT)
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "DRAFT"; // Mặc định là bản nháp
        }
        if (this.price == null) {
            this.price = BigDecimal.ZERO; // SỬA Ở ĐÂY: Dùng BigDecimal.ZERO thay vì 0.0
        }
    }

    // JPA tự động cập nhật thời gian khi bạn sửa (UPDATE)
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // ================= GETTER VÀ SETTER =================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    // SỬA Ở ĐÂY: Đổi Double thành BigDecimal
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

package com.baitapnhom.courseweb.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//  Nhieu lesson thuoc 1 course
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(unique = true) // Tránh việc 2 bài học xài chung 1 link video
    private String videoUrl;

    private String thumbnailUrl;
    private Integer duration;  //do dai video

    private Integer lessonOrder;

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getLessonOrder() {
        return lessonOrder;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLessonOrder(Integer lessonOrder) {
        this.lessonOrder = lessonOrder;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

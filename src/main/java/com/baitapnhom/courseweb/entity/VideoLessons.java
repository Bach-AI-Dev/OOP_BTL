package com.baitapnhom.courseweb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "video_lessons")
public class VideoLessons {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String lessonId;

    @Column(name = "url")
    private String url;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }
}

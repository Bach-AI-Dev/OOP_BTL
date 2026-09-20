package com.baitapnhom.courseweb.dto.response;

public class LessonResponse{
    private Long id;
    private Long courseId; // Chỉ trả về ID của course cho nhẹ, không trả nguyên cái Entity Course
    private String title;
    private String description;
    private String videoUrl;
    private String thumbnailUrl;
    private Integer duration;
    private Integer lessonOrder;

    public Long getId() {
        return id;
    }

    public Integer getLessonOrder() {
        return lessonOrder;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setLessonOrder(Integer lessonOrder) {
        this.lessonOrder = lessonOrder;
    }
}
package com.baitapnhom.courseweb.dto.request;

public class LessonRequest{
    private String title;
    private String description;
    private String videoUrl;
    private String thumbnailUrl;
    private Integer duration;
    private Integer lessonOrder;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public Integer getLessonOrder() {
        return lessonOrder;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
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
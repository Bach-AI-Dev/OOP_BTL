package com.baitapnhom.courseweb.repository;

import com.baitapnhom.courseweb.entity.VideoLessons;
import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoLessonRepository extends JpaRepository <VideoLessons,Integer> {

}

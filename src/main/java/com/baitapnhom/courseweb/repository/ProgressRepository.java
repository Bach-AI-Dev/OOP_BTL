package com.baitapnhom.courseweb.repository;

import com.baitapnhom.courseweb.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgressRepository extends JpaRepository<Progress,Integer>{
    Optional<Progress> findByVideoLesson_LessonIdAndStudent_StudentId(Integer lessonId, Integer studentId);
}


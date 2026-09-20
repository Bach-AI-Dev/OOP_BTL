package com.baitapnhom.courseweb.repository;

import com.baitapnhom.courseweb.entity.Enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
    // Hàm dùng để kiểm tra việc chống đăng ký trùng
    boolean existsByStudentStudentIdAndCourseId(String studentId, String courseId);    
    // Lấy danh sách khóa học của 1 user (My Courses)
    List<Enrollment> findByStudentStudentId(String studentId);
    //check trạng thái enroll
    default boolean checkEnrollmentStatus(String studentId, String courseId){
        return existsByStudentStudentIdAndCourseId(studentId, courseId);
    }

}

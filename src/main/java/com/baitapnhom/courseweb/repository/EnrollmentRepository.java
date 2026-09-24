package com.baitapnhom.courseweb.repository;

import com.baitapnhom.courseweb.entity.Enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
    // Hàm dùng để kiểm tra việc chống đăng ký trùng
    boolean existsByStudentStudentIdAndCourseId(String studentId, String courseId);    
    // Lấy danh sách khóa học của 1 user (My Courses)
    List<Enrollment> findByStudentStudentIdOrderByEnrollDateDesc(String studentId);
    // 3. Dùng cho hàm enrollCourse của Service (để lấy bản ghi lên xử lý trạng thái CANCELED)
    Optional<Enrollment> findByStudentStudentIdAndCourseId(String studentId, String courseId);

}

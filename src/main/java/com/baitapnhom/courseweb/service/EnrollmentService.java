package com.baitapnhom.courseweb.service;

import com.baitapnhom.courseweb.entity.Course;
import com.baitapnhom.courseweb.entity.Enrollment;
import com.baitapnhom.courseweb.entity.Student;

import com.baitapnhom.courseweb.enums.EnrollmentStatus;

import com.baitapnhom.courseweb.repository.CourseRepository;
import com.baitapnhom.courseweb.repository.EnrollmentRepository;
import com.baitapnhom.courseweb.repository.StudentRepository;

import com.baitapnhom.courseweb.dto.response.EnrollmentResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class EnrollmentService {
    // 1. Khai báo các dependency với từ khóa final
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    
    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             CourseRepository courseRepository,
                             StudentRepository studentRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void enrollCourse(String studentId, String courseId) {
        // 1. Kiểm tra chống đăng ký trùng
        if (enrollmentRepository.existsByStudentStudentIdAndCourseId(studentId, courseId)) {
            throw new RuntimeException("Ban da dang ky khoa hoc nay roi ....................!");
        }

        // 2. Tìm Student trực tiếp từ StudentRepository (Không cần ép kiểu)
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Khong tim thay hoc vien............................"));
            
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Khong tim thay khoa hoc.............................."));

        // 3. Tạo Enrollment mới
        Enrollment enrollment = new Enrollment(student, course, EnrollmentStatus.ACTIVE);
        
        // 4. Lưu vào Database
        enrollmentRepository.save(enrollment);
    }
    public List<EnrollmentResponse> getMyCourses(String studentId) {
        // Lấy danh sách khóa học mà studentId này đã đăng ký
        List<Enrollment> enrollments = enrollmentRepository.findByStudentStudentId(studentId);        
        // Chuyển đổi từ Entity (Enrollment) sang DTO (EnrollmentResponse) để trả về Controller
        return enrollments.stream().map(enrollment -> new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getCourse().getTitle(),
                enrollment.getStatus().name(),
                enrollment.getEnrollDate()
        )).collect(Collectors.toList());
    }
    
    public boolean checkEnrollmentStatus(String studenId, String courseId) {
        // Gọi thẳng xuống DB để đếm xem có tồn tại bản ghi ghép cặp này chưa
        return enrollmentRepository.existsByStudentStudentIdAndCourseId(studenId, courseId);
    }
}

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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {
    //  Khai báo các dependency với từ khóa final
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
    public EnrollmentResponse enrollCourse(String studentId, String courseId) {
        // 1. Kiểm tra student tồn tại
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy học viên với ID: " + studentId));

        // 2. Kiểm tra course tồn tại
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy khóa học với ID: " + courseId));

        // 3. Kiểm tra enrollment hiện tại
        Optional<Enrollment> optionalEnrollment = enrollmentRepository
            .findByStudentStudentIdAndCourseId(studentId, courseId);

        if (optionalEnrollment.isPresent()) {
            Enrollment existingEnrollment = optionalEnrollment.get();

            // 4. Xử lý trạng thái cũ nếu có
            switch (existingEnrollment.getStatus()) {
                case ACTIVE -> throw new RuntimeException("Bạn đã đăng ký khóa học này rồi!");
                case COMPLETED -> throw new RuntimeException("Bạn đã hoàn thành khóa học này, không thể đăng ký lại!");
                case CANCELED -> {
                    // 5a. Cập nhật enrollment (Re-activate)
                    existingEnrollment.setStatus(EnrollmentStatus.ACTIVE);
                    // 6a. Lưu (UPDATE)
                    enrollmentRepository.saveAndFlush(existingEnrollment);
                    // Trả về DTO
                    return new EnrollmentResponse(
                            existingEnrollment.getId(),
                            existingEnrollment.getCourse().getTitle(),
                            existingEnrollment.getStatus().name(),
                            existingEnrollment.getEnrollDate()
                    );
                }
            }
        }

        // 5b. Tạo enrollment mới nếu chưa từng tồn tại
        Enrollment newEnrollment = new Enrollment(student, course, EnrollmentStatus.ACTIVE);
        //Phải LƯU (INSERT) xuống DB trước khi convert sang DTO
        newEnrollment = enrollmentRepository.saveAndFlush(newEnrollment);
        // 6b. Trả về thông tin
        return new EnrollmentResponse(
            newEnrollment.getId(),
            newEnrollment.getCourse().getTitle(), // Hoặc getId() tùy cấu trúc DTO
            newEnrollment.getStatus().name(),
            newEnrollment.getEnrollDate()
        );
    }

    public List<EnrollmentResponse> getMyCourses(String studentId) {
        // Lấy danh sách khóa học mà studentId này đã đăng ký
        List<Enrollment> enrollments = enrollmentRepository.findByStudentStudentIdOrderByEnrollDateDesc(studentId);
        // Chuyển đổi từ Entity (Enrollment) sang DTO (EnrollmentResponse) để trả về Controller
        return enrollments.stream().map(enrollment -> new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getCourse().getTitle(),
                enrollment.getStatus().name(),
                enrollment.getEnrollDate()
        )).collect(Collectors.toList());
    }

    public boolean checkEnrollmentStatus(String studentId, String courseId) {
        // Gọi thẳng xuống DB để đếm xem có tồn tại bản ghi ghép cặp này chưa
        return enrollmentRepository.existsByStudentStudentIdAndCourseId(studentId, courseId);
    }
}
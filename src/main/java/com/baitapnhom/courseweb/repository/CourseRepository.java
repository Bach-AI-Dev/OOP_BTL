package OOP_BTL_develop.demo.repository;

import OOP_BTL_develop.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}

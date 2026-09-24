package com.baitapnhom.courseweb.service;

import com.baitapnhom.courseweb.dto.request.LessonProgressRequest;
import com.baitapnhom.courseweb.dto.response.LessonProgressResponse;
import com.baitapnhom.courseweb.entity.LessonProgress;
import com.baitapnhom.courseweb.entity.VideoLessons;
import com.baitapnhom.courseweb.repository.EnrollmentRepository; // ✅ Import thêm Enrollment
import com.baitapnhom.courseweb.repository.LessonProgressRepository;
import com.baitapnhom.courseweb.repository.StudentRepository;
import com.baitapnhom.courseweb.repository.VideoLessonsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class LessonProgressImpl implements ILessonProgressService {

    private final LessonProgressRepository progressRepository;
    private final VideoLessonsRepository videoRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository; //  Tiêm thêm để check đăng ký

    @Override
    public LessonProgressResponse updateProgress(LessonProgressRequest request){

        Optional<VideoLessons> videoRequest = videoRepository.findById(request.getLessonId());

        if(videoRequest.isEmpty()) {
            throw new RuntimeException("Không tìm thấy thông tin video bài học!");
        }

        VideoLessons video = videoRequest.get();

        //  1. KIỂM TRA ENROLLMENT & TÍNH HỢP LỆ CỦA LESSON
        // Lưu ý: Sửa '.getCourse()' thành phương thức thực tế trong entity VideoLessons của bạn
        String courseId = video.getLesson().getCourse().getId();

        // Sửa lại tên hàm cho khớp với bên EnrollmentRepository
        boolean isEnrolled = enrollmentRepository.existsByStudentStudentIdAndCourseId(request.getStudentId(), courseId);
        if(!isEnrolled) {
            throw new RuntimeException("Học viên chưa đăng ký khóa học này, không thể cập nhật tiến độ!");
        }

        int durationVideo = video.getDurationSeconds();
        //  2. FIX LỖI TOÁN HỌC (CHIA CHO 0)
        if (durationVideo <= 0) {
            durationVideo = 1;
        }

        Optional<LessonProgress> progressRequest = progressRepository.findByVideoLesson_LessonIdAndStudent_StudentId(request.getLessonId(), request.getStudentId());

        int tolerance = 2;
        LessonProgress savedProgress;

        //  3. CHẶN LÙI TIẾN ĐỘ (Chặn trường hợp client gửi timeDelta âm)
        int validTimeDelta = Math.max(request.getTimeDelta(), 0);

        if(progressRequest.isPresent()){
            LessonProgress progress = progressRequest.get();

            int newWatchedTimeSeconds = progress.getWatchedTimeSeconds() + validTimeDelta;
            newWatchedTimeSeconds = Math.min(newWatchedTimeSeconds, durationVideo);

            int newLastPosition = Math.min(request.getLastPosition(), durationVideo);

            if(newWatchedTimeSeconds >= (durationVideo - tolerance) && !progress.getIsCompleted() && newLastPosition >= (durationVideo-tolerance)){
                progress.setIsCompleted(true);
            }

            progress.setWatchedTimeSeconds(newWatchedTimeSeconds);
            progress.setLastPosition(newLastPosition);

            savedProgress = progressRepository.save(progress);
        }
        else{
            LessonProgress newProgress = new LessonProgress();

            newProgress.setVideoLesson(videoRepository.getReferenceById(request.getLessonId()));
            newProgress.setStudent(studentRepository.getReferenceById(request.getStudentId()));

            int initalTime = Math.min(validTimeDelta, durationVideo);
            newProgress.setWatchedTimeSeconds(initalTime);

            int newLastPosition = Math.min(request.getLastPosition(), durationVideo);
            newProgress.setLastPosition(newLastPosition);

            if(initalTime >= (durationVideo - tolerance) && newLastPosition >= (durationVideo - tolerance)){
                newProgress.setIsCompleted(true);
            }
            else{
                newProgress.setIsCompleted(false);
            }

            savedProgress = progressRepository.save(newProgress);
        }

        double percentage = (double) savedProgress.getWatchedTimeSeconds() / durationVideo * 100;
        int percentCompleted = (int) Math.round(percentage);

        LessonProgressResponse responseDto = new LessonProgressResponse();
        responseDto.setStudentId(request.getStudentId());
        responseDto.setLessonId(request.getLessonId());
        responseDto.setWatchedTimeSeconds(savedProgress.getWatchedTimeSeconds());
        responseDto.setIsCompleted(savedProgress.getIsCompleted());
        responseDto.setCompletionPercentage(percentCompleted);

        return responseDto;
    }
}
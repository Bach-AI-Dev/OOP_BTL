package com.baitapnhom.courseweb.service;

import com.baitapnhom.courseweb.dto.request.LessonProgressRequest;
import com.baitapnhom.courseweb.dto.response.LessonProgressResponse; // Kéo file Response DTO vào
import com.baitapnhom.courseweb.entity.LessonProgress;
import com.baitapnhom.courseweb.entity.VideoLessons;
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
    
    public LessonProgressImpl(LessonProgressRepository progressRepository, 
                              VideoLessonsRepository videoRepository, 
                              StudentRepository studentRepository) {
        this.progressRepository = progressRepository;
        this.videoRepository = videoRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public LessonProgressResponse updateProgress(LessonProgressRequest request){

        Optional<VideoLessons> videoRequest = videoRepository.findById(request.getLessonId());

        if(videoRequest.isEmpty()) {
            throw new RuntimeException("Không tìm thấy thông tin video bài học!");
        }

        VideoLessons video = videoRequest.get();
        int durationVideo = video.getDurationSeconds();

        Optional<LessonProgress> progressRequest = progressRepository.findByVideoLesson_LessonIdAndStudent_StudentId(request.getLessonId(), request.getStudentId());

        int tolerance = 2;

        // Khai báo một biến chung để hứng dữ liệu sau khi lưu
        LessonProgress savedProgress;

        if(progressRequest.isPresent()){
            LessonProgress progress = progressRequest.get();

            int newWatchedTimeSeconds = progress.getWatchedTimeSeconds() + request.getTimeDelta();
            newWatchedTimeSeconds = Math.min(newWatchedTimeSeconds, durationVideo);

            int newLastPosition = Math.min(request.getLastPosition(), durationVideo);

            if(newWatchedTimeSeconds >= (durationVideo - tolerance) && !progress.getIsCompleted() && newLastPosition >= (durationVideo-tolerance)){
                progress.setIsCompleted(true);
            }

            progress.setWatchedTimeSeconds(newWatchedTimeSeconds);
            progress.setLastPosition(newLastPosition);

            // Lưu và gán vào biến chung
            savedProgress = progressRepository.save(progress);
        }
        else{
            LessonProgress newProgress = new LessonProgress();

            newProgress.setVideoLesson(videoRepository.getReferenceById(request.getLessonId()));
            newProgress.setStudent(studentRepository.getReferenceById(request.getStudentId()));

            int initalTime = Math.min(request.getTimeDelta(), durationVideo);
            newProgress.setWatchedTimeSeconds(initalTime);

            int newLastPosition = Math.min(request.getLastPosition(), durationVideo);
            newProgress.setLastPosition(newLastPosition);

            if(initalTime >= (durationVideo - tolerance) && newLastPosition >= (durationVideo - tolerance)){
                newProgress.setIsCompleted(true);
            }
            else{
                newProgress.setIsCompleted(false);
            }

            // Lưu và gán vào biến chung
            savedProgress = progressRepository.save(newProgress);
        }

        //  TÍNH TOÁN PHẦN TRĂM VÀ ĐÓNG GÓI VÀO DTO TRẢ VỀ
        double percentage = (double) savedProgress.getWatchedTimeSeconds() / durationVideo * 100;
        int percentCompleted = (int) Math.round(percentage);

        LessonProgressResponse responseDto = new LessonProgressResponse();
        responseDto.setStudentId(request.getStudentId());
        responseDto.setLessonId(request.getLessonId());
        responseDto.setWatchedTimeSeconds(savedProgress.getWatchedTimeSeconds());
        responseDto.setIsCompleted(savedProgress.getIsCompleted());
        responseDto.setCompletionPercentage(percentCompleted); // Gắn phần trăm vừa tính

        return responseDto;
    }
}
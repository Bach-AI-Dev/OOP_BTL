package com.baitapnhom.courseweb.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LessonProgressRequest {
    @NotNull(message = "studentId không được để trống")
    private String studentId;

    @NotNull(message = "lessonId không được để trống")
    private String lessonId;

    @NotNull(message = "Thời gian không được để trống")
    @Min(value = 0,message = "Thời gian không được âm")
    private Integer timeDelta;

    @NotNull(message = "Vi tri khong duoc de trong")
    @Min(value = 0,message = "Vi tri khong duoc am")
    private  Integer lastPosition;
}

package com.baitapnhom.courseweb.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.dao.DataIntegrityViolationException;

import com.baitapnhom.courseweb.dto.response.ApiResponse;

// Nơi tập hợp tất cả các exception
@ControllerAdvice
public class GlobalExceptionHandler {


    // Nếu có những lỗi khác thì sẽ bắt và chạy ra message
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingUncategoriedException(RuntimeException exception) {

        ApiResponse apiResponse = new ApiResponse<>();

        apiResponse.setCode(ErrorCode.UNCATEGORIED_EXISTED.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIED_EXISTED.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }
    
    // Định nghĩa Appexception
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse<>();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(exception.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }
    // Định nghĩa Validation
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);

        ApiResponse apiResponse = new ApiResponse<>();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }
    //  Thêm hàm bắt ngoại lệ trùng lặp database
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    ResponseEntity<ApiResponse<?>> handlingDataIntegrityViolation(DataIntegrityViolationException exception) {
        ApiResponse<?> apiResponse = new ApiResponse<>();
        
        apiResponse.setCode(1009);
        apiResponse.setMessage("Ban da dang ky khoa hoc nay roi (du lieu da ton tai).................................");
        
        return ResponseEntity.badRequest().body(apiResponse);
    }
}

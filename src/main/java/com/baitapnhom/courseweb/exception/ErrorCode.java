package com.baitapnhom.courseweb.exception;

public enum ErrorCode {

    UNCATEGORIED_EXISTED(9999, "Lỗi chưa phần loại(uncategoried)"),
    USER_EXISTED(1001, "User đã tồn tại"),
    EMAIL_EXISTED(1002, "Email đã tồn tại"),

    USERNAME_INVALID(1003, "Username phải có tối thiểu 3 ký tự và tối đa 50 ký tự.............."),
    USERNAME_EMPTY(1004, "Username không được để trống!!!!!!!!!!!!!!!!!!!!"),

    PASSWORD_INVALID(1005, "Password phải có tối thiểu 6 ký tự............"),
    PASSWORD_EMPTY(1006, "Password không được để trống!!!!!!!!!!!!!!!!!!!!"),

    EMAIL_INVALID(1007, "Email không đúng định dạng..........."),
    EMAIL_EMPTY(1008, "Email không được để trống!!!!!!!!!!!!!!!!!!!!"),
            
    USER_NOT_EXISTED(1009, "User không tồn tại"),
    UNAUTHENTICATED(1010, "Mật khẩu không chính xác hoặc chưa được xác thực"),
    ;

    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

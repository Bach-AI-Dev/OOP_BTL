package com.baitapnhom.courseweb.dto.request;

import com.baitapnhom.courseweb.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "Username không được để trống!!!!!!!!!!!!!!!!!!!!")
    @Size(min = 3, max = 50, message = "Username phải có tối thiểu 3 ký tự và tối đa 50 ký tự..............")
    private String username;

    @NotBlank(message = "Password không được để trống!!!!!!!!!!!!!!!!!!!!")
    @Size(min = 6, message = "Password phải có tối thiểu 6 ký tự............")
    private String password;

    @NotBlank(message = "Email không được để trống!!!!!!!!!!!!!!!!!!!!")
    @Email(message = "Email không đúng định dạng...........")
    private String email;

    private String fullName;    
    private String phone;
    private Role role;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String email, String fullName, String phone, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}

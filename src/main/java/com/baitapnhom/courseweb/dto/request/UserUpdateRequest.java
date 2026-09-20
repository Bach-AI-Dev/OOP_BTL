package com.baitapnhom.courseweb.dto.request;

import com.baitapnhom.courseweb.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserUpdateRequest {

    @NotBlank(message = "Password khong duoc de trong..............")
    @Size(min = 6, message = "Password phai co it nhat 6 ky tu............")
    private String password;

    @NotBlank(message = "Email khong duoc de trong.........")
    @Email(message = "Email khong dung dinh dang...........")
    private String email;

    private String fullName;
    private String phone;
    private Role role;

    public UserUpdateRequest() {
    }

    public UserUpdateRequest(String password, String email, String fullName, String phone, Role role) {
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.role = role;
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

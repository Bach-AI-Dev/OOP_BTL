package com.baitapnhom.courseweb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baitapnhom.courseweb.dto.request.RegisterRequest;
import com.baitapnhom.courseweb.dto.request.UserUpdateRequest;
import com.baitapnhom.courseweb.entity.User;
import com.baitapnhom.courseweb.enums.Role;
import com.baitapnhom.courseweb.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User register(RegisterRequest request) {

        User user = new User();

        // Kiểm tra trùng lặp
        if (userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("User existed(User da ton tai)...................");

        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email existed(Email da ton toi)...................");

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole() != null ? request.getRole() : Role.STUDENT);

        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found..............................."));
    }

    public User updatUser(String userId, UserUpdateRequest request) {
        User user = getUser(userId);
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole() != null ? request.getRole() : Role.STUDENT);

        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

}

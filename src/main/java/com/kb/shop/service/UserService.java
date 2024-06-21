package com.kb.shop.service;

import com.kb.shop.domain.User;
import com.kb.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String checkIdAndChangePassword(Long userId, String oldPassword, String newPassword) {
        // user의 credential 정보가 맞는지 확인
        if (checkPasswordByUserInfo(userId, oldPassword)) {
            if (changePassword(userId, newPassword)) {
                return "password has been changed successfully!";
            }
            else return "password not changed.";
        }
        else return "user is not exist.";
    }

    public Boolean changePassword(Long userId, String newPassword) {
        User user = new User();
        user.setId(userId);
        user.setPassword(newPassword);
        userRepository.save(user);
        return true;
    }

    public Boolean checkIdExist(Long userId) {
       return userRepository.existsById(userId);
    }

    public Boolean checkUsernameExist(String username) {
         User user = userRepository.findByUsername(username);
         return user != null ? true:false;
    }

    public Boolean checkPasswordByUserInfo(Long userId, String password) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()){
            return false;
        }
        else {
            if (user.get().getPassword().equals(password)) {
                return true;
            }
            else return false;
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void createAdminUser() {
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(Collections.singleton("ADMIN"));
            userRepository.save(admin);
        }
    }
}

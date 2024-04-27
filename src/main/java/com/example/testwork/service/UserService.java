package com.example.testwork.service;

import com.example.testwork.dao.User;
import org.springframework.validation.Errors;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(User user, Errors errors);
    User updateUser(UUID id, User user, Errors errors);
    User getUserById(UUID id);
    void deleteUser(UUID id);
    List<User> searchUsersByBirthDateRange(LocalDate from, LocalDate to);
}

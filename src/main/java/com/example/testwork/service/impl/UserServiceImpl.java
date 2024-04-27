package com.example.testwork.service.impl;

import com.example.testwork.dao.User;
import com.example.testwork.service.UserService;
import com.example.testwork.service.validator.UserValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.testwork.util.Util.userList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserValidatorImpl userValidator;

    @Override
    public User createUser(User user, Errors errors) {
        userValidator.validate(user, errors);
        if (errors.hasErrors()) {
            return null;
        }

        user.setId(generateID());
        userList.add(user);
        return user;
    }

    @Override
    public User updateUser(UUID id, User user, Errors errors) {
        User existingUser = getUserById(id);
        if (existingUser == null) {
            return null;
        }

        user.setId(id);
        userValidator.validate(user, errors);
        if (errors.hasErrors()) {
            return null;
        }

        userList.remove(existingUser);
        userList.add(user);
        return user;
    }

    @Override
    public User getUserById(UUID id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void deleteUser(UUID id) {
        userList.removeIf(user -> user.getId().equals(id));
    }


    @Override
    public List<User> searchUsersByBirthDateRange(LocalDate from, LocalDate to) {
        List<User> usersInRange = new ArrayList<>();

        for (User user : userList) {
            LocalDate userBirthday = user.getBirthday();
            if (userBirthday != null && !userBirthday.isBefore(from) && !userBirthday.isAfter(to)) {
                usersInRange.add(user);
            }
        }

        return usersInRange;
    }


    private UUID generateID() {
        return UUID.randomUUID();
    }
}

package com.example.testwork.service;

import com.example.testwork.dao.User;

import java.util.Date;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public User createUser(String email, String firstName, String lastName, Date birthday, String address, String phone) {
        User user = new User();
        user.setId(generateID());
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthday(birthday);
        user.setAddress(address);
        user.setPhone(phone);
        return user;
    }

    @Override
    public User updateUser(UUID id, String email, String firstName, String lastName, Date birthday, String address, String phone) {
        return null;
    }

    private UUID generateID(){
        UUID uuid = UUID.randomUUID();
        return uuid;
    }
}

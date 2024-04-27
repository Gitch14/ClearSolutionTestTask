package com.example.testwork.dao;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String address;
    private String phone;
}

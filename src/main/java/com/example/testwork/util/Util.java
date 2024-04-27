package com.example.testwork.util;

import com.example.testwork.dao.User;

import java.util.ArrayList;
import java.util.List;


public class Util {
    public static List<User> userList = new ArrayList<>();
    public static final String EMAIL_REGEX_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,7}$";
}

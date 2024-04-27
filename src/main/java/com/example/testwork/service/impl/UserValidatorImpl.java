package com.example.testwork.service.impl;

import com.example.testwork.dao.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;

import static com.example.testwork.util.Util.EMAIL_REGEX_PATTERN;

@Component
public class UserValidatorImpl implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        List<String> errorList = new ArrayList<>();

        // Проверка обязательных полей
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required", "Email is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required", "First name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required", "Last name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "birthday.required", "Birthday is required");

        // Проверка спецификаций полей
        if (!StringUtils.isEmpty(user.getEmail()) && !user.getEmail().matches(EMAIL_REGEX_PATTERN)) {
            errorList.add("Invalid email format");
        }

        if (user.getBirthday() != null && user.getBirthday().after(new Date())) {
            errorList.add("Birthday must be before current date");
        }
    }
}

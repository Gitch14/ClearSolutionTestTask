package com.example.testwork.service.validator;

import com.example.testwork.dao.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;

import static com.example.testwork.util.Util.EMAIL_REGEX_PATTERN;

@Component
public class UserValidatorImpl implements Validator {

    private boolean checkBirthdayYear;
    @Value("${age.requirements}")
    private int age;

    public void setCheckBirthdayYear(boolean checkBirthdayYear) {
        this.checkBirthdayYear = checkBirthdayYear;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required", "Email is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required", "First name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required", "Last name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "birthday.required", "Birthday is required");

        if (!user.getEmail().matches(EMAIL_REGEX_PATTERN)) {
            errors.rejectValue("email", "email.invalid", "Invalid email format");
        }

        if (user.getBirthday() != null) {
            LocalDate birthday = user.getBirthday();

            if (checkBirthdayYear && isFutureDate(birthday)) {
                errors.rejectValue("birthday", "birthday.futureDate", "Birthday date cannot be in the future");
            }

            if (isUnderAge(birthday)) {
                errors.rejectValue("birthday", "birthday.underAge", "User must be at least 18 years old");
            }
        }
    }

    private boolean isFutureDate(LocalDate date) {
        LocalDate now = LocalDate.now();
        return date.isAfter(now);
    }

    private boolean isUnderAge(LocalDate birthday) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthday, today);
        return period.getYears() < age;
    }
}

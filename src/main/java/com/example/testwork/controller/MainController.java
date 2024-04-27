package com.example.testwork.controller;

import com.example.testwork.dao.User;
import com.example.testwork.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        return userService.createUser(user,bindingResult);
    }

    @PostMapping("/update/{id}") // @PutMapping
    public User updateUser(@PathVariable UUID id, @Valid @RequestBody User user,BindingResult bindingResult) {
        return userService.updateUser(id, user,bindingResult);
    }


    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping("/delete/{id}") // @DeleteMapping
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @GetMapping("/search")
    public List<User> searchUsersByBirthDateRange(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        return userService.searchUsersByBirthDateRange(from, to);
    }
}

package com.example.testwork;

import com.example.testwork.controller.MainController;
import com.example.testwork.dao.User;
import com.example.testwork.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.verify;

class UserServiceTest {

    private MainController mainController;
    private BindingResult bindingResult;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mainController = new MainController(userService);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        mainController.createUser(user, bindingResult);
        verify(userService).createUser(user, bindingResult);
    }

    @Test
    void testUpdateUser() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        mainController.updateUser(userId, user, bindingResult);
        verify(userService).updateUser(userId, user, bindingResult);
    }

    @Test
    void testGetUserById() {
        UUID userId = UUID.randomUUID();
        mainController.getUserById(userId);
        verify(userService).getUserById(userId);
    }

    @Test
    void testDeleteUser() {
        UUID userId = UUID.randomUUID();
        mainController.deleteUser(userId);
        verify(userService).deleteUser(userId);
    }

    @Test
    void testSearchUsersByBirthDateRange() {
        LocalDate fromDate = LocalDate.now().minusYears(30);
        LocalDate toDate = LocalDate.now();
        mainController.searchUsersByBirthDateRange(fromDate, toDate);
        verify(userService).searchUsersByBirthDateRange(fromDate, toDate);
    }

}

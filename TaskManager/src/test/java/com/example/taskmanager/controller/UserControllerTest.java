package com.example.taskmanager.controller;

import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.service.UserService;
import com.example.taskmanager.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserControllerTest {
//    @Autowired
//    UserService userService;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService = new UserServiceImpl();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addUser() {
        User s = new User(15,"hoangkb","hoagnvvn","abcd");
        Mockito.when(userRepository.save(s)).thenReturn(s);
        assertEquals("hoangkb",userService.getUserById(15));
    }
}
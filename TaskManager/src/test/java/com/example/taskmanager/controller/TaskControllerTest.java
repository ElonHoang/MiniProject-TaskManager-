package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TaskControllerTest {
    //@Autowired
    @Mock
    public TaskRepository taskRepository;
    @InjectMocks
    public TaskServiceImpl taskService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void getAllTask() {
//        List<Task>  taskList = taskService.findAll();
//        int size = taskList.size();
//        assertEquals(6,size);
        TaskStatus t = TaskStatus.OPEN;
        List<Task> taskList = Arrays.asList(new Task(1,"test1","des1",t),new Task(2,"test2","des2",t));
        Mockito.when(taskRepository.findAll()).thenReturn(taskList);
        assertEquals(2,taskService.findAll().size());
    }

//    @Test
//    public void pagination() {
//    }
}
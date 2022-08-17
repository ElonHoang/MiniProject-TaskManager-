package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task createTask(Task task);

    Task updateTask(Task task);

    Optional<Task> getTaskById(int id);

    void deleteTaskById(int taskId);

    List<Task> findAll();
    List<Task> findAll(Integer userId);


    public Page<Task> searchTaskTitle(String title,Integer userId, int page);

    public Page<Task> selectTaskByTaskStatusAndTitle(String status, String title,Integer userId, int page);


    void exportToCSV(HttpServletResponse response, Integer userId) throws IOException;
}

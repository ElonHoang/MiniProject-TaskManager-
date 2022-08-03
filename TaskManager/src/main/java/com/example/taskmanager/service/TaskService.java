package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Integer createTask(Task task);

    Integer updateTask(Task task);

    Optional<Task> getTaskById(int id);

    void deleteTaskById(int taskId);

    List<Task> findAll();
    Page<Task> showAllPage(Integer page);
    Integer countPages(Integer page);


    public Page<Task> searchTaskTitle(String title, int page);

    public Page<Task> selectTaskByTaskStatusAndTitle(String status, String title, int page);
}

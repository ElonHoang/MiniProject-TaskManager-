package com.example.taskmanager.repository;


import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT u FROM Task u WHERE u.title LIKE %?1% ")
    public Page<Task> searchTaskTitle(String title, Pageable page);

    @Query("SELECT u FROM Task u WHERE u.status = ?1 AND u.title LIKE %?2% ")
    public Page<Task> selectTaskByTaskStatusAndTitle(TaskStatus status, String title, Pageable page);

}

package com.example.taskmanager.mapper;

import com.example.taskmanager.model.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

@Mapper
public interface TaskMapper {
    public List<Task> findAll(@Param("pageable") Pageable pageable);
    Integer deleteTask(@Param("id") Integer id);
    Integer insertTask(@Param("task") Task task);

    Integer updateTask(@Param("task") Task task);

    Optional<Task> selectTaskById(@Param("id") Integer id);

    List<Task> searchTasksByTitleAndStatus(@Param("title") String title, @Param("status") String status,@Param("pageable") Pageable pageable);

    List<Task> searchTasksByTitle(@Param("title") String title,@Param("pageable") Pageable page);

    Integer countPages(@Param("pageable") Pageable pageable);
}

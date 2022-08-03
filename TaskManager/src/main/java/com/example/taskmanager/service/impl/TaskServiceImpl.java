package com.example.taskmanager.service.impl;


import com.example.taskmanager.mapper.TaskMapper;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
     TaskRepository taskRepository;
    @Autowired
     TaskMapper taskMapper;

    @Override
    public Integer createTask(Task task) {
        //taskRepository.save(task);
        return taskMapper.insertTask(task);
    }

    @Override
    public Integer updateTask(Task task) {
        //Task t = taskRepository.findById(task.getId()).get();
        //Optional<Task> t = taskMapper.selectTaskById(task.getId());
//        t.setTitle(task.getTitle());
//        t.setDescription(task.getDescription());
//        t.setStatus(task.getStatus());
        //taskRepository.save(t);
        return taskMapper.updateTask(task);

//        return t;
    }

    @Override
    public Optional<Task> getTaskById(int id) {
//        return taskRepository.findById(id).isPresent() ? taskRepository.findById(id) : Optional.empty();
        return taskMapper.selectTaskById(id).isPresent() ? taskRepository.findById(id) : Optional.empty();
    }

    @Override
    public void deleteTaskById(int taskId) {
        //taskRepository.deleteById(taskId);
        taskMapper.deleteTask(taskId);
    }

    @Override
    public List<Task> findAll() {

        return taskRepository.findAll();
//        return taskMapper.findAll();
    }

    @Override
    public Page<Task> showAllPage(Integer page) {
        Pageable pageable = PageRequest.of(page -1,6);
        List<Task> tasks = taskMapper.findAll(pageable);
        return new PageImpl<>(tasks,pageable,taskMapper.countPages(pageable));
    }

    @Override
    public Integer countPages(Integer page) {
        Pageable pageable = PageRequest.of(page -1,6);
        return taskMapper.countPages(pageable);
    }


    @Override
    public Page<Task> searchTaskTitle(String title, int page) {
        Pageable pageable = PageRequest.of(page - 1, 6);
        return taskRepository.searchTaskTitle(title, pageable);
    }


    @Override
    public Page<Task> selectTaskByTaskStatusAndTitle(String status, String title, int page) {
        Pageable pageable = PageRequest.of(page - 1, 6);
        Page<Task> taskList;
//        List<Task> taskList = null;
        switch (status) {
            case "OPEN":
                taskList = taskRepository.selectTaskByTaskStatusAndTitle(TaskStatus.OPEN, title, pageable);
                //taskList = taskMapper.searchTasksByTitleAndStatus(title,TaskStatus.OPEN.getValue(),pageable);
                break;
            case "DONE":
                taskList = taskRepository.selectTaskByTaskStatusAndTitle(TaskStatus.DONE, title, pageable);
                //taskList = taskMapper.searchTasksByTitleAndStatus(title,TaskStatus.DONE.getValue(),pageable);
                break;
            case "INPROGRESS":
                taskList = taskRepository.selectTaskByTaskStatusAndTitle(TaskStatus.INPROGRESS, title, pageable);
                //taskList = taskMapper.searchTasksByTitleAndStatus(title,TaskStatus.INPROGRESS.getValue(),pageable);
                break;
            default:
                taskList = taskRepository.searchTaskTitle(title, pageable);
                //taskList = taskMapper.searchTasksByTitle(title,pageable);
                //taskList = taskMapper.searchTasksByTitleAndStatus(title,TaskStatus.OPEN.getValue(),pageable);
                break;
        }
        return taskList;
    }
}

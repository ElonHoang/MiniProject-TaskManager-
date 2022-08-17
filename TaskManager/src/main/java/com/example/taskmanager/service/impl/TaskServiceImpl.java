package com.example.taskmanager.service.impl;


import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
     TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        Task t = taskRepository.findById(task.getId()).get();
        t.setTitle(task.getTitle());
        t.setDescription(task.getDescription());
        t.setStatus(task.getStatus());
        taskRepository.save(t);
        return t;
    }

    @Override
    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id).isPresent() ? taskRepository.findById(id) : Optional.empty();
    }

    @Override
    public void deleteTaskById(int taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAll(Integer userId) {
        return taskRepository.findAll(userId);
    }



    @Override
    public Page<Task> searchTaskTitle(String title,Integer userId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 6);
        return taskRepository.searchTaskTitle(title,userId, pageable);
    }


    @Override
    public Page<Task> selectTaskByTaskStatusAndTitle(String status, String title,Integer userId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 6);
        Page<Task> taskList;
        switch (status) {
            case "OPEN":
                taskList = taskRepository.selectTaskByTaskStatusAndTitle(TaskStatus.OPEN, title,userId, pageable);
                break;
            case "DONE":
                taskList = taskRepository.selectTaskByTaskStatusAndTitle(TaskStatus.DONE, title,userId, pageable);
                break;
            case "INPROGRESS":
                taskList = taskRepository.selectTaskByTaskStatusAndTitle(TaskStatus.INPROGRESS, title,userId, pageable);
                break;
            default:
                taskList = taskRepository.searchTaskTitle(title,userId, pageable);
                break;
        }
        return taskList;
    }

    @Override
    public void exportToCSV(HttpServletResponse response,Integer userId) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + Task.class + "_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Task> listUsers = taskRepository.findAll(userId);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Task ID", "Title", "Description", "Status"};
        String[] nameMapping = {"id", "title", "description", "status"};

        csvWriter.writeHeader(csvHeader);

        for (Task task : listUsers) {
            csvWriter.write(task, nameMapping);
        }

        csvWriter.close();
    }
}

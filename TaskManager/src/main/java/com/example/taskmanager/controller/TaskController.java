package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.model.User;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.service.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/all")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public String showTasks(Model model,Authentication authentication) {

        return pagination(model,1, "", "",authentication);
    }

    @GetMapping("/task/{id}")
    public Optional<Task> getTaskById(int id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/create-task")
    public String goToAddTask(Model model) {
        model.addAttribute("task", new Task());
        return "addTask";
    }
    @PostMapping("/create")
    public String createTask(@Valid Task task, BindingResult rs, Authentication authentication) {
        if (rs.hasErrors()) {
            return "addTask";
        }
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        Integer userId = userDetail.getUser().getId();
        task.setUsers(userDetail.getUser());
        taskService.createTask(task);
        return "redirect:/all/tasks";
    }

    @GetMapping("/edit-task/{id}")
    public String goToEditTask(Model model, @PathVariable(value = "id") int id) {
        Optional<Task> edit = taskService.getTaskById(id);
        taskService.updateTask(edit.get());
        model.addAttribute("task", edit);
        return "editTask";
    }
    @PostMapping("/edit")
    public String editTask(@Valid Task task, BindingResult rs) {
        if(rs.hasErrors()){
            return "editTask";
        }
        taskService.updateTask(task);
        return "redirect:/all/tasks";
    }

    @GetMapping("/detail-task/{id}")
    public String detailTask(Model model, @PathVariable(value = "id") int id) {
        Optional<Task> detail = taskService.getTaskById(id);
        model.addAttribute("task", detail);
        return "detailTask";
    }

    @GetMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable(value = "id") int id) {
        taskService.deleteTaskById(id);
        return "redirect:/all/tasks";
    }

    @GetMapping("/search")
    public String searchByTitle(Model model, @Param("title") String title, @Param("status") String status, Authentication authentication) {
        return pagination(model, 1, title, status,authentication);
    }

    @GetMapping("/task/page")
    public String pagination(Model model, @RequestParam("page") Integer page, @Param("title") String title, @Param("status") String status, Authentication authentication) {
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        Integer userId = userDetail.getUser().getId();
        Page<Task> taskPage = taskService.selectTaskByTaskStatusAndTitle(status, title,userId, page);
        //int totals = taskPage.getTotalPages();
        List<Task> taskList = taskPage.getContent();
        model.addAttribute("tasks", taskList);
        model.addAttribute("currentPage", page);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        return "tasks";
    }

    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response, Authentication authentication) throws IOException {
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        Integer userId = userDetail.getUser().getId();
        taskService.exportToCSV(response,userId);

    }


}

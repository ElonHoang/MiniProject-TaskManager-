package com.example.taskmanager.controller;



import com.example.taskmanager.model.User;
import com.example.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/authen")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String goToLogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String goToRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/add-user")
    public String addUser(@Valid @ModelAttribute User user, BindingResult rs) {
        if (rs.hasErrors()) {
            return "register";
        }
        if (userService.getUserByString(user.getUsername()) == true) {
            rs.addError(new FieldError("user", "username", "UserName is already exists !"));
            return "register";
        }
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String pass = encode.encode(user.getPassword());
        user.setPassword(pass);
        userService.createUser(user);
        return "redirect:/authen/login";
    }
    @GetMapping("/login/false")
    public String messageError(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("msg","Username or Password not true !");
        return "login";
    }


}

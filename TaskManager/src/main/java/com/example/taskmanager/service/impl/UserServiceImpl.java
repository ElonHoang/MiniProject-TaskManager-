package com.example.taskmanager.service.impl;

import com.example.taskmanager.mapper.UserMapper;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;

//import com.example.taskmanager.service.UserDetail;
import com.example.taskmanager.service.UserDetail;
import com.example.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User findUser = userRepository.findByUsername(username);
        User findUser = userMapper.findUserByUsername(username);
        return new UserDetail(findUser);
    }


    @Override
    public Integer createUser(User user) {
        //return userRepository.save(user);
        return userMapper.insertUser(user);


    }

    @Override
    public User updateUser(User user) {
        User userId = userRepository.findById(user.getId()).get();
        userId.setName(user.getName());
        userId.setPassword(user.getPassword());
        return user;
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id).isPresent() ? userRepository.findById(id) : Optional.empty();
    }

    @Override
    public boolean getUserByString(String name) {
        User findUser = userRepository.findByUsername(name);
        if (findUser == null) return false;
        if (findUser.getUsername().equals(name)) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public String findByPassword(String username) {

        //return userRepository.findByPassword(username)
        return userMapper.findPasswordByUsername(username);
    }


}

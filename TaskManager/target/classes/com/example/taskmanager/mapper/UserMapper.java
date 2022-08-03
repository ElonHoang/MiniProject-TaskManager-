package com.example.taskmanager.mapper;

import com.example.taskmanager.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    public String findPasswordByUsername(@Param("username") String username);
    public User findUserByUsername(@Param("username") String username);
    public Integer insertUser(@Param("user") User user);
}

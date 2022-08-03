package com.example.taskmanager.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    private int id;
    @Size(min = 3, max = 50, message = "name min >= 3 and max <= 50")
    @Column(name = "name")
    private String name;
    @Size(min = 3, max = 50, message = "userName min >= 3 and max <= 50")
    @Column(name = "username")
    private String username;
    @Size(min = 3, max = 250, message = "passWord min >= 3 and max <= 250")
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }


}

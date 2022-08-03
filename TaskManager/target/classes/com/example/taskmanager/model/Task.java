package com.example.taskmanager.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "task_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 3, max = 250, message = "title min >= 3 and max <= 250")
    private String title;
    @Size(min = 3, max = 250, message = "description min >= 3 and max <= 250")
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private TaskStatus status;

    public Task() {
        this.status = TaskStatus.OPEN;
    }

    public Task(int id, String title, String description, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }


}

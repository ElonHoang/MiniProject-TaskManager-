package com.example.taskmanager.model;

public enum TaskStatus {
    OPEN("OPEN"),DONE("DONE"),INPROGRESS("INPROGRESS");
    private final String value;
    TaskStatus(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}

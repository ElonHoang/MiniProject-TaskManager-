package com.example.taskmanager.model.ulti;

import com.example.taskmanager.model.TaskStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class convert implements AttributeConverter<TaskStatus, String> {
    @Override
    public String convertToDatabaseColumn(TaskStatus attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public TaskStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return Stream.of(TaskStatus.values())
                .filter(t -> t.getValue().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

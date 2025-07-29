package com.example.todo.util;

import com.example.todo.dto.TodoDto;
import com.example.todo.model.Todo;

public class TodoMapper {

    public static TodoDto toDto(Todo todo) {
        TodoDto dto = new TodoDto();
        dto.setId(todo.getId());
        dto.setTitle(todo.getTitle());
        dto.setCompleted(todo.isCompleted());
        return dto;
    }

    public static Todo toEntity(TodoDto dto) {
        Todo todo = new Todo();
        if (dto.getId() != null) {
            todo.setId(dto.getId());
        }
        todo.setTitle(dto.getTitle());
        todo.setCompleted(dto.isCompleted());
        return todo;
    }
}

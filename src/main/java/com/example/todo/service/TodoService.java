package com.example.todo.service;

import com.example.todo.dto.TodoDto;
import com.example.todo.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {

    List<TodoDto> getAllTodos();

    Optional<TodoDto> getTodoById(Long id);

    TodoDto createTodo(TodoDto todoDto);

    Optional<TodoDto> updateTodo(Long id, TodoDto newTodoDto);

    void deleteTodo(Long id);
}

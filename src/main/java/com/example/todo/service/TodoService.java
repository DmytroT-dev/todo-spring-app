package com.example.todo.service;

import com.example.todo.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {

    List<Todo> getAllTodos();

    Optional<Todo> getTodoById(Long id);

    Todo createTodo(Todo todo);

    Optional<Todo> updateTodo(Long id, Todo todo);

    void deleteTodo(Long id);
}

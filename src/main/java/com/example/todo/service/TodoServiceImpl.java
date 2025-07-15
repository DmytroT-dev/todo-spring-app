package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository repository;

    @Override
    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Todo> getTodoById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Todo createTodo(Todo todo) {
        return repository.save(todo);
    }

    @Override
    public Optional<Todo> updateTodo(Long id, Todo newTodo) {
        return repository.findById(id)
                .map(exitingTodo -> {
                   exitingTodo.setTitle(newTodo.getTitle());
                   exitingTodo.setCompleted(newTodo.isCompleted());
                   return repository.save(exitingTodo);
                });
    }

    @Override
    public void deleteTodo(Long id) {
        repository.deleteById(id);
    }
}

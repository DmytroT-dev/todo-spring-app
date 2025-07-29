package com.example.todo.service;

import com.example.todo.dto.TodoDto;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import com.example.todo.util.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository repository;

    @Override
    public List<TodoDto> getAllTodos() {
        return repository.findAll().stream()
                .map(TodoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TodoDto> getTodoById(Long id) {
        return repository.findById(id)
                .map(TodoMapper::toDto);
    }

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        Todo todo = TodoMapper.toEntity(todoDto);
        Todo saved = repository.save(todo);
        return TodoMapper.toDto(saved);
    }

    @Override
    public Optional<TodoDto> updateTodo(Long id, TodoDto newTodoDto) {
        return repository.findById(id)
                .map(exiting -> {
                   exiting.setTitle(newTodoDto.getTitle());
                   exiting.setCompleted(newTodoDto.isCompleted());
                   Todo updated =  repository.save(exiting);
                   return TodoMapper.toDto(updated);
                });
    }

    @Override
    public void deleteTodo(Long id) {
        repository.deleteById(id);
    }
}

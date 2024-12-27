package com.example.todo.sevice;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;  // MongoDB Repository

    // Get all Todos
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    // Find Todo by ID
    public Optional<Todo> findById(String id) {
        return todoRepository.findById(id);
    }

    // Save or update Todo
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    // Delete Todo by ID
    public void deleteById(String id) {
        todoRepository.deleteById(id);
    }
}

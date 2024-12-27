package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:5500") // Allow requests from the frontend server running on port 5500
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    // Get all todos
    @GetMapping
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    // Create new todo
    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    // Update the completed status
    @PutMapping("/{id}")
    public Todo updateCompleted(@PathVariable String id, @RequestBody Todo todo) {
        Todo existingTodo = todoRepository.findById(id).orElse(null);
        if (existingTodo != null) {
            existingTodo.setCompleted(todo.isCompleted());
            return todoRepository.save(existingTodo);
        }
        return null;
    }

    // Delete a todo
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id) {
        todoRepository.deleteById(id);
    }
}

package com.example.demo.todos;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todos")
public class TodoController {
    private final TodoService todoService;
    TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @GetMapping
    public List<Todo> getTodos() {
        return this.todoService.getTodos();
    }
    @PostMapping
    public void addTodo(@RequestBody TodoRequest request) {
        this.todoService.addTodo(request);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        this.todoService.deleteById(id);
    }
    @GetMapping("{id}")
    public Todo findById(@PathVariable("id") Integer id) {
        return this.todoService.findById(id);
    }
}

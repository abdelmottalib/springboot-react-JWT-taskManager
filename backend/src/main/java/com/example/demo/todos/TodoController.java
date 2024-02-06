package com.example.demo.todos;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todos")
@CrossOrigin(origins = "http://localhost:5173")
public class TodoController {
    private final TodoService todoService;
    TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @GetMapping
    public List<Todo> getTodos() {
        return this.todoService.getTodos();
    }
    @GetMapping("{id}")
    public Todo findById(@PathVariable("id") Integer id) {
        return this.todoService.findById(id);
    }
    @PostMapping
    public void addTodo(@RequestBody TodoRequest request) {
        this.todoService.addTodo(request);
    }
    @PutMapping("{id}")
    public void updateById(@PathVariable("id") Integer id, @RequestBody TodoRequest request) {
        this.todoService.updateById(id, request);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        this.todoService.deleteById(id);
    }
}

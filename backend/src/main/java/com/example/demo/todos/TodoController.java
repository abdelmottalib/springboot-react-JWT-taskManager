package com.example.demo.todos;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*")
public class TodoController {
    private final TodoService todoService;
    TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @GetMapping("{userId}/todos")
    public List<Todo> getTodos(@PathVariable("userId") Integer userId) {
        System.out.println("from controller in todo");
        return this.todoService.getTodos(userId);
    }
    @GetMapping("{id}")
    public Todo findById(@PathVariable("id") Integer id) {
        return this.todoService.findById(id);
    }
    @PostMapping("{userId}/todos")
    public void addTodo(@PathVariable("userId") Integer userId,@RequestBody TodoRequest request) {
        System.out.println("from controller in todo1");
        this.todoService.addTodo(userId, request);
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

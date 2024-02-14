package com.example.demo.todos;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoController {
    private final TodoService todoService;
    TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @GetMapping
    public List<Todo> getTodos(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("the user name is " + userDetails.getUsername());
        return this.todoService.getTodos(userDetails.getUsername());
    }
    @GetMapping("{id}")
    public Todo findById(@AuthenticationPrincipal UserDetails userDetails,@PathVariable("id") Integer id) {
        return this.todoService.findById(userDetails.getUsername() ,id);
    }
    @PostMapping
    public void addTodo(@AuthenticationPrincipal UserDetails userDetails,@RequestBody TodoRequest request) {
        System.out.println("from controller in todo1");
        System.out.println("the username is " + userDetails.getUsername());
        this.todoService.addTodo(userDetails.getUsername(), request);
    }
    @PutMapping("{id}")
    public void updateById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Integer id, @RequestBody TodoRequest request) {
        System.out.println("from controller in updateById");
        this.todoService.updateById(userDetails.getUsername(), id, request);
    }
    @DeleteMapping("{id}")
    public void deleteById(@AuthenticationPrincipal UserDetails userDetails ,@PathVariable("id") Integer id) {
        System.out.println("from controller in deleteById");
        this.todoService.deleteById(userDetails.getUsername(), id);
    }
}

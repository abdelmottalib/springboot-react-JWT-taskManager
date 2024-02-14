package com.example.demo.todos;


import com.example.demo.exceptions.FieldIsEmptyException;
import com.example.demo.exceptions.IdNotFoundException;
import com.example.demo.exceptions.TitleAlreadyExistsException;
import com.example.demo.user.User;
import com.example.demo.user.UserJPAService;
import com.example.demo.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TodoService {
    private final TodoDao todoDao;
    private final UserService userService;
    TodoService(TodoJPAService todoDao, UserService userService) {
        this.userService = userService;
        this.todoDao = todoDao;
    }
    public List<Todo> getTodos(String email) {
        User user = userService.getUserByEmail(email);
        return this.todoDao.getTodos(user.getId());
    }
        public void addTodo(String email, TodoRequest request) {
        if (request.getTitle() == null || request.getDescription() == null || request.getDone() == null) {
            System.out.println("from exception 1");
            throw new FieldIsEmptyException("Title, description, and done fields cannot be empty");
        }
        System.out.println("from service in todo");
        User user = userService.getUserByEmail(email);
        this.todoDao.addTodo(user ,request);
    }
    public void deleteById(String email, Integer id) {
        User user = userService.getUserByEmail(email);
        Todo todo = this.todoDao.findById(id).orElseThrow(() -> new IdNotFoundException("Todo with id " + id + " not found"));
        if (!Objects.equals(todo.getUser().getId(), user.getId())) {
            System.out.println("this is not your todo");
            throw new IdNotFoundException("this is not your todo");
        }
        this.todoDao.deleteById(id);
    }
    public Todo findById(String email, Integer id) {
        User user = userService.getUserByEmail(email);
        Todo todo = this.todoDao.findById(id).orElseThrow(() -> new IdNotFoundException("Todo with id " + id + " not found"));
        if (!Objects.equals(todo.getUser().getId(), user.getId())) {
            System.out.println("this is not your todo");
            throw new IdNotFoundException("this is not your todo");
        }
        return todo;
    }
    public void updateById(String email, Integer id, TodoRequest request) {
        System.out.println("from service in updateById");
        User user = userService.getUserByEmail(email);
        Todo todo = this.todoDao.findById(id).orElseThrow(() -> new IdNotFoundException("Todo with id " + id + " not found"));
        if (!Objects.equals(todo.getUser().getId(), user.getId())) {
            System.out.println("this is not your todo");
            throw new IdNotFoundException("this is not your todo");
        }
        this.todoDao.updateById(id, request);
    }
}

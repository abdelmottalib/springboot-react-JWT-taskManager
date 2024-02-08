package com.example.demo.todos;


import com.example.demo.exceptions.FieldIsEmptyException;
import com.example.demo.exceptions.IdNotFoundException;
import com.example.demo.exceptions.TitleAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoDao todoDao;
    TodoService(TodoJPAService todoDao) {
        this.todoDao = todoDao;
    }
    public List<Todo> getTodos(Integer userId) {
        return this.todoDao.getTodos(userId);
    }
        public void addTodo(Integer userId, TodoRequest request) {
        if (request.getTitle() == null || request.getDescription() == null || request.getDone() == null) {
            System.out.println("from exception 1");
            throw new FieldIsEmptyException("Title, description, and done fields cannot be empty");
        }
        if (todoDao.existsByTitle(request.getTitle())) {
            System.out.println("from exception 2");
            throw new TitleAlreadyExistsException(request.getTitle() + " already exists");
        }
            System.out.println("from service in todo");
        this.todoDao.addTodo(userId ,request);
    }
    public void deleteById(Integer id) {
        if (!todoDao.existsById(id)) {
            throw new IdNotFoundException("Todo with id " + id + " not found");
        }
        this.todoDao.deleteById(id);
    }
    public Todo findById(Integer id) {
        return this.todoDao.findById(id).orElseThrow(() -> new IdNotFoundException("Todo with id " + id + " not found"));
    }
    public void updateById(Integer id, TodoRequest request) {
        if (!todoDao.existsById(id)) {
            throw new IdNotFoundException("Todo with id " + id + " not found");
        }
        this.todoDao.updateById(id, request);
    }
}

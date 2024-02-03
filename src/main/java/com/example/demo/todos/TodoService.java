package com.example.demo.todos;


import exceptions.IdNotFoundException;
import exceptions.TitleAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoDao todoDao;
    TodoService(TodoJPAService todoDao) {
        this.todoDao = todoDao;
    }
    public List<Todo> getTodos() {
        return this.todoDao.getTodos();
    }
        public void addTodo(TodoRequest request) {
        if (todoDao.existsByTitle(request.getTitle())) {
            throw new TitleAlreadyExistsException(request.getTitle() + " already exists");
        }
        this.todoDao.addTodo(request);
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
}

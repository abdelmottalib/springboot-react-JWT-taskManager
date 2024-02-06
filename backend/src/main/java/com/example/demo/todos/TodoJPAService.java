package com.example.demo.todos;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoJPAService implements TodoDao {
    TodoRepository todoRepository;
    TodoJPAService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }
    @Override
    public void addTodo(TodoRequest request) {
//        System.out.println("lllllllllllllllllllllllllllllllllll");
        Todo todo = new Todo(request.getTitle(), request.getDescription(), request.getDone());
//        System.out.println("lllllllllllllllllllllllllllllllllll09890879879");
        todoRepository.save(todo);
    }
    @Override
    public Boolean existsByTitle(String title) {
        return todoRepository.existsByTitle(title);
    }
    @Override
    public void deleteById(Integer id) {
        todoRepository.deleteById(id);
    }
    @Override
    public Boolean existsById(Integer id) {
        return todoRepository.existsById(id);
    }
    @Override
    public Optional<Todo> findById(Integer id) {
        return todoRepository.findById(id);
    }
    @Override
    public void updateById(Integer id, TodoRequest request) {
        Todo todo = todoRepository.findById(id).get();
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setDone(request.getDone());
        todoRepository.save(todo);
    }
}

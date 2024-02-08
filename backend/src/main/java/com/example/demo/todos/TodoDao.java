package com.example.demo.todos;


import java.util.List;
import java.util.Optional;

public interface TodoDao {
    List<Todo> getTodos(Integer userId);
    void addTodo(Integer userId, TodoRequest request);

    Boolean existsByTitle(String title);
    void deleteById(Integer id);
    Boolean existsById(Integer id);
    Optional<Todo> findById(Integer id);
    void updateById(Integer id, TodoRequest request);
}

package com.example.demo.todos;


import com.example.demo.user.User;

import java.util.List;
import java.util.Optional;


public interface TodoDao {
    List<Todo> getTodos(Integer userId);
    void addTodo(User user, TodoRequest request);

    Boolean existsByTitle(String title);
    void deleteById(Integer id);
    Boolean existsById(Integer id);
    Boolean existsByUserId(Integer userId);
    Optional<Todo> findById(Integer id);
    void updateById(Integer id, TodoRequest request);
}

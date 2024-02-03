package com.example.demo.todos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
    Boolean existsByTitle(String title);

    @Override
    boolean existsById(Integer integer);
}

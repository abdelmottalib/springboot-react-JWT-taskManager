package com.example.demo.todos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
    Boolean existsByTitle(String title);

    @Override
    boolean existsById(@NonNull Integer integer);
}

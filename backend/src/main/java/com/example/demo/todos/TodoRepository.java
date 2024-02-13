package com.example.demo.todos;

import com.example.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
    Boolean existsByTitle(String title);

    @Override
    boolean existsById(@NonNull Integer integer);
    boolean existsByUserId(Integer userId);
    List<Todo> findByUserId(Integer userId);
}

package com.example.demo.todos;

import jakarta.persistence.EntityNotFoundException;
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
        Todo todo = new Todo(request.getTitle(), request.getDescription(), request.getDone());
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
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setTitle(request.getTitle());
            todo.setDescription(request.getDescription());
            todo.setDone(request.getDone());

            // Logging to check the state before and after update
            System.out.println("Before update - Todo ID: " + todo.getId() + ", Done: " + todo.getDone());

            todoRepository.save(todo);

            // Logging to check the state after update
            System.out.println("After update - Todo ID: " + todo.getId() + ", Done: " + todo.getDone());
        } else {
            // Handle the case where the todo with the given ID is not found
            throw new EntityNotFoundException("Todo with ID " + id + " not found");
        }
    }
}

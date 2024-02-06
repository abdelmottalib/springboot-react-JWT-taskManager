package com.example.demo.todos;

public class TodoRequest {
    private final String title;
    private final String description;
    private final Boolean done;

    public TodoRequest(String title, String description, boolean done) {
        this.title = title;
        this.description = description;
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getDone() {
        return done;
    }
}

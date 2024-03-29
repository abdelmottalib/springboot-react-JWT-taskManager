package com.example.demo.todos;


import com.example.demo.user.User;
import jakarta.persistence.*;

@Entity
public class Todo {
    @Id
    @SequenceGenerator( //these are basically for auto incrementing the id
            name = "todo_id_seq",
            sequenceName = "todo_id_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "todo_id_seq"
    )
    @Column(
            nullable = false
    )
    private Integer id;
    @Column(
            nullable = false
    )
    private  String title;
    @Column(
            nullable = false
    )
    private  String description;
    @Column(
            nullable = false
    )
    private  boolean done;


    public User getUser() {
        return user;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Todo() {
    }
    public Todo(String title, String description, boolean done) {
        this.title = title;
        this.description = description;
        this.done = done;
    }
    public Todo(String title, String description, boolean done, User user) {
        this.title = title;
        this.description = description;
        this.done = done;
        this.user = user;
    }
    public Integer getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean getDone() {
        return done;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    @Override
    public String toString() {
        return "Todo{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", done=" + done +
                '}';
    }

}

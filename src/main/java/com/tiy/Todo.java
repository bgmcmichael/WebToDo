package com.tiy;

import javax.persistence.*;

/**
 * Created by fenji on 9/15/2016.
 */
@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String text;

    @Column(nullable = false)
    boolean isDone = false;

    @ManyToOne
    User user;

    public Todo() {
    }

    public Todo(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

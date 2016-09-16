package com.tiy;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by fenji on 9/15/2016.
 */
public interface TodoRepository extends CrudRepository<Todo, Integer> {
        // allow to search by user
    List<Todo> findByUser(User user);
}


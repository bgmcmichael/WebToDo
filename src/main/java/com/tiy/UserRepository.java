package com.tiy;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by fenji on 9/15/2016.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findFirstByName(String name);
}


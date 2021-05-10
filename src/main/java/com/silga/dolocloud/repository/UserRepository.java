package com.silga.dolocloud.repository;

import com.silga.dolocloud.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}


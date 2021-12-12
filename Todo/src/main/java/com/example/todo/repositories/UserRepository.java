package com.example.todo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByEmail(final String email);

}
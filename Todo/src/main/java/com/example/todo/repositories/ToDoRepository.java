package com.example.todo.repositories;

import com.example.todo.models.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ToDoRepository extends JpaRepository<ToDoList, Long> {
    
    ToDoList findByTitle(final String title);
}
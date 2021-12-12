package com.example.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Role findByRole(final String role);

}
package com.example.coffeeshop.repository;

import java.util.Optional;

import com.example.coffeeshop.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findUserByEmail(String email);

    
}

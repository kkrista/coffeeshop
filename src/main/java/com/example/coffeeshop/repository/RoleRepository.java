package com.example.coffeeshop.repository;

import com.example.coffeeshop.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    
}

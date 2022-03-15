package com.example.coffeeshop.repository;

import com.example.coffeeshop.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
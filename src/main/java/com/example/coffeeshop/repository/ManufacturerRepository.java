package com.example.coffeeshop.repository;

import com.example.coffeeshop.entity.Manufacturer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
    
}

package com.example.coffeeshop.repository;

import java.util.List;

import com.example.coffeeshop.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory_Id(int id);
    List<Product> findAllByManufacturer_Id(int id);
    List<Product> findAllBySupplier_Id(int id);

    @Query("SELECT product FROM Product product WHERE lower(product.name) LIKE %?1%")
    List<Product> findByNameContainingIgnoreCase(String keyword);

    
}
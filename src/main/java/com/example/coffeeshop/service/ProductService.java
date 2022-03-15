package com.example.coffeeshop.service;

import java.util.List;
import java.util.Optional;

import com.example.coffeeshop.entity.Product;

import com.example.coffeeshop.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProduct(String keyword) {
        if (keyword != null) {
            return productRepository.findByNameContainingIgnoreCase(keyword);
        }
        return productRepository.findAll();
    }

    

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void removeProductById(long id){
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProductsByCategoryId(int parentId) {
        return productRepository.findAllByCategory_Id(parentId);
    }

    public Page<Product> getProductPaginate(int currentPage, int size) {

        PageRequest pageable = PageRequest.of(currentPage, size);
        return productRepository.findAll(pageable);
    }

}

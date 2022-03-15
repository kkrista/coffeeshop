package com.example.coffeeshop.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private int id;

    private String name;
    private long parentId;
    
}
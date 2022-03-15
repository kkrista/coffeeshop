package com.example.coffeeshop.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "manufacturer_id")
    private int id;
    private String url;
    private String name;
    
}
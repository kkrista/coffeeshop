package com.example.coffeeshop.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "supplier_id")
    private int id;
    private String name;
    private String contactPerson;
    private String contactPersonEmail;

    
}

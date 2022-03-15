package com.example.coffeeshop.service;

import java.util.List;
import java.util.Optional;
import com.example.coffeeshop.entity.Supplier;
import com.example.coffeeshop.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }

    public void addSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public void removeSupplierById(int id) {
        supplierRepository.deleteById(id);
    }

    public Optional<Supplier> getSupplierById(int id) {
        return supplierRepository.findById(id);
    }
}

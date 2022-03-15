package com.example.coffeeshop.service;

import com.example.coffeeshop.entity.Manufacturer;
import com.example.coffeeshop.repository.ManufacturerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerService {
    @Autowired
    ManufacturerRepository manufacturerRepository;
    public List<Manufacturer> getAllManufacturer() {
        return manufacturerRepository.findAll();
    }
    public void addManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }   

    public void removeManufacturerById(int id) {
        manufacturerRepository.deleteById(id);
    }
    public Optional<Manufacturer> getManufacturerById(int id) {
        return manufacturerRepository.findById(id);
    }
}

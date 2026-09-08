package com.realestate.controller;

import com.realestate.entity.Property;
import com.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "*")
public class PropertyController {

    @Autowired PropertyRepository propertyRepository;

    @GetMapping
    public List<Property> getAll(@RequestParam(required = false) String city) {
        if(city != null && !city.trim().isEmpty()) {
            return propertyRepository.findByCityContainingIgnoreCase(city.trim());
        }
        return propertyRepository.findAll();
    }

    @PostMapping
    public Property addProperty(@RequestBody Property property) {
        return propertyRepository.save(property);
    }

    @DeleteMapping("/{id}")
    public void deleteProperty(@PathVariable Long id) {
        propertyRepository.deleteById(id);
    }
}

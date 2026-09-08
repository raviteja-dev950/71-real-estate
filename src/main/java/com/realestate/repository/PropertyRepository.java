package com.realestate.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.realestate.entity.Property;
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByCityContainingIgnoreCase(String city);
}
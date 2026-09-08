package com.realestate.config;

import com.realestate.entity.Property;
import com.realestate.entity.User;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired UserRepository userRepository;
    @Autowired PropertyRepository propertyRepository;
    @Autowired PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 1. Create admin
        User admin = null;
        if(userRepository.findByUsername("admin").isEmpty()) {
            admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            admin = userRepository.save(admin);
            System.out.println("Created admin / admin123");
        } else {
            admin = userRepository.findByUsername("admin").get();
        }

        // 2. Create owner
        if(userRepository.findByUsername("owner").isEmpty()) {
            User owner = new User();
            owner.setUsername("owner");
            owner.setPassword(passwordEncoder.encode("owner123"));
            owner.setRole("OWNER");
            userRepository.save(owner);
            System.out.println("Created owner / owner123");
        }

        // 3. Auto-insert properties if empty (so you never see 0 properties)
        if(propertyRepository.count() == 0) {
            Property p1 = new Property();
            p1.setTitle("Razole Dream House");
            p1.setCity("Razole");
            p1.setType("FLAT");
            p1.setPrice(2500000);
            p1.setImageUrl("https://images.unsplash.com/photo-1600596542815-ffad4c1539a9");
            p1.setDescription("2BHK Flat near temple - Verified - Direct from Owner - No Brokerage");
            p1.setOwner(admin);
            propertyRepository.save(p1);

            Property p2 = new Property();
            p2.setTitle("Hyderabad Villa");
            p2.setCity("Hyderabad");
            p2.setType("VILLA");
            p2.setPrice(8500000);
            p2.setImageUrl("https://images.unsplash.com/photo-1600047509807-ba8f99d2cdde");
            p2.setDescription("Luxury Villa with private pool - 4BHK - Gated Community - Verified Owner");
            p2.setOwner(admin);
            propertyRepository.save(p2);

            Property p3 = new Property();
            p3.setTitle("Bangalore Plot");
            p3.setCity("Bangalore");
            p3.setType("PLOT");
            p3.setPrice(4500000);
            p3.setImageUrl("https://images.unsplash.com/photo-1500382017468-9049fed747ef");
            p3.setDescription("Premium Plot near IT Park - 2400 sqft - Clear Title - Direct Owner Sale");
            p3.setOwner(admin);
            propertyRepository.save(p3);

            System.out.println("Auto-inserted 3 properties - Razole, Hyderabad, Bangalore");
        }
    }
}
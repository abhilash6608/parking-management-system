package com.college.parking.config;

import com.college.parking.entity.*;
import com.college.parking.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import java.util.stream.IntStream;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seed(UserRepository users, ParkingSlotRepository slots){
        return args -> {
            if(users.count()==0){
                users.save(new User("Admin","admin@college.com","admin123","9000000000","ADMIN001",User.Role.ADMIN));
                users.save(new User("Student Demo","student@college.com","student123","9000000001","STU001",User.Role.STUDENT));
            }
            if(slots.count()==0){
                IntStream.rangeClosed(1,10).forEach(i ->
                    slots.save(new ParkingSlot("A"+String.format("%02d",i),"Block A",
                        i<=6 ? Vehicle.VehicleType.TWO_WHEELER : Vehicle.VehicleType.FOUR_WHEELER)));
            }
        };
    }
}

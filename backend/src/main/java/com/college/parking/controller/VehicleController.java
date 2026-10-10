package com.college.parking.controller;

import com.college.parking.entity.*;
import com.college.parking.repository.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "http://localhost:5173")
public class VehicleController {
    private final VehicleRepository vehicles;
    private final UserRepository users;
    public VehicleController(VehicleRepository vehicles, UserRepository users){
        this.vehicles=vehicles; this.users=users;
    }

    @GetMapping public List<Vehicle> all(){return vehicles.findAll();}
    @GetMapping("/user/{userId}") public List<Vehicle> byUser(@PathVariable Long userId){
        return vehicles.findByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String,Object> body){
        Long userId=Long.valueOf(body.get("userId").toString());
        User user=users.findById(userId).orElse(null);
        if(user==null) return ResponseEntity.badRequest().body(Map.of("message","User not found"));
        Vehicle v=new Vehicle(body.get("vehicleNumber").toString().toUpperCase(),
            Vehicle.VehicleType.valueOf(body.get("vehicleType").toString()),user);
        return ResponseEntity.ok(vehicles.save(v));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        vehicles.deleteById(id); return ResponseEntity.ok(Map.of("message","Vehicle deleted"));
    }
}

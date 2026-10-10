package com.college.parking.controller;

import com.college.parking.entity.*;
import com.college.parking.repository.ParkingSlotRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/parking-slots")
@CrossOrigin(origins = "http://localhost:5173")
public class ParkingSlotController {
    private final ParkingSlotRepository repo;
    public ParkingSlotController(ParkingSlotRepository repo){this.repo=repo;}

    @GetMapping public List<ParkingSlot> all(){return repo.findAll();}
    @GetMapping("/available") public List<ParkingSlot> available(){
        return repo.findByStatus(ParkingSlot.SlotStatus.AVAILABLE);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String,String> body){
        ParkingSlot s=new ParkingSlot(body.get("slotNumber"),body.get("location"),
            Vehicle.VehicleType.valueOf(body.get("vehicleType")));
        return ResponseEntity.ok(repo.save(s));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> status(@PathVariable Long id,@RequestBody Map<String,String> body){
        return repo.findById(id).map(s -> {
            s.setStatus(ParkingSlot.SlotStatus.valueOf(body.get("status")));
            return ResponseEntity.ok(repo.save(s));
        }).orElse(ResponseEntity.notFound().build());
    }
}

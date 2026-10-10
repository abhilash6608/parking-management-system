package com.college.parking.controller;

import com.college.parking.entity.*;
import com.college.parking.repository.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.*;
import java.util.*;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {
    private final BookingRepository bookings;
    private final UserRepository users;
    private final VehicleRepository vehicles;
    private final ParkingSlotRepository slots;

    public BookingController(BookingRepository bookings,UserRepository users,
                              VehicleRepository vehicles,ParkingSlotRepository slots){
        this.bookings=bookings; this.users=users; this.vehicles=vehicles; this.slots=slots;
    }

    @GetMapping public List<Booking> all(){return bookings.findAll();}
    @GetMapping("/user/{userId}") public List<Booking> byUser(@PathVariable Long userId){
        return bookings.findByUserIdOrderByBookingDateDesc(userId);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String,String> b){
        try {
            User u=users.findById(Long.valueOf(b.get("userId"))).orElseThrow();
            Vehicle v=vehicles.findById(Long.valueOf(b.get("vehicleId"))).orElseThrow();
            ParkingSlot s=slots.findById(Long.valueOf(b.get("slotId"))).orElseThrow();

            if(s.getStatus()!=ParkingSlot.SlotStatus.AVAILABLE)
                return ResponseEntity.badRequest().body(Map.of("message","Slot is not available"));
            if(v.getVehicleType()!=s.getVehicleType())
                return ResponseEntity.badRequest().body(Map.of("message","Vehicle type does not match slot"));

            Booking booking=new Booking();
            booking.setUser(u); booking.setVehicle(v); booking.setSlot(s);
            booking.setBookingDate(LocalDate.parse(b.get("bookingDate")));
            booking.setStartTime(LocalTime.parse(b.get("startTime")));
            booking.setEndTime(LocalTime.parse(b.get("endTime")));
            booking.setStatus(Booking.BookingStatus.CONFIRMED);

            s.setStatus(ParkingSlot.SlotStatus.OCCUPIED);
            slots.save(s);
            return ResponseEntity.ok(bookings.save(booking));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message","Invalid booking data"));
        }
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id){
        return bookings.findById(id).map(b -> {
            b.setStatus(Booking.BookingStatus.CANCELLED);
            b.getSlot().setStatus(ParkingSlot.SlotStatus.AVAILABLE);
            slots.save(b.getSlot());
            return ResponseEntity.ok(bookings.save(b));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<?> complete(@PathVariable Long id){
        return bookings.findById(id).map(b -> {
            b.setStatus(Booking.BookingStatus.COMPLETED);
            b.setExitTime(LocalTime.now());
            b.getSlot().setStatus(ParkingSlot.SlotStatus.AVAILABLE);
            slots.save(b.getSlot());
            return ResponseEntity.ok(bookings.save(b));
        }).orElse(ResponseEntity.notFound().build());
    }
}

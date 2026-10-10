package com.college.parking.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="bookings")
public class Booking {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private User user;

    @ManyToOne(optional=false)
    private Vehicle vehicle;

    @ManyToOne(optional=false)
    private ParkingSlot slot;

    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.CONFIRMED;

    private LocalTime entryTime;
    private LocalTime exitTime;

    public Booking() {}

    public enum BookingStatus { CONFIRMED, COMPLETED, CANCELLED, OVERDUE }

    public Long getId(){return id;}
    public User getUser(){return user;}
    public void setUser(User v){user=v;}
    public Vehicle getVehicle(){return vehicle;}
    public void setVehicle(Vehicle v){vehicle=v;}
    public ParkingSlot getSlot(){return slot;}
    public void setSlot(ParkingSlot v){slot=v;}
    public LocalDate getBookingDate(){return bookingDate;}
    public void setBookingDate(LocalDate v){bookingDate=v;}
    public LocalTime getStartTime(){return startTime;}
    public void setStartTime(LocalTime v){startTime=v;}
    public LocalTime getEndTime(){return endTime;}
    public void setEndTime(LocalTime v){endTime=v;}
    public BookingStatus getStatus(){return status;}
    public void setStatus(BookingStatus v){status=v;}
    public LocalTime getEntryTime(){return entryTime;}
    public void setEntryTime(LocalTime v){entryTime=v;}
    public LocalTime getExitTime(){return exitTime;}
    public void setExitTime(LocalTime v){exitTime=v;}
}

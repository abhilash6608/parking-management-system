package com.college.parking.entity;

import jakarta.persistence.*;

@Entity
@Table(name="parking_slots")
public class ParkingSlot {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String slotNumber;

    private String location;

    @Enumerated(EnumType.STRING)
    private Vehicle.VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    private SlotStatus status = SlotStatus.AVAILABLE;

    public ParkingSlot() {}

    public ParkingSlot(String slotNumber, String location, Vehicle.VehicleType vehicleType) {
        this.slotNumber=slotNumber; this.location=location; this.vehicleType=vehicleType;
    }

    public enum SlotStatus { AVAILABLE, OCCUPIED, MAINTENANCE }

    public Long getId(){return id;}
    public String getSlotNumber(){return slotNumber;}
    public void setSlotNumber(String v){slotNumber=v;}
    public String getLocation(){return location;}
    public void setLocation(String v){location=v;}
    public Vehicle.VehicleType getVehicleType(){return vehicleType;}
    public void setVehicleType(Vehicle.VehicleType v){vehicleType=v;}
    public SlotStatus getStatus(){return status;}
    public void setStatus(SlotStatus v){status=v;}
}

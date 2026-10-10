package com.college.parking.entity;

import jakarta.persistence.*;

@Entity
@Table(name="vehicles")
public class Vehicle {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String vehicleNumber;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @ManyToOne(optional=false)
    private User user;

    public Vehicle() {}

    public Vehicle(String vehicleNumber, VehicleType vehicleType, User user) {
        this.vehicleNumber=vehicleNumber; this.vehicleType=vehicleType; this.user=user;
    }

    public enum VehicleType { TWO_WHEELER, FOUR_WHEELER }

    public Long getId(){return id;}
    public String getVehicleNumber(){return vehicleNumber;}
    public void setVehicleNumber(String v){vehicleNumber=v;}
    public VehicleType getVehicleType(){return vehicleType;}
    public void setVehicleType(VehicleType v){vehicleType=v;}
    public User getUser(){return user;}
    public void setUser(User v){user=v;}
}

package com.college.parking.repository;
import com.college.parking.entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot,Long> {
    List<ParkingSlot> findByStatus(ParkingSlot.SlotStatus status);
}

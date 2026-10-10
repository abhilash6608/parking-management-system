package com.college.parking.repository;
import com.college.parking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByUserIdOrderByBookingDateDesc(Long userId);
    long countByBookingDate(LocalDate date);
}

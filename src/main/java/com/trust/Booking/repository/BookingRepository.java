package com.trust.Booking.repository;

import com.trust.Booking.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Register, Integer> {
}

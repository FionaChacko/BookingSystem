package com.trust.Booking.repository;

import com.trust.Booking.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Register, Integer> {


    Optional<Register> findByEmail(String email);

    Optional<Register> findByName(String username);
}

package com.lavish.moviebookingapplication.Repository;

import com.lavish.moviebookingapplication.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
}

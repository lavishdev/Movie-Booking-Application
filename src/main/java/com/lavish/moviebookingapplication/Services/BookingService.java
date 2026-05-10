package com.lavish.moviebookingapplication.Services;

import com.lavish.moviebookingapplication.DTOs.Bookingdto;
import com.lavish.moviebookingapplication.Models.Booking;
import com.lavish.moviebookingapplication.Models.BookingStatus;
import com.lavish.moviebookingapplication.Models.Show;
import com.lavish.moviebookingapplication.Repository.BookingRepo;
import com.lavish.moviebookingapplication.Repository.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private ShowRepo showRepo;


    public Booking createBooking(Bookingdto bookingdto) {
        Show show = showRepo.findById(bookingdto.getShowId())
                .orElseThrow(()-> new RuntimeException("Show not found"));
        if(!isSeatsAvailable(show.getId(), bookingdto.getNumberOfSeats())){
            throw new RuntimeException("Seats not available");
        }
        if(!bookingdto.getSeatNumbers().size() != bookingdto.getNumberOfSeats()){
            throw new RuntimeException("seat Numbers and Number of seats must be equal")
        }

        ValidateDuplicateSeats(show.getId(), bookingdto.getSeatNumbers());

    }

    private void ValidateDuplicateSeats(Long id, List<String> seatNumbers) {
        Show show = showRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Show not found"));
        Set<String> occupiedSeats = show.getBookings().stream()
                .filter(b->b.getBookingStatus() != BookingStatus.CANCELLED)
                .filter(b->b.getSeatNumbers().stream())
                .collect(Collectors.toSet());
    }

    private boolean isSeatsAvailable(Long showid, Integer numberOfSeats) {
        Show show = showRepo.findById(showid)
                .orElseThrow(()-> new RuntimeException("Show not found"));
        int bookedSeats = show.getBookings().stream()
                .filter(booking -> booking.getBookingStatus() != BookingStatus.CANCELLED)
                .mapToInt(Booking::getNumberOfSeats)
                .sum();
        return (show.getTheatre().getTheatreCapacity() - bookedSeats) => numberOfSeats;
    }

    public Booking getUserBooking(Long id) {
    }

    public Booking getShowBooking(Long id) {
    }

    public Booking confirmBooking(Long id) {
    }

    public Booking cancelBooking(Long id) {
    }


}

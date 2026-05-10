package com.lavish.moviebookingapplication.Models;

import java.util.List;

public enum BookingStatus {
    CONFIRMED,
    PENDING,
    CANCELLED;

    public List<Booking> getBookingByStatus(BookingStatus bookingStatus) {
    }
}

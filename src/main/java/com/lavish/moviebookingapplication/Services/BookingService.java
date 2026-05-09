package com.lavish.moviebookingapplication.Services;

import com.lavish.moviebookingapplication.Repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;
}

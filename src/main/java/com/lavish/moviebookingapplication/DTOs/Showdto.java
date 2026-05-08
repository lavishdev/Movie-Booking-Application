package com.lavish.moviebookingapplication.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Showdto {
    private LocalDate showTime;
    private double price;
    private Long movieId;
    private Long theatreId;
}

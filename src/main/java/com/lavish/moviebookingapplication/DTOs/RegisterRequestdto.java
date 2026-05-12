package com.lavish.moviebookingapplication.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequestdto {
    private String username;
    private String password;
    private String email;
}

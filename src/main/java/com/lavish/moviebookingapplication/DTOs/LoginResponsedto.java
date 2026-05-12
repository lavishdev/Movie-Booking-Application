package com.lavish.moviebookingapplication.DTOs;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class LoginResponsedto {
    private String jwtToken;
    private String username;
    private Set<String> roles;
}

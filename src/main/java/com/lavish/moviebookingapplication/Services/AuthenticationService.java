package com.lavish.moviebookingapplication.Services;

import com.lavish.moviebookingapplication.DTOs.LoginRequestdto;
import com.lavish.moviebookingapplication.DTOs.LoginResponsedto;
import com.lavish.moviebookingapplication.DTOs.RegisterRequestdto;
import com.lavish.moviebookingapplication.JWT.JwtService;
import com.lavish.moviebookingapplication.Models.User;
import com.lavish.moviebookingapplication.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public User registerNormalUser(RegisterRequestdto registerRequestdto) {
        if(userRepo.findByUsername(registerRequestdto.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        Set<String> roles = new HashSet<String>();
        roles.add("ROLE_USER");

        User user = new User();
        user.setUsername(registerRequestdto.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestdto.getPassword()));
        user.setEmail(registerRequestdto.getEmail());
        user.setRoles(roles);
        userRepo.save(user);
    }

    public LoginResponsedto login(LoginRequestdto loginRequestdto) {
        User user  = userRepo.findByUsername(loginRequestdto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestdto.getUsername(),
                        loginRequestdto.getPassword()
                )
        );

        String token = jwtService.generateToken(user);

        return LoginResponsedto.builder()
                .jwtToken(token)
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }

    public User registerAdminUser(RegisterRequestdto registerRequestdto) {
        if(userRepo.findByUsername(registerRequestdto.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        Set<String> roles = new HashSet<String>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");

        User user = new User();
        user.setUsername(registerRequestdto.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestdto.getPassword()));
        user.setEmail(registerRequestdto.getEmail());
        user.setRoles(roles);
        userRepo.save(user);
    }
}

package com.ncprojects.controller;

import com.ncprojects.dto.SignInDto;
import com.ncprojects.dto.SignUpDto;
import com.ncprojects.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/public")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    ResponseEntity<String> registerUser(@RequestBody @Valid final SignUpDto signUpDTO){

        String token = authService.registerUser(signUpDTO);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    ResponseEntity<String> loginUser(@RequestBody SignInDto signInDto){
        String token = authService.authenticateAndGetToken(signInDto);
        return ResponseEntity.ok(token);
    }
}

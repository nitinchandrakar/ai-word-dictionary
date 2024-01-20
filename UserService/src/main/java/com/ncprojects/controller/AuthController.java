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

@RestController
@RequestMapping("/public")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    ResponseEntity<Long> registerUser(@RequestBody @Valid final SignUpDto signUpDTO){

        Long id = authService.registerUser(signUpDTO);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/login")
    ResponseEntity<String> loginUser(@RequestBody SignInDto signInDto){
        String token = authService.authenticateAndGetToken(signInDto);
        return ResponseEntity.ok(token);
    }
}
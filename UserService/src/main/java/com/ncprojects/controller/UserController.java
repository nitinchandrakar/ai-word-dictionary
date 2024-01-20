package com.ncprojects.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/profile")
    public String getUserProfile(){
        return "User profile"+ Thread.currentThread().getName();
    }
}

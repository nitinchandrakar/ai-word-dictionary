package com.ncprojects.controller;

import com.ncprojects.Dto.AuthorDto;
import com.ncprojects.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @GetMapping("/profile")
    public String getUserProfile(@AuthenticationPrincipal UserDetails userDetails){
        return "User Details: " + userDetails.getUsername();
    }

    @GetMapping("/author-info")
    public ResponseEntity<AuthorDto> getUserIdFromToken(@AuthenticationPrincipal UserDetails userDetails){

        AuthorDto authorDto = userInfoService.getAuthorInfo(userDetails.getUsername());
        return ResponseEntity.ok(authorDto);
    }
}

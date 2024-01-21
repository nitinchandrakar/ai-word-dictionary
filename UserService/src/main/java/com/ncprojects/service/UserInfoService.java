package com.ncprojects.service;

import com.ncprojects.Dto.AuthorDto;
import com.ncprojects.entity.User;
import com.ncprojects.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private IUserRepo iUserRepo;

    @Autowired Jwtservice jwtservice;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = iUserRepo.findByUsername(username);
        if(userDetails == null){
            throw new UsernameNotFoundException("User does not exist");
        }
        return userDetails;
    }

    public String getUserId(String token){
        return jwtservice.extractUsername(token);
    }

    public AuthorDto getAuthorInfo(String username) {
        User user = iUserRepo.findByUsername(username);
        AuthorDto authorDto = new AuthorDto();
        authorDto.setAuthorName(user.getFirstname() +" "+user.getLastname());
        authorDto.setAuthorEmail(user.getUsername());
        authorDto.setAuthorId(user.getId());
        return authorDto;
    }
}

package com.ncprojects.service;

import com.ncprojects.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private IUserRepo iUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = iUserRepo.findByUsername(username);
        if(userDetails == null){
            throw new UsernameNotFoundException("User does not exist");
        }
        return userDetails;
    }

}

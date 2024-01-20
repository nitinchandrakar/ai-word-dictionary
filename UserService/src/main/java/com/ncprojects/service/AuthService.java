package com.ncprojects.service;

import com.ncprojects.dto.SignInDto;
import com.ncprojects.dto.SignUpDto;
import com.ncprojects.entity.User;
import com.ncprojects.enums.SubscriptionType;
import com.ncprojects.enums.UserRolesEnum;
import com.ncprojects.enums.UserVerificationStatus;
import com.ncprojects.repository.IUserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private Jwtservice jwtservice;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepo iUserRepo;

    public Long registerUser(SignUpDto signUpDto){
       return createUser(signUpDto);
    }

    public String authenticateAndGetToken(SignInDto signInDto){
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(signInDto.getEmail(),
                        signInDto.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtservice.generateToken(signInDto.getEmail());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @Transactional
    private Long createUser(SignUpDto signUpDTO){
        final User user = new User();

        mapToEntity(signUpDTO, user);

        user.setRole(UserRolesEnum.AUTHOR);
        user.setSubscriptionType(SubscriptionType.FREE);
        user.setVerificationStatus(UserVerificationStatus.PENDING);
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));

        iUserRepo.save(user);


        return user.getId();
    }

    private void mapToEntity(SignUpDto signUpDTO, User user){
        user.setFirstname(signUpDTO.getFirstname());
        user.setLastname(signUpDTO.getLastname());
        user.setUsername(signUpDTO.getEmail());
    }
}

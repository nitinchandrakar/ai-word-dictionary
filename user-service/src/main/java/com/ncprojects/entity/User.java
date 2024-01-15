package com.ncprojects.entity;

import com.ncprojects.enums.SubscriptionType;
import com.ncprojects.enums.UserRolesEnum;
import com.ncprojects.enums.UserVerificactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Setter
@Getter
public class User {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRolesEnum role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserVerificactionStatus accountStatus;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;
}


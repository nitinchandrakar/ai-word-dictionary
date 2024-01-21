package com.ncprojects.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name = "words")
public class Word {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "word", nullable = false)
    private String word;

    @Column(name = "meaning", nullable = false)
    private String meaning;

    @Column(name = "description")
    private String description;

    @Column(name = "author-email",  nullable = false)
    private String email;

    @Column(name = "author-name",  nullable = false)
    private String authorName;

    @Column(name = "status",  nullable = false)
    private String status;
}

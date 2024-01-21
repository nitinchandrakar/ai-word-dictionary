package com.ncprojects.Dto;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorDto {
    private UUID authorId;
    private String authorEmail;
    private String authorName;
}

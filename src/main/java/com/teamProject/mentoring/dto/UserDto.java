package com.teamProject.mentoring.dto;


import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDto {
    private Long id;

    private String name;

    private String email;

    private String password;





}
